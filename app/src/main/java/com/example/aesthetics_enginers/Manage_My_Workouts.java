package com.example.aesthetics_enginers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aesthetics_enginers.Interfaces.Ex_RecycleViewInterface;
import com.example.aesthetics_enginers.Interfaces.Manage_Workout_Interface;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.RecycleViews.Exercise_Guide_Full_Page;
import com.example.aesthetics_enginers.RecycleViews.Exercise_Guide_RecycleView;
import com.example.aesthetics_enginers.User_Account.Login;
import com.example.aesthetics_enginers.Utility.RecycleViewAdapter_Manage_Workouts;
import com.example.aesthetics_enginers.Utility.RecycleViewAdapter_Workout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Manage_My_Workouts extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Manage_Workout_Interface {
    private DrawerLayout drawer;
    private FirebaseFirestore mDb;
    private ArrayList<Workout> exercisesArrayList;
    private RecycleViewAdapter_Manage_Workouts adapter;
    private View WorkoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__my__workouts);
        //Changing the title of the action bar
        this.setTitle("Manage Workouts");

        RecyclerView recyclerView = findViewById(R.id.RecycleView_Manage_Workout);
        //setUpExersizeModelsNeck();
        DatabaseEventChangeListener();
        adapter = new RecycleViewAdapter_Manage_Workouts(this, exercisesArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        //Nav Menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }


    /*
         Navigation Menu
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dashboard:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
            case R.id.workouts:
                startActivity(new Intent(getApplicationContext(),Workouts.class));
                finish();
                break;

            case R.id.exersice_guide:
                startActivity(new Intent(getApplicationContext(),Exercise_Guide.class));
                finish();
                break;

            case R.id.Manage_My_Workouts:
                startActivity(new Intent(getApplicationContext(),Manage_My_Workouts.class));
                finish();
                break;

            case R.id.Workout_Log:
                startActivity(new Intent(getApplicationContext(),Workout_Log.class));
                finish();
                break;

            case R.id.Sign_Out:
                signOut();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public void OnWorkoutDelete(int position) {
        // Delete the workout plan from the user_Workouts collection
        String Title = exercisesArrayList.get(position).getTitle();// this will be use to identify the collection on the database


    }

    @Override
    public void OnWorkoutReset(int position) {
        // Reset the workout plan
        String Title = exercisesArrayList.get(position).getTitle();  //this will be used to identify the collection on the database




    }





    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
