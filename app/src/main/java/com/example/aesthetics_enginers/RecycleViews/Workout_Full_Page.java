package com.example.aesthetics_enginers.RecycleViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Workout_Full_Page extends AppCompatActivity {
    private ArrayList<Workout> exercisesArrayList;
    private FirebaseFirestore mDb;
    private String Title;
    private String doc_ID;
    Task<QuerySnapshot> snapshotTask;
    private Boolean workout_Exists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__full__page);


        Title = getIntent().getStringExtra("Title");
        String MainImage = getIntent().getStringExtra("Image_Main");
        String Img1 = getIntent().getStringExtra("Img1");
        String Img2 = getIntent().getStringExtra("Img2");
        //String MainImage = getIntent().getStringExtra("Image_Main");


        TextView titleTextView = findViewById(R.id.Title_Workout);
        TextView textViewDuration = findViewById(R.id.textViewDuration);
        TextView textView12 = findViewById(R.id.textView12);
        ImageView imgView_Main = findViewById(R.id.imageView12);
        ImageView imgView1 = findViewById(R.id.imageView7);
        ImageView imgView2 = findViewById(R.id.imageView6);


        titleTextView.setText(Title);
        Glide.with(this).load(MainImage).into(imgView_Main);
        Glide.with(this).load(MainImage).into(imgView1);
        Glide.with(this).load(MainImage).into(imgView2);


        // Adding the workout to the user_Workouts collections
        Button Start_Plan = findViewById(R.id.buttonStartWorkout);
        Start_Plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the Logic for adding the workout
                DatabaseEventChangeListener();


            }
        });


    }

    private void DatabaseEventChangeListener(){
        mDb = FirebaseFirestore.getInstance();
        //exercisesArrayList = new ArrayList<>();
        mDb.collection("collection_workout").whereEqualTo("Title", Title)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                //TODO find  how to store only one instance of the workout in the collection users
                                QueryDocumentSnapshot document = dc.getDocument();
                                doc_ID = dc.getDocument().getId();
                                String user_ID = "M2nfGjb7KLcyOw5WeC7vB84gTFN2";
//                               // getUserWorkouts();
                                Map<String, Object> workout = new HashMap<>();
                                Map<String, Object> docData = new HashMap<>();
                                workout = dc.getDocument().getData();
                               // docData.put(doc_ID, workout);
                                mDb.collection("collection_users")
                                        .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
                                        .collection("User_Workouts")
                                        .document(doc_ID)
                                        .set(workout);

                               // startActivity(new Intent(getApplicationContext(), MainActivity.class));


                            }

                        }
                    }
                    });

    }



//    public void getUserWorkouts() {
//        //mDb = FirebaseFirestore.getInstance();
//        CollectionReference collectionReference = mDb.collection("collection_users")
//                .document("M2nfGjb7KLcyOw5WeC7vB84gTFN2")
//                .collection("workouts");
//
//        collectionReference.whereEqualTo(doc_ID, true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        if (document.exists()) {
//                            workout_Exists = true;
//                        } else {
//                            workout_Exists = false;
//                        }
//
//                    }
//                }
//            }
//
//            //Toast.makeText(Workout_Full_Page.this, snapshotTask.toString() ,Toast.LENGTH_SHORT).show();
//        });
//    }
}