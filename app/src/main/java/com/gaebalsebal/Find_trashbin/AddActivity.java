package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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
        Button apply = findViewById(R.id.button);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hi");
                System.out.println(latitudeText.getText());

                Map<String, Object> point = new HashMap<>();

                point.put("Name", NameText.getText().toString());
                point.put("latitude", parseInt(latitudeText.getText().toString()));
                point.put("longtitude", parseInt(longtitudeText.getText().toString()));

                db.collection("point")
                        .add(point)
                        .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                        .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }


        });



    }
}
