package com.example.aesthetics_enginers.Utility;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.aesthetics_enginers.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images={
            R.mipmap.ic_launcher_foreground,
            R.drawable.ic_mail


    };

    // List of titles
    public String[] lst_title ={
            "Test1",
            "Test2"

    };

    // List of description
    public String[] lst_description = {
            "test1",
            "test2"

    };

    // List of backgroun colors
    public int[] lst_backgroungcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89)

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
        layoutslide.setBackgroundColor(lst_backgroungcolor[position]);
        imageslide.setImageResource(lst_images[position]);
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
