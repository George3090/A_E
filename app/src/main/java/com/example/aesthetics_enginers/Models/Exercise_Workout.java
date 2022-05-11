package com.example.aesthetics_enginers.Models;

import java.util.ArrayList;
import java.util.Map;

public class Exercise_Workout {
    Boolean Completed;
    String Ex_ID;
    ArrayList<Sets> sets;
    Map<String, Sets> setsMap;

    public Exercise_Workout(Boolean completed, String ex_ID, ArrayList<Sets> sets, Map<String, Sets> setsMap) {
        Completed = completed;
        Ex_ID = ex_ID;
        this.sets = sets;
        this.setsMap = setsMap;
    }

    public Exercise_Workout() {
    }


    public Boolean getCompleted() {
        return Completed;
    }

    public void setCompleted(Boolean completed) {
        Completed = completed;
    }

    public String getEx_ID() {
        return Ex_ID;
    }

    public void setEx_ID(String ex_ID) {
        Ex_ID = ex_ID;
    }

    public ArrayList<Sets> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Sets> sets) {
        this.sets = sets;
    }

    public Map<String, Sets> getSetsMap() {
        return setsMap;
    }

    public void setSetsMap(Map<String, Sets> setsMap) {
        this.setsMap = setsMap;
    }
//    public Exercise_Workout(Boolean completed, String ex_ID, ArrayList<Sets> sets) {
//        Completed = completed;
//        Ex_ID = ex_ID;
//        this.sets = sets;
//    }
//
//    public Exercise_Workout() {
//    }
//
//    public Boolean getCompleted() {
//        return Completed;
//    }
//
//    public void setCompleted(Boolean completed) {
//        Completed = completed;
//    }
//
//    public String getEx_ID() {
//        return Ex_ID;
//    }
//
//    public void setEx_ID(String ex_ID) {
//        Ex_ID = ex_ID;
//    }
//
//    public ArrayList<Sets> getSets() {
//        return sets;
//    }
//
//    public void setSets(ArrayList<Sets> sets) {
//        this.sets = sets;
//    }
}
