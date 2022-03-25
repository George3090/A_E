package com.example.aesthetics_enginers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.aesthetics_enginers.User_Account.Login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Exercise_Guide extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise__guide);
        //Changing the title of the action bar
        this.setTitle("Workouts");

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

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }}
