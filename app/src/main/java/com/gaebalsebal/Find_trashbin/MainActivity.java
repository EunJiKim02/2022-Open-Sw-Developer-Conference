package com.gaebalsebal.Find_trashbin;

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
                animateFab();
            }
        });
        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(MainActivity.this, "baseline_delete_24 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(MainActivity.this, "add_trashbin clicked", Toast.LENGTH_SHORT).show();
            }
        });
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(MainActivity.this, "baseline_chat_24", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateFab() {
        if (isOpen){
            menu_btn.startAnimation(rotateFor);
            guide_btn.startAnimation(fabClose);
            edit_btn.startAnimation(fabClose);
            community_btn.startAnimation(fabClose);
            guide_btn.setClickable(false);
            edit_btn.setClickable(false);
            community_btn.setClickable(false);
            isOpen=false;
        }
        else {
            menu_btn.startAnimation(rotateBack);
            guide_btn.startAnimation(fabOpen);
            edit_btn.startAnimation(fabOpen);
            community_btn.startAnimation(fabOpen);
            guide_btn.setClickable(true);
            edit_btn.setClickable(true);
            community_btn.setClickable(true);
            isOpen=true;
        }
    }
}
