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
import com.example.aesthetics_enginers.R;

import java.util.ArrayList;

public class RecycleViewAdapter_Ex extends RecyclerView.Adapter<RecycleViewAdapter_Ex.MyViewHolder> {
    private final Ex_RecycleViewInterface ex_recycleViewInterface;
    Context context;
    ArrayList<Exercises> exercises;

   public RecycleViewAdapter_Ex(Context context,  ArrayList<Exercises> exercises, Ex_RecycleViewInterface ex_recycleViewInterface){
       this.context = context;
       this.exercises = exercises;
       this.ex_recycleViewInterface = ex_recycleViewInterface;


   }

    @NonNull
    @Override
    public RecycleViewAdapter_Ex.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where I inflate the layout (giving a loog to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);

        return new RecycleViewAdapter_Ex.MyViewHolder(view,ex_recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter_Ex.MyViewHolder holder, int position) {
        // assigning values to each of our rows as they come back on the screen based on position of recycleViewer
        holder.textViewTitle.setText(exercises.get(position).getTitle());
        holder.textviewSubHeading.setText(exercises.get(position).getMuscle());
        holder.textView3.setText(exercises.get(position).getEquipment());
       // String url2= exercises.get(position).getImage_2();
        String url = exercises.get(position).getImage_1();
        Glide.with(context).load(url).into(holder.imageView); // context may need to be changed to view

    }

    @Override
    public int getItemCount() {
        // the number of items to be displayed
        return exercises.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing teh views from our recycle_view_row layouyt file
        // kinda like in the on create method
        ImageView imageView;
        TextView textViewTitle, textviewSubHeading, textView3;
        public MyViewHolder(@NonNull View itemView, Ex_RecycleViewInterface ex_recycleViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textViewTitle = itemView.findViewById(R.id.ExTitleView);
            textviewSubHeading = itemView.findViewById(R.id.textViewSubHeading);
            textView3 = itemView.findViewById(R.id.textView3);



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
