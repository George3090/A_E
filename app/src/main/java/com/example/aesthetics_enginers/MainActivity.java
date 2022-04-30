package com.example.aesthetics_enginers;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.ActionBar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Models.User;
import com.example.aesthetics_enginers.User_Account.Login;
import com.example.aesthetics_enginers.Utility.SlideAdapter;
import com.example.aesthetics_enginers.Utility.UserClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Firebase Database
    private FirebaseFirestore mDb;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private User CurrentUser;



    // UI
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    private static final String TAG = "ViewDatabase";
    private TextView Nav_UserName;
    private TextView Nav_UserDetail;
    private ImageView Nav_UserImage;
    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = FirebaseFirestore.getInstance();
        CurrentUser = ((UserClient)(getApplicationContext())).getUser();
        //SLider
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);



        //Changing the title of the action bar
        this.setTitle("Dashboard");

        getUserDetails();
        setUser();
        set_nav_menu();
        Data_query_test();

    }

/*
Testing quearing subcolleciton
 */
    private void Data_query_test(){
        mDb.collection("collection_users")
                .document("slvLwlRNJMM5U6RFufBKYk7A0Ft1")
                .collection("collection_user_workouts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                 Log.d(TAG, document.getId() + "=>" +document.getData());
                                Toast.makeText(MainActivity.this, document.getId() ,Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(MainActivity.this,"Error getting documents: " ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void set_nav_menu(){
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
        View header= navigationView.getHeaderView(0);
        Nav_UserName = header.findViewById(R.id.Nav_UserName);
        Nav_UserImage = header.findViewById(R.id.Nav_UserImage);
        //User user = ((UserClient)(getApplicationContext())).getUser();
        Nav_UserName.setText(CurrentUser.getFullName());
        Nav_UserDetail = header.findViewById(R.id.Nav_UserDetails);
        Nav_UserDetail.setText(CurrentUser.getAge());
        //Prfile Picture
        Glide.with(this).load(CurrentUser.getProfile_Photo_uri()).into(Nav_UserImage);
    }



    private void setUser(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .build();
        db.setFirestoreSettings(settings);
        DocumentReference userRef = db.collection("collection_users")
                .document(user.getUid());
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "onComplete: successfully set the user client.");
                    User user = task.getResult().toObject(User.class);
                    ((UserClient)(getApplicationContext())).setUser(user);
                }
            }
        });

    }




    private void getUserDetails() {
        if (CurrentUser == null) {
            CurrentUser = new User();
            DocumentReference userRef = mDb.collection("collection_users").
                    document(FirebaseAuth.getInstance().getUid());
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        User user = task.getResult().toObject(User.class);
                        ((UserClient) getApplicationContext()).setUser(user);
                        Toast.makeText(MainActivity.this, "merger " , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

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
        Navigation Menu this will be redundant due to errors occurring when implementing a more OOP approach
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


