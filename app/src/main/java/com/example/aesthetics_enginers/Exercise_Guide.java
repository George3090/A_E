package com.example.aesthetics_enginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Fragments.Exercise_Guide_Back;
import com.example.aesthetics_enginers.Fragments.Exercise_Guide_Front;
import com.example.aesthetics_enginers.User_Account.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.aesthetics_enginers.Models.Exercises;


public class Exercise_Guide extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private FirebaseFirestore mDb;
    private DocumentReference docRef;
    private TextView textView1;
    private Exercises Extest;
    private ImageView Imgview1;
    private Switch switchButon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise__guide);
        //Changing the title of the action bar
        this.setTitle("Exercise Guide");

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


        //Images
        //Imgview1 = findViewById(R.id.imageView1);
        switchButon = findViewById(R.id.switch1);


        switchButon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, Exercise_Guide_Back.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name") // name can be null
                            .commit();

                }
                else {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, Exercise_Guide_Front.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name") // name can be null
                            .commit();
                }
            }
        });





        mDb = FirebaseFirestore.getInstance();
        docRef = mDb.collection("Exercises").document("823naq9iMzKHaaDubBLG");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        // Do something with the document



                    }
                    else {
                        //Log.d(TAG, "No such document");
                    }
                }
                else {
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });




    }



    private void addView(){}













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

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }}
