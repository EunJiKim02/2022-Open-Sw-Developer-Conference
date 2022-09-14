package com.gaebalsebal.Find_trashbin;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gaebalsebal.Find_trashbin.databinding.ActivityPostBinding;
import com.gaebalsebal.Find_trashbin.databinding.ActivityReadBinding;
import com.google.firebase.auth.FirebaseUser;

public class ReadActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        @NonNull ActivityReadBinding binding;

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        binding = ActivityReadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = (FirebaseUser) intent.getExtras().get("user");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        binding.read.setText(content);
        binding.jaemok.setText(title);
        binding.username.setText(user.getDisplayName());





    }


}
