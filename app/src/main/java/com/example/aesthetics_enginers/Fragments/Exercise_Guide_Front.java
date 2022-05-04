package com.example.aesthetics_enginers.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.RecycleViews.Exercise_Guide_RecycleView;
import com.example.aesthetics_enginers.R;


public class Exercise_Guide_Front extends Fragment {
    private View exercise_View;
    private ImageView Imgview1;
    private Button ButtonNeck, ButtonShoulder, ButtonBiceps, ButtonAbs, ButtonQuads,
            Button_Calves,Button_Forearms,Button_Chest,Button_Traps;


    private ConstraintLayout layout;

    public Exercise_Guide_Front() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Images
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup viewt = (ViewGroup) exercise_View.findViewById(R.id.view);
        // Inflate the layout for this fragment
        Context context = getContext(); //this may not work
        exercise_View = inflater.inflate(R.layout.fragment_exercise__guide__front, container, false);
        Imgview1 = (ImageView) exercise_View.findViewById(R.id.Exersice_Guide_Front);
        Glide.with(Imgview1).load("https://firebasestorage.googleapis.com/v0/b/aestheticsenginering.appspot.com/o/Exercises%2FExerciseFilterFrontr.jpeg?alt=media&token=f8741844-7ddb-46d6-87c0-bf8a0c77f429").into(Imgview1);

        //find button by ID  add all of them afterwards
        ButtonNeck = (Button) exercise_View.findViewById(R.id.button_Neck);
        ButtonShoulder = (Button) exercise_View.findViewById(R.id.button_Shoulders);
        ButtonBiceps = (Button) exercise_View.findViewById(R.id.button_Biceps);
        ButtonQuads = (Button) exercise_View.findViewById(R.id.button_Quads);
        ButtonAbs = (Button) exercise_View.findViewById(R.id.button_Abs);
        Button_Forearms = (Button) exercise_View.findViewById(R.id.button_Forearms);
        Button_Traps = (Button) exercise_View.findViewById(R.id.button_Traps);
        Button_Calves = (Button) exercise_View.findViewById(R.id.button_Calves);
        Button_Chest = (Button) exercise_View.findViewById(R.id.button_Chest);



        //set onClicklistener for buttons
        ButtonNeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Neck");
                startActivity(intent);

            }
        });

        ButtonShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Shoulders");
                startActivity(intent);

            }
        });

        ButtonBiceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Biceps");
                startActivity(intent);

            }
        });

        ButtonQuads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Quads");
                startActivity(intent);

            }
        });

        ButtonAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Abs");
                startActivity(intent);

            }
        });

        Button_Forearms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Forearms");
                startActivity(intent);

            }
        });

        Button_Traps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Traps");
                startActivity(intent);

            }
        });

        Button_Calves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Calves");
                startActivity(intent);

            }
        });

        Button_Chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Chest");
                startActivity(intent);

            }
        });


        return exercise_View;
    }


}