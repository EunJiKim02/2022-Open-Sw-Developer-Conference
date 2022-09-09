package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        EditText latitudeText = findViewById(R.id.editTextNumber);
        EditText longtitudeText = findViewById(R.id.editTextNumber2);
        Button apply = findViewById(R.id.button);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Map<String, Object> point = new HashMap<>();
                point.put("latitude", parseInt(latitudeText.getText().toString()));
                point.put("longtitude", parseInt(longtitudeText.getText().toString()));

                db.collection("point")
                        .add(point)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });



    }
}
