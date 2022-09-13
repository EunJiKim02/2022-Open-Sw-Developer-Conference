package com.gaebalsebal.Find_trashbin;

import static android.content.ContentValues.TAG;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;

    FloatingActionButton guide_btn;
    FloatingActionButton edit_btn;
    FloatingActionButton community_btn, menu_btn;

    Animation fabOpen, fabClose, rotateFor, rotateBack;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 35.8881);
        longitude = intent.getDoubleExtra("longitude", 128.6112);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);

        guide_btn = findViewById(R.id.guide_btn);
        edit_btn = findViewById(R.id.edit_btn);
        community_btn = findViewById(R.id.community_btn);
        menu_btn = (FloatingActionButton) findViewById(R.id.menu_btn);

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClicked();
            }
        });

        guide_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MapsActivity.this, GuideActivity.class);
                   startActivity(intent);
               }
           }
        );

        edit_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MapsActivity.this, AddActivity.class);
               startActivity(intent);
           }
       }
    );


        community_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MapsActivity.this, CommunityActivity.class);
               startActivity(intent);
           }
       }
       );

    }


    private void onAddButtonClicked() {
        setVisibility(isOpen);
        setAnimation(isOpen);
        setClickable(isOpen);
        isOpen = !isOpen;
    }

    private void setVisibility(Boolean isOpen){
        if(!isOpen){
            guide_btn.setVisibility(View.VISIBLE);
            edit_btn.setVisibility(View.VISIBLE);
            community_btn.setVisibility(View.VISIBLE);
        }
        else{
            guide_btn.setVisibility(View.INVISIBLE);
            edit_btn.setVisibility(View.INVISIBLE);
            community_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean isOpen) {
        if(!isOpen){
            menu_btn.setAnimation(rotateFor);
            guide_btn.setAnimation(fabOpen);
            edit_btn.setAnimation(fabOpen);
            community_btn.setAnimation(fabOpen);
        }
        else
        {
            menu_btn.setAnimation(rotateBack);
            guide_btn.setAnimation(fabClose);
            edit_btn.setAnimation(fabClose);
            community_btn.setAnimation(fabClose);
        }
    }

    private void setClickable(Boolean isOpen){
        if(!isOpen){
            guide_btn.setClickable(false);
            edit_btn.setClickable(false);
            community_btn.setClickable(false);
        }
        else
        {
            guide_btn.setClickable(true);
            edit_btn.setClickable(true);
            community_btn.setClickable(true);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        LatLng latlng = new LatLng(latitude, longitude);
        //mMap.addMarker(new MarkerOptions().position(latlng).title("IT 융복합관"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        db.collection("point")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                LatLng mlatlng = new LatLng((Double) document.getData().get("latitude"), (Double) document.getData().get("longtitude"));
                                //System.out.println(document.getData().get("Name"));
                                if ((Boolean)document.getData().get("check")){
                                    mMap.addMarker(new MarkerOptions().position(mlatlng).title(String.valueOf(document.get("Name"))));
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        }
    }


    /*
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton menu_btn, guide_btn, edit_btn, community_btn;
    Animation fabOpen, fabClose, rotateFor, rotateBack;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        menu_btn = (FloatingActionButton) findViewById(R.id.menu_btn);
        guide_btn = (FloatingActionButton) findViewById(R.id.guide_btn);
        edit_btn = (FloatingActionButton) findViewById(R.id.edit_btn);
        community_btn = (FloatingActionButton) findViewById(R.id.community_btn);

        rotateFor = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBack = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("hihihihihihihih********************");
                onAddButtonClicked();

            }
        });
        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "baseline_delete_24 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "add_trashbin clicked", Toast.LENGTH_SHORT).show();
            }
        });
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "baseline_chat_24", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onAddButtonClicked() {
        setVisibility(isOpen);
        setAnimation(isOpen);
        setClickable(isOpen);
        isOpen = !isOpen;
    }

    private void setVisibility(Boolean isOpen){
        if(!isOpen){
            guide_btn.setVisibility(View.VISIBLE);
            edit_btn.setVisibility(View.VISIBLE);
            community_btn.setVisibility(View.VISIBLE);
        }
        else{
            guide_btn.setVisibility(View.INVISIBLE);
            edit_btn.setVisibility(View.INVISIBLE);
            community_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean isOpen) {
        if(!isOpen){
            menu_btn.startAnimation(rotateFor);
            guide_btn.startAnimation(fabOpen);
            edit_btn.startAnimation(fabOpen);
            community_btn.startAnimation(fabOpen);
        }
        else
        {
            menu_btn.startAnimation(rotateBack);
            guide_btn.startAnimation(fabClose);
            edit_btn.startAnimation(fabClose);
            community_btn.startAnimation(fabClose);
        }
    }

    private void setClickable(Boolean isOpen){
        if(!isOpen){
            guide_btn.setClickable(false);
            edit_btn.setClickable(false);
            community_btn.setClickable(false);
        }
        else
        {
            guide_btn.setClickable(true);
            edit_btn.setClickable(true);
            community_btn.setClickable(true);
        }
    }
}*/
