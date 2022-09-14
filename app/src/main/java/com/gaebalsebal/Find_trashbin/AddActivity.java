package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gaebalsebal.Find_trashbin.databinding.ActivityAddBinding;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAddBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText NameText = findViewById(R.id.editTextTextPersonName);
        EditText latitudeText = findViewById(R.id.editTextNumber);
        EditText longtitudeText = findViewById(R.id.editTextNumber2);
        Button Apply = findViewById(R.id.button1);
        Button Delete = findViewById(R.id.button2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String idToken;

        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hi");
                System.out.println(latitudeText.getText());

                Map<String, Object> point = new HashMap<>();

                point.put("Name", NameText.getText().toString());
                point.put("latitude", parseDouble(latitudeText.getText().toString()));
                point.put("longtitude", parseDouble(longtitudeText.getText().toString()));
                point.put("check",false);

                db.collection("point")
                        .add(point)
                        .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                        .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                AlertDialog.Builder exit = new AlertDialog.Builder(AddActivity.this);
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
