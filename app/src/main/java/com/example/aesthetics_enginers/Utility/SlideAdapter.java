package com.example.aesthetics_enginers.Utility;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Models.User;
import com.example.aesthetics_enginers.R;
import com.example.aesthetics_enginers.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.aesthetics_enginers.Utility.UserClient;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Date currentTime = Calendar.getInstance().getTime();
    String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
    User CurrentUser;
    private FirebaseAuth mAuth;
    //private FirebaseFirestore mDb;
    FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    //public String[] lst_images_test;
//
//    public String[] lst_images_test = {
//                mDb.collection("collection_users")
//                .document("slvLwlRNJMM5U6RFufBKYk7A0Ft1")
//                .collection("collection_user_workouts")
//                        .document("LlN3QHlKXswIFBeJIfBS")
//                        .
//
//                .get().toString(),
////                CurrentUser.getProfile_Photo_uri(),
//
//                                };




//    // we will get the default FirebaseDatabase instance
//    FirebaseFirestore firebaseDatabase = FirebaseFirestore.getInstance();
//
//    // we will get a DatabaseReference for the database root node
//    DocumentReference databaseReference = FirebaseFirestore.getReference();
//
//    // Here "image" is the child node value we are getting
//    // child node data in the getImage variable
//    DatabaseReference getImage = databaseReference.child("image");
//




    // list of images


    public String[] lst_images={

    };


    // List of titles
    public String[] lst_title ={
            "Let's get started",
            "Let's get started",


    };

    // List of description
    public String[] lst_description = {
            formatedDate,
            formatedDate,

    };

    // Current user workout ID
    public int[] lst_backgroungcolor = {

    };


    public SlideAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = view.findViewById(R.id.slide_linear_layout);
        ImageView imageslide = (ImageView) view.findViewById(R.id.slide_image);
        TextView texttitle = (TextView) view.findViewById(R.id.text_title);
        TextView description = (TextView) view.findViewById(R.id.text_description);
//        layoutslide.setBackgroundColor(lst_backgroungcolor[position]);
        CurrentUser = ((UserClient)(context.getApplicationContext())).getUser();

        if(CurrentUser != null){
            String[] lst_images={
                    CurrentUser.getTemporary(),
                    CurrentUser.getProfile_Photo_uri()
            };


            String url = lst_images[position];
            Glide.with(view).load(url).into(imageslide);}
        //imageslide.setImageResource(lst_images[position]) ;

        texttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
