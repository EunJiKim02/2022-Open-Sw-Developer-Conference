package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gaebalsebal.Find_trashbin.databinding.ActivityCommunityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CommunityActivity extends AppCompatActivity {


    ArrayList<Mypost> dataList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context)this);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(myRecyclerAdapter);


        db.collection("post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                String datatitle = (String) document.getData().get("title");
                                String datacontent = (String) document.getData().get("content");
                                System.out.println(datatitle + " " + datacontent);
                                dataList.add(new Mypost(datatitle, datacontent));
                                //System.out.println(data.title+data.content);
                                //System.out.println(datalist.get(datalist.size()-1).title);
                                myRecyclerAdapter.notifyDataSetChanged();

                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });





    }


}


class Mypost {
    String title, content;
    Mypost(String title, String content){
        this.title = title;
        this.content = content;
    }
}
