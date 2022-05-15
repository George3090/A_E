package com.example.aesthetics_enginers.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aesthetics_enginers.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class Profile_BodyFat extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View graphView1 = inflater.inflate(R.layout.fragment_profile__body_fat, container, false);
        LineChart chart_body_Fat = (LineChart) graphView1.findViewById(R.id.LineChartBody);

        ArrayList<Entry> Y_Body_Fat = new ArrayList<>();
        Y_Body_Fat.add(new Entry(0, 60f));
        Y_Body_Fat.add(new Entry(1, 50f));
        Y_Body_Fat.add(new Entry(2, 70f));
        Y_Body_Fat.add(new Entry(3, 78f));
        Y_Body_Fat.add(new Entry(4, 75f));
        Y_Body_Fat.add(new Entry(5, 80f));

        LineDataSet weight_set = new LineDataSet(Y_Body_Fat, "Weight");
        weight_set.setFillAlpha(110);
        weight_set.setColor(Color.BLACK);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(weight_set);
        LineData data = new LineData(dataSets);
        chart_body_Fat.setData(data);


        return graphView1;
    }

}