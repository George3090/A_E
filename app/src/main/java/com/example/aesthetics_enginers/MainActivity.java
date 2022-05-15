package com.example.aesthetics_enginers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Interfaces.Slide_Adapter_Interface;
import com.example.aesthetics_enginers.Models.Days;
import com.example.aesthetics_enginers.Models.Schedule;
import com.example.aesthetics_enginers.Models.User;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.RecycleViews.Workout_Full_Page;
import com.example.aesthetics_enginers.User_Account.Login;
import com.example.aesthetics_enginers.User_Account.Profile;
import com.example.aesthetics_enginers.User_Workout_Tracking.Day_Overview_FullPage;
import com.example.aesthetics_enginers.Utility.SlideAdapter;
import com.example.aesthetics_enginers.Utility.UserClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Slide_Adapter_Interface {
    //Firebase Database
    private FirebaseFirestore mDb;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private User CurrentUser;
    private TextView[] mDots;
    // UI
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    private static final String TAG = "ViewDatabase";
    private TextView Nav_UserName;
    private TextView Nav_UserDetail;
    private ImageView Nav_UserImage;
    private Button button_seeAllWorkouts;
    private ArrayList<Days> exercisesArrayList;


    private SlideAdapter adapter;
    private LinearLayout mDotLayout;

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mDb = FirebaseFirestore.getInstance();

        CurrentUser = ((UserClient)(getApplicationContext())).getUser();
        //SLider
        DatabaseEventChangeListener();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new SlideAdapter(this,exercisesArrayList, this);
        viewPager.setAdapter(adapter);

        mDotLayout = findViewById(R.id.DotsLayout);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);


        getUserDetails();
        //setUser();
        //set_nav_menu();
//        Toast.makeText(MainActivity.this,  CurrentUser.toString() , Toast.LENGTH_SHORT).show();
        //Button

        mDb = FirebaseFirestore.getInstance();
        button_seeAllWorkouts = findViewById(R.id.button_seeAllWorkouts);
        button_seeAllWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Workouts.class));
            }
        });


        Toast.makeText(MainActivity.this, exercisesArrayList.toString(),Toast.LENGTH_SHORT).show();

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
        String userID = FirebaseAuth.getInstance().getUid();
        //Toast.makeText(MainActivity.this, userID , Toast.LENGTH_SHORT).show();
        mDb.collection("collection_users")
                .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                        if (snapshot != null && snapshot.exists()) {
                            Log.d(TAG, "Current data: " + snapshot.getData());
                            User user = snapshot.toObject(User.class);
                            set_nav_menu(user);
                        } else {
                            Log.d(TAG, "Current data: null");
                        }

                    }

                });

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




    private void DatabaseEventChangeListener(){
        exercisesArrayList = new ArrayList<>();
        mDb = FirebaseFirestore.getInstance();
        mDb.collection("collection_users")
                .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
                .collection("User_Workouts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){

                                Workout workout = dc.getDocument().toObject(Workout.class);
                                Days days = workout.getSchedule().getDays();
                                exercisesArrayList.add(days);
                                String workoutid = dc.getDocument().getId();
                                dc.getDocument().getData();

                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
    }



//    private void DatabaseEventChangeListenerDay(){
//        DayArrayList = new ArrayList<>();
//        mDb.collection("collection_users")
//                .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
//                .collection("User_Workouts")
//                .document(
//
//
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if(error != null){
//                            Log.e("Firestore error",error.getMessage());
//                            return;
//                        }
//                        for(DocumentChange dc : value.getDocumentChanges()){
//                            if(dc.getType() == DocumentChange.Type.ADDED){
//
//                                exercisesArrayList.add(dc.getDocument().toObject(Workout.class));
//                            }
//                            adapter.notifyDataSetChanged();
//                        }
//
//                    }
//                });
//    }




//    @Override
//    public void OnItemClicked(int position) {
////        Intent intent = new Intent( MainActivity.this , Workout_Full_Page.class); //change MainActivity to added activity
////        intent.putExtra("Title", exercisesArrayList.get(position).getTitle());
////        intent.putExtra("Image_Main", exercisesArrayList.get(position).getMain_Image());
////        intent.putExtra("Img1", exercisesArrayList.get(position).getImg1());
////        intent.putExtra("Img2", exercisesArrayList.get(position).getImg2());
////        startActivity(intent);
//    }



    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private void addDotsIndicator(int position){
        int indicator = exercisesArrayList.size();
        mDots = new TextView[indicator];
        mDotLayout.removeAllViewsInLayout();
        for(int i = 0; i< mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.black));

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.red));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
                addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
            CurrentUser = ((UserClient)(getApplicationContext())).getUser();
}


    @Override
    public void OnStartWorkout(int position) {
        Intent intent = new Intent( MainActivity.this , Day_Overview_FullPage.class); //change MainActivity to added activity
        intent.putExtra("Title", exercisesArrayList.get(position).getDay_Title());
        intent.putExtra("Image_Main", exercisesArrayList.get(position).getDay_Image());
        //intent.putExtra("Day_Description", exercisesArrayList.get(position).getDay_Description());


        startActivity(intent);

    }

    @Override
    public void OnMarkWorkoutCoompleted(int position) {


    }
}

//
//    private void Data_query_test(){
//        mDb.collection("collection_users")
//                .document("slvLwlRNJMM5U6RFufBKYk7A0Ft1")
//                .collection("collection_user_workouts")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for (QueryDocumentSnapshot document : task.getResult()){
//                                Log.d(TAG, document.getId() + "=>" +document.getData());
//                                // Toast.makeText(MainActivity.this, document.getId() ,Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                            Toast.makeText(MainActivity.this,"Error getting documents: " ,Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }



//    private void setUser(){
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .build();
//        db.setFirestoreSettings(settings);
//        DocumentReference userRef = db.collection("collection_users")
//                .document(user.getUid());
//        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    Log.d(TAG, "onComplete: successfully set the user client.");
//                    User user = task.getResult().toObject(User.class);
//                    ((UserClient)(getApplicationContext())).setUser(user);
//                }
//            }
//        });
//
//    }.

