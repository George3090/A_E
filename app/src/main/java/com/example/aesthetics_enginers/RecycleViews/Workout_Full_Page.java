package com.example.aesthetics_enginers.RecycleViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.MainActivity;
import com.example.aesthetics_enginers.Manage_My_Workouts;
import com.example.aesthetics_enginers.R;

public class Workout_Full_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__full__page);


        String Title = getIntent().getStringExtra("Title");
        String MainImage = getIntent().getStringExtra("Image_Main");
        String Img1 = getIntent().getStringExtra("Img1");
        String Img2 = getIntent().getStringExtra("Img2");
        //String MainImage = getIntent().getStringExtra("Image_Main");


        TextView titleTextView = findViewById(R.id.Title_Workout);
        TextView textViewDuration = findViewById(R.id.textViewDuration);
        TextView textView12 = findViewById(R.id.textView12);
        ImageView imgView_Main = findViewById(R.id.imageView12);
        ImageView imgView1 = findViewById(R.id.imageView7);
        ImageView imgView2 = findViewById(R.id.imageView6);


        titleTextView.setText(Title);
        Glide.with(this).load(MainImage).into(imgView_Main);
        Glide.with(this).load(MainImage).into(imgView1);
        Glide.with(this).load(MainImage).into(imgView2);


        // Adding the workout to the user_Workouts collections
        Button Start_Plan = findViewById(R.id.buttonStartWorkout);
        Start_Plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the Logic for adding the workout

                //Find some way of implementing this

            }
        });








    }
}