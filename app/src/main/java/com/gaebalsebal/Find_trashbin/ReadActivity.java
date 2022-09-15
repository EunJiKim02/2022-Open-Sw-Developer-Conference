package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gaebalsebal.Find_trashbin.databinding.ActivityPostBinding;
import com.gaebalsebal.Find_trashbin.databinding.ActivityReadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReadActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    FirebaseUser user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        @NonNull ActivityReadBinding binding;

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        binding = ActivityReadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = (FirebaseUser) intent.getExtras().get("user"); //currentuser
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String Id = intent.getStringExtra("documentid");

        binding.read.setText(content);
        binding.jaemok.setText(title);
        binding.username.setText( name+" ( " + email + " ) ");

        if(user.getEmail().equals(email) )
        {
            binding.deletepost.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.deletepost.setVisibility(View.INVISIBLE);
        }

        binding.deletepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("post").document(Id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                AlertDialog.Builder exit = new AlertDialog.Builder(ReadActivity.this);
                                exit.setMessage("글이 삭제되었습니다.");

                                exit.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                });
                                exit.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });


            }
        });







    }


}
