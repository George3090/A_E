package com.example.aesthetics_enginers.Models;

public class Sets {
    String Reps;
    String Weight;

    public Sets(String reps, String weight) {
        Reps = reps;
        Weight = weight;
    }

    public Sets() {
    }

    public String getReps() {
        return Reps;
    }

    public void setReps(String reps) {
        Reps = reps;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
