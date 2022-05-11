package com.example.aesthetics_enginers.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Interfaces.Ex_RecycleViewInterface;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Workout>workouts;
    private Ex_RecycleViewInterface ex_recycleViewInterface;
    Date currentTime = Calendar.getInstance().getTime();
    String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

    public SlideAdapter(Context context, ArrayList<Workout> workouts,Ex_RecycleViewInterface ex_recycleViewInterface){
        this.context = context;
        this.workouts = workouts;
        this.ex_recycleViewInterface = ex_recycleViewInterface;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return workouts.size();
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view== object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       // inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        //View
        LinearLayout layoutslide = view.findViewById(R.id.slide_linear_layout);
        ImageView imageslide = (ImageView) view.findViewById(R.id.slide_image);
        TextView texttitle = (TextView) view.findViewById(R.id.text_title);
        TextView description = (TextView) view.findViewById(R.id.text_description);

        //getting Date
        Date currentTime = Calendar.getInstance().getTime();
        String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        //Set Data
        texttitle.setText("Let's get started");
        //texttitle.setText(lst_title[position]);
        description.setText(formatedDate);

        String url = workouts.get(position).getMain_Image();
        Glide.with(context).load(url).into(imageslide);




        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}


