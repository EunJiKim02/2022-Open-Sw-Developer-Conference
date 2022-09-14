package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;
import static java.lang.Double.parseDouble;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gaebalsebal.Find_trashbin.databinding.ActivityPostBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;
    FirebaseUser user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user =(FirebaseUser) intent.getExtras().get("user");

        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> post = new HashMap<>();

                post.put("time",System.currentTimeMillis());
                post.put("title", binding.postTitle.getText().toString());
                post.put("content", binding.editTextTextMultiLine.getText().toString());
                post.put("userid", user.getEmail());

                db.collection("post")
                        .add(post)
                        .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                        .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                AlertDialog.Builder exit = new AlertDialog.Builder(PostActivity.this);
                exit.setMessage("정상적으로 등록되었습니다.");

                exit.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                exit.show();
            }
        });


    }
}
