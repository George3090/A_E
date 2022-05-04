package com.example.aesthetics_enginers.RecycleViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.R;

public class Exercise_Guide_Full_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise__guide__full__page);

        //getting data from recycleView
        String Title = getIntent().getStringExtra("Title");
        String Eqipment = getIntent().getStringExtra("Equipment");
        String Muscle = getIntent().getStringExtra("Muscle");
        String Img1 = getIntent().getStringExtra("Img1");
        String Img2 = getIntent().getStringExtra("Img2");

        //getting the views
        TextView titleTextView = findViewById(R.id.textViewtitle);
        TextView textViewMuscle = findViewById(R.id.textViewMuscle);
        TextView textViewEquipment = findViewById(R.id.textViewEquipment);
        ImageView imgView1 = findViewById(R.id.imageView1);
        ImageView imgView2 = findViewById(R.id.imageView2);

        //Assigning values to the view
        titleTextView.setText(Title);
        textViewEquipment.setText(Eqipment);
        textViewMuscle.setText(Muscle);
        Glide.with(this).load(Img1).into(imgView1);
        Glide.with(this).load(Img2).into(imgView2);



    }
}