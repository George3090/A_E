package com.example.aesthetics_enginers.User_Workout_Tracking;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Day_Overview_FullPage extends AppCompatActivity {
    private ImageView day_Image;
    private TextView day_Title, day_Description;
    private Button TrackMyWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__overview__full_page);
        //Getting Data from Intent
        String Day_Image = getIntent().getStringExtra("Image_Main");
        String Day_Title = getIntent().getStringExtra("Title");
        String Day_Description = getIntent().getStringExtra("Day_Description");
        //Finding Views
        day_Image = findViewById(R.id.ImageViweDayIMAGE);
        day_Title = findViewById(R.id.Textview_Day_Title);
        day_Description = findViewById(R.id.Textview_Day_Description);
        TrackMyWorkout = findViewById(R.id.Button_Track_My_Workout);

        //Setting views
        day_Title.setText(Day_Title);
        day_Description.setText(Day_Description);
        Glide.with(this).load(Day_Image).into(day_Image);

        TrackMyWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });




    }
}