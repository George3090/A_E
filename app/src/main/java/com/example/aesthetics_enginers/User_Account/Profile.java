package com.example.aesthetics_enginers.User_Account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Exercise_Guide;
import com.example.aesthetics_enginers.Fragments.Exercise_Guide_Back;
import com.example.aesthetics_enginers.Fragments.Profile_BodyFat;
import com.example.aesthetics_enginers.Fragments.Profile_Weight;
import com.example.aesthetics_enginers.MainActivity;
import com.example.aesthetics_enginers.Manage_My_Workouts;
import com.example.aesthetics_enginers.Models.User;
import com.example.aesthetics_enginers.R;
import com.example.aesthetics_enginers.Utility.SlideAdapter;
import com.example.aesthetics_enginers.Workout_Log;
import com.example.aesthetics_enginers.Workouts;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView mUserName, mGender;
    private ImageView mProfileImage;
    private FirebaseFirestore mDb;
    private DrawerLayout drawer;
    private TextView Nav_UserName;
    private TextView Nav_UserDetail;
    private ImageView Nav_UserImage;
    private Button Weight, BodyFat,Height, WorkoutHistory;
    private Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Gets UserData and sets the views
        getUserDetails();

        // Buttons

        WorkoutHistory = findViewById(R.id.buttonWorkoutHistory);
        Weight = findViewById(R.id.buttonWeight);
        BodyFat = findViewById(R.id.buttonBodyFat);
        Height = findViewById(R.id.buttonHeight);
        drawable = getResources().getDrawable(R.drawable.button_profile);
        //need to set the fragments dynamically to overcom graph prohlemst
         Weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetFragmentWeight();
            }
        });

        BodyFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetFragmentBodyFatChart();

            }
        });


        Height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        WorkoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }


    private void SetFragmentWeight(){
        Weight.setTextColor(Color.BLACK);
        Weight.setBackgroundColor(Color.WHITE);
        BodyFat.setBackground(drawable);
        BodyFat.setTextColor(Color.WHITE);
        //replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Profile_Weight profile_weight = new Profile_Weight();
        fragmentTransaction.replace(R.id.fragment3, Profile_Weight.class, null);
        fragmentTransaction.setReorderingAllowed(false);
        fragmentTransaction.addToBackStack(null);// name can be null
        fragmentTransaction.commit();

    }

    private void SetFragmentBodyFatChart(){
        BodyFat.setBackgroundColor(Color.WHITE);
        BodyFat.setTextColor(Color.BLACK);
        Weight.setBackground(drawable);
        Weight.setTextColor(Color.WHITE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Profile_BodyFat profile_bodyFat = new Profile_BodyFat();
        fragmentTransaction.replace(R.id.fragment3, Profile_BodyFat.class, null);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null); // name can be null
        fragmentTransaction.commit();

    }



    private void SetProfileData(User user){
        //getting the views
        mUserName = findViewById(R.id.textViewUserName);
        mGender = findViewById(R.id.textView10);
        mProfileImage = findViewById(R.id.imageViewProfilePic);

        //setting the views
        if(user != null){
            mUserName.setText(user.getFullName());
            mGender.setText("Age: "+user.getAge());
            //Prfile Picture
            Glide.with(this).load(user.getProfile_Photo_uri()).into(mProfileImage);
        }

    }

    private void set_nav_menu(User user){
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
        Nav_UserDetail = header.findViewById(R.id.Nav_UserDetails);
        if(user != null){
            Nav_UserName.setText(user.getFullName());
            Nav_UserDetail.setText(user.getAge());
            //Prfile Picture
            Glide.with(this).load(user.getProfile_Photo_uri()).into(Nav_UserImage);
        }
    }



    private void getUserDetails() {
        mDb = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getUid();
        //Toast.makeText(MainActivity.this, userID , Toast.LENGTH_SHORT).show();
        mDb.collection("collection_users")
                .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                        if (snapshot != null && snapshot.exists()) {
                            //Log.d(TAG, "Current data: " + snapshot.getData());
                            User user = snapshot.toObject(User.class);
                            SetProfileData(user);
                            set_nav_menu(user);
                        } else {
                            //Log.d(TAG, "Current data: null");
                        }

                    }

                });

    }

    /*
    Navigation Menu this will be redundant due to errors occurring when implementing a more OOP approach
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dashboard:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            case R.id.workouts:
                startActivity(new Intent(getApplicationContext(), Workouts.class));
                finish();
                break;

            case R.id.exersice_guide:
                startActivity(new Intent(getApplicationContext(), Exercise_Guide.class));
                finish();
                break;

            case R.id.Manage_My_Workouts:
                startActivity(new Intent(getApplicationContext(), Manage_My_Workouts.class));
                finish();
                break;

            case R.id.Workout_Log:
                startActivity(new Intent(getApplicationContext(), Workout_Log.class));
                finish();
                break;

            case R.id.My_Profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
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
    }


}