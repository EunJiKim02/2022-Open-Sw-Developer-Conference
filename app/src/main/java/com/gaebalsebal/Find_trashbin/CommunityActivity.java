package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommunityActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user = null;
    BeginSignInRequest signInRequest;
    SignInClient oneTapClient;
    GoogleSignInClient googleSignInClient;
    GoogleSignInClient mGoogleSignInClient = null;
    private ActivityResultLauncher<Intent> resultLauncher;

    ArrayList<Mypost> dataList = new ArrayList<>();
    ImageButton login_btn;
    FloatingActionButton post_btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    // ...
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    // ...


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);



        login_btn = findViewById(R.id.loginbutton);
        post_btn = findViewById(R.id.post_button);
        RecyclerView recycler_View = findViewById(R.id.recyclerview);



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        //리사이클 뷰 적용
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context)this);
        recycler_View.setLayoutManager(linearLayoutManager);


        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);


        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent intent3 = new Intent(CommunityActivity.this, ReadActivity.class);
                intent3.putExtra("title", dataList.get(position).title);
                intent3.putExtra("content", dataList.get(position).content);
                intent3.putExtra("user", user);
                startActivity(intent3);
            }
        });


        recycler_View.setAdapter(adapter);


/*
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("이거 실행은 됨?");
                Toast.makeText(CommunityActivity.this, "으악", Toast.LENGTH_SHORT).show();
            }
        });*/
/*
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(dataList);
        myRecyclerAdapter.setOnItemClickListener();



        myRecyclerAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/

        updateUI(user);


        //db에서 데이터 불러오기
        db.collection("post").orderBy("time", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                String datatitle = (String) document.getData().get("title");
                                String datacontent = (String) document.getData().get("content");
                                String postid = (String) document.getId();
                                String Token = (String) document.getData().get("userToken");
                                dataList.add(new Mypost(datatitle, datacontent, postid));
                                adapter.notifyDataSetChanged();
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });




/*
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(this, i,Toast.LENGTH_SHORT ).show();
        }*/


        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.default_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build();



        //로그인 버튼 구현(구글 로그인)
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user == null)
                {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    Intent signInIntent = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build();
                    signInLauncher.launch(signInIntent);
                }
                else
                {
                    FirebaseAuth.getInstance().signOut();
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    updateUI(user);
                }

            }

        });

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user != null)
                {
                    Intent intent = new Intent(CommunityActivity.this, PostActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(CommunityActivity.this, "로그인을 먼저 해주세요",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });





    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            user = FirebaseAuth.getInstance().getCurrentUser();
            updateUI(user);

            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with Firebase.
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "signInWithCredential:success");
                                            user = mAuth.getCurrentUser();
                                            updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                                            updateUI(null);
                                        }
                                    }
                                });
                    }
                } catch (ApiException e) {
                    // ...
                }
                break;
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        user = currentUser;
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser user) { //update ui code here
        TextView username = findViewById(R.id.user_name);
        if (user != null) {
            username.setText(user.getDisplayName());
        }
        else
        {
            username.setText("로그인");
        }
    }


}



//db 데이터 담을 용도
class Mypost {
    String title, content, postid;
    Mypost(String title, String content, String postid){
        this.title = title;
        this.content = content;
        this.postid = postid;
    }
}



