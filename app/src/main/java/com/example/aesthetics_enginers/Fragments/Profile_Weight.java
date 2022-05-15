package com.example.aesthetics_enginers.Fragments;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.aesthetics_enginers.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.Map;


public class Profile_Weight extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View graphView = inflater.inflate(R.layout.fragment_profile__weight, container, false);
        LineChart mchart = (LineChart) graphView.findViewById(R.id.LineChart);

        ArrayList<Entry> Y_Weight = new ArrayList<>();
        Y_Weight.add(new Entry(0, 15f));
        Y_Weight.add(new Entry(1, 14f));
        Y_Weight.add(new Entry(2, 14f));
        Y_Weight.add(new Entry(3, 16f));
        Y_Weight.add(new Entry(4, 13f));
        Y_Weight.add(new Entry(5, 12f));

        LineDataSet weight_set = new LineDataSet(Y_Weight, "Weight");

        weight_set.setFillAlpha(110);
        weight_set.setColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets =  new ArrayList<>();
        dataSets.add(weight_set);

        LineData data = new LineData(dataSets);


        Legend legend = mchart.getLegend();

        mchart.setData(data);
        mchart.invalidate(); // refresh

        return graphView;
    }


}