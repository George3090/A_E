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

public class Exercise_Guide_Back extends Fragment {
    private View exercise_View;
    private ImageView Imgview1;
    private Button button_Lats, button_Middle_back, button_Lower_Back, button_Quads, button_Calves,
            button_Hamstrings,button_Glutes,button_Triceps,button_Traps;


    private ConstraintLayout layout;

    public Exercise_Guide_Back() {
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
        exercise_View = inflater.inflate(R.layout.fragment_exercise__guide__back, container, false);
        Imgview1 = (ImageView) exercise_View.findViewById(R.id.Exersice_Guide_Back);
        Glide.with(Imgview1).load("https://firebasestorage.googleapis.com/v0/b/aestheticsenginering.appspot.com/o/Exercises%2FExerciseFilterBack.jpeg?alt=media&token=d532fac8-b031-4f58-a582-092de1befe2b").into(Imgview1);

        //find button by ID  add all of them afterwards
        button_Lats = (Button) exercise_View.findViewById(R.id.button_Lats);
        button_Middle_back = (Button) exercise_View.findViewById(R.id.button_Middle_back);
        button_Lower_Back = (Button) exercise_View.findViewById(R.id.button_Lower_Back);
        button_Quads = (Button) exercise_View.findViewById(R.id.button_Quads);
        button_Calves = (Button) exercise_View.findViewById(R.id.button_Calves);
        button_Hamstrings = (Button) exercise_View.findViewById(R.id.button_Hamstrings);
        button_Glutes = (Button) exercise_View.findViewById(R.id.button_Glutes);
        button_Triceps = (Button) exercise_View.findViewById(R.id.button_Triceps);
        button_Traps = (Button) exercise_View.findViewById(R.id.button_Traps);



        //set onClicklistener for buttons
        button_Lats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Lats");
                startActivity(intent);

            }
        });

        button_Middle_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Middle Back");
                startActivity(intent);

            }
        });

        button_Lower_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Lower Back");
                startActivity(intent);

            }
        });

        button_Quads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Quads");
                startActivity(intent);

            }
        });

        button_Calves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Calves");
                startActivity(intent);

            }
        });

        button_Hamstrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Hamstrings");
                startActivity(intent);

            }
        });

        button_Glutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Glutes");
                startActivity(intent);

            }
        });

        button_Triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Triceps");
                startActivity(intent);

            }
        });

        button_Traps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(context, Exercise_Guide_RecycleView.class));
                Intent intent = new Intent(context, Exercise_Guide_RecycleView.class);
                intent.putExtra("Muscle_Group", "Traps");
                startActivity(intent);

            }
        });


        return exercise_View;
    }


}