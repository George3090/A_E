package com.example.aesthetics_enginers.Fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aesthetics_enginers.Interfaces.Ex_RecycleViewInterface;
import com.example.aesthetics_enginers.MainActivity;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;
import com.example.aesthetics_enginers.RecycleViews.Exercise_Guide_Full_Page;
import com.example.aesthetics_enginers.RecycleViews.Workout_Full_Page;
import com.example.aesthetics_enginers.Utility.RecycleViewAdapter_Workout;
import com.example.aesthetics_enginers.Workouts;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Workout_Plan_Criteria extends Fragment implements Ex_RecycleViewInterface{
    private FirebaseFirestore mDb;
    private ArrayList<Workout> exercisesArrayList;
    private RecycleViewAdapter_Workout adapter;
    private View WorkoutView;


    public Workout_Plan_Criteria() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        WorkoutView = inflater.inflate(R.layout.fragment_workout__plan__criteria, container, false);
        RecyclerView recyclerView = WorkoutView.findViewById(R.id.recycleviewfragment1);
        //setUpExersizeModelsNeck();
        DatabaseEventChangeListener();
        adapter = new RecycleViewAdapter_Workout(context, exercisesArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));




        return WorkoutView;
    }


    private void DatabaseEventChangeListener(){
        mDb = FirebaseFirestore.getInstance();
        exercisesArrayList = new ArrayList<>();
        mDb.collection("collection_workouts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                exercisesArrayList.add(dc.getDocument().toObject(Workout.class));
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    @Override
    public void OnItemClicked(int position) {
        Intent intent = new Intent( getActivity() , Workout_Full_Page.class); //change MainActivity to added activity
        intent.putExtra("Title", exercisesArrayList.get(position).getTitle());
        intent.putExtra("Image_Main", exercisesArrayList.get(position).getMain_Image());
        intent.putExtra("Img1", exercisesArrayList.get(position).getImg1());
        intent.putExtra("Img2", exercisesArrayList.get(position).getImg2());


        startActivity(intent);
    }
}