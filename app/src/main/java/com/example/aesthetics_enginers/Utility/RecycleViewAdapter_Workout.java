package com.example.aesthetics_enginers.Utility;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.Interfaces.Ex_RecycleViewInterface;
import com.example.aesthetics_enginers.Models.Exercises;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;

import java.util.ArrayList;

public class RecycleViewAdapter_Workout extends RecyclerView.Adapter<RecycleViewAdapter_Workout.MyViewHolder>{

    private final Ex_RecycleViewInterface ex_recycleViewInterface;
    Context context;
    ArrayList<Workout> workouts;

    public RecycleViewAdapter_Workout(Context context,  ArrayList<Workout> workouts, Ex_RecycleViewInterface ex_recycleViewInterface){
        this.context = context;
        this.workouts = workouts;
        this.ex_recycleViewInterface = ex_recycleViewInterface;
    }


    @NonNull
    @Override
    public RecycleViewAdapter_Workout.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where I inflate the layout (giving a loog to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_workouts, parent, false);

        return new RecycleViewAdapter_Workout.MyViewHolder(view,ex_recycleViewInterface );
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter_Workout.MyViewHolder holder, int position) {
        // assigning values to each of our rows as they come back on the screen based on position of recycleViewer
        holder.textViewTitle.setText(workouts.get(position).getTitle());
        holder.textviewSubHeading.setText(workouts.get(position).getExperience_Level());
        holder.textView3.setText(workouts.get(position).getDuration());
        // String url2= exercises.get(position).getImage_2();
        String url = workouts.get(position).getMain_Image();
        Glide.with(context).load(url).into(holder.imageView); // context may need to be changed to view

    }

    @Override
    public int getItemCount() {
        // the number of items to be displayed
        return workouts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing teh views from our recycle_view_row layouyt file
        // kinda like in the on create method
        ImageView imageView;
        TextView textViewTitle, textviewSubHeading, textView3;
        public MyViewHolder(@NonNull View itemView, Ex_RecycleViewInterface ex_recycleViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView5);
            textViewTitle = itemView.findViewById(R.id.Text_View_Title);
            textviewSubHeading = itemView.findViewById(R.id.textView5);
            textView3 = itemView.findViewById(R.id.textView4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ex_recycleViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            ex_recycleViewInterface.OnItemClicked(pos);
                        }
                    }
                }
            });



        }
    }

}


