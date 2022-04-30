package com.example.aesthetics_enginers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.aesthetics_enginers.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Exercise_Guide_Back#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Exercise_Guide_Back extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView Imgview1;

    public Exercise_Guide_Back() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Exercise_Guide_Back.
     */
    // TODO: Rename and change types and number of parameters
    public static Exercise_Guide_Back newInstance(String param1, String param2) {
        Exercise_Guide_Back fragment = new Exercise_Guide_Back();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_exercise__guide__back, container, false);
        Imgview1 = (ImageView) view.findViewById(R.id.Exersice_Guide_Back);
        Glide.with(Imgview1).load("https://firebasestorage.googleapis.com/v0/b/aestheticsenginering.appspot.com/o/Exercises%2FExerciseFilterBack.jpeg?alt=media&token=d532fac8-b031-4f58-a582-092de1befe2b").into(Imgview1);
        return view;

    }
}