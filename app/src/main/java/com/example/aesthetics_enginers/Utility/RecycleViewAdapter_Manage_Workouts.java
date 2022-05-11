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
import com.example.aesthetics_enginers.Interfaces.Manage_Workout_Interface;
import com.example.aesthetics_enginers.Models.Workout;
import com.example.aesthetics_enginers.R;

import java.util.ArrayList;


public class RecycleViewAdapter_Manage_Workouts extends RecyclerView.Adapter<RecycleViewAdapter_Manage_Workouts.MyViewHolder>{

    private final Manage_Workout_Interface manage_workout_interface;
    Context context;
    ArrayList<Workout> workouts;

    public RecycleViewAdapter_Manage_Workouts(Context context,  ArrayList<Workout> workouts, Manage_Workout_Interface manage_workout_interface){
        this.context = context;
        this.workouts = workouts;
        this.manage_workout_interface = manage_workout_interface;
    }


    @NonNull
    @Override
    public RecycleViewAdapter_Manage_Workouts.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where I inflate the layout (giving a loog to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_manage_my_workouts, parent, false);
        return new RecycleViewAdapter_Manage_Workouts.MyViewHolder(view,manage_workout_interface );
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter_Manage_Workouts.MyViewHolder holder, int position) {
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
        String Title;
        public MyViewHolder(@NonNull View itemView, Manage_Workout_Interface manage_workout_interface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView5);
            textViewTitle = itemView.findViewById(R.id.Text_View_Title);
            textviewSubHeading = itemView.findViewById(R.id.textView5);
            textView3 = itemView.findViewById(R.id.textView4);
            itemView.findViewById(R.id.button_Remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove The workout from the User_Workout table

                    if(manage_workout_interface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            manage_workout_interface.OnWorkoutDelete(pos);
                        }
                    }
                }
            });


            itemView.findViewById(R.id.button_Reset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset The workout progrees to null
                    if(manage_workout_interface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            manage_workout_interface.OnWorkoutReset(pos);
                        }
                    }
                }
            });
        }
    }

}



