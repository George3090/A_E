package com.example.aesthetics_enginers.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Interfaces.Slide_Adapter_Interface;
import com.example.aesthetics_enginers.Models.Days;
import com.example.aesthetics_enginers.Models.Schedule;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Days>days;
    private Slide_Adapter_Interface slide_adapter_interface;
    Date currentTime = Calendar.getInstance().getTime();
    String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

    public SlideAdapter(Context context, ArrayList<Days>days,Slide_Adapter_Interface slide_adapter_interface){
        this.context = context;
        this.days = days;
        this.slide_adapter_interface = slide_adapter_interface;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return days.size();
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
        TextView description = (TextView) view.findViewById(R.id.text_description1512);
        TextView Day_Title = (TextView) view.findViewById(R.id.textView9);
        Button Continue_Workout = (Button) view.findViewById(R.id.button_continue);
        Button Mark_Complete = (Button) view.findViewById(R.id.Slide_button);


        //getting Date improve to get date once a day
        Date currentTime = Calendar.getInstance().getTime();
        String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        //Set Data
        texttitle.setText("Let's get started");
        //texttitle.setText(lst_title[position]);
//        description.setText(formatedDate);
//        Days test = workouts.get(position).getSchedule().getDays();
//        String test3 = test.getDay_Image();
//        //Setting Day_Image
//        String url = workouts.get(position).getMain_Image();
//        Glide.with(context).load(test3).into(imageslide);
        description.setText(formatedDate);
         Day_Title.setText(days.get(position).getDay_Title());
         String url = days.get(position).getDay_Image();
         Glide.with(context).load(url).into(imageslide);


        Continue_Workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slide_adapter_interface != null){
                    int pos = position;
                    if(pos != RecyclerView.NO_POSITION){
                        slide_adapter_interface.OnStartWorkout(pos);
                    }
                }

            }
        });

        Mark_Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}


