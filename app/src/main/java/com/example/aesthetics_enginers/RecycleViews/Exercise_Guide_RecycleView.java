package com.example.aesthetics_enginers.RecycleViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aesthetics_enginers.Exercise_Guide;
import com.example.aesthetics_enginers.Interfaces.Ex_RecycleViewInterface;
import com.example.aesthetics_enginers.Models.Exercises;
import com.example.aesthetics_enginers.R;
import com.example.aesthetics_enginers.Utility.RecycleViewAdapter_Ex;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Exercise_Guide_RecycleView extends AppCompatActivity implements Ex_RecycleViewInterface {

    private FirebaseFirestore mDb;
    private ArrayList<Exercises> exercisesArrayList;
    private RecycleViewAdapter_Ex adapter;
    private String Muscle_Group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_rxercise_guide);

        Muscle_Group = getIntent().getStringExtra("Muscle_Group");


        RecyclerView recyclerView = findViewById(R.id.recycleviewlist);
        //setUpExersizeModelsNeck();
        DatabaseEventChangeListener();
        adapter = new RecycleViewAdapter_Ex(this, exercisesArrayList,this  );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Exercise_Guide.class));



            }
        });


    }


    private void DatabaseEventChangeListener(){
        mDb = FirebaseFirestore.getInstance();
        exercisesArrayList = new ArrayList<>();
        mDb.collection("Exercises").whereEqualTo("Muscle", Muscle_Group)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.e("Firestore error",error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        exercisesArrayList.add(dc.getDocument().toObject(Exercises.class));
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }






//    private void setUpExersizeModelsNeck(){
//        //get the model from the database where exersize group equal neck
//        mDb = FirebaseFirestore.getInstance();
//        exercisesArrayList = new ArrayList<>();
//        mDb.collection("Exercises")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for (QueryDocumentSnapshot document : task.getResult()){
//
//                                exercisesArrayList.add(document.toObject(Exercises.class));
//
//                            }
//                        } else {
//                            //Log.d(TAG, "Error getting documents: ", task.getException());
//                            Toast.makeText(Exercise_Guide_RecycleView.this,"Error getting documents: " ,Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//    }


    @Override
    public void OnItemClicked(int position) {
        Intent intent = new Intent(Exercise_Guide_RecycleView.this, Exercise_Guide_Full_Page.class); //change MainActivity to added activity
        intent.putExtra("Title", exercisesArrayList.get(position).getTitle());
        intent.putExtra("Equipment", exercisesArrayList.get(position).getEquipment());
        intent.putExtra("Muscle", exercisesArrayList.get(position).getMuscle());
        intent.putExtra("Img1", exercisesArrayList.get(position).getImage_1());
        intent.putExtra("Img2", exercisesArrayList.get(position).getImage_2());

        startActivity(intent);
    }


}