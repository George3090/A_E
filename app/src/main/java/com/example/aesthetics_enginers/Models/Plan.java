package com.example.aesthetics_enginers.Models;

import java.util.ArrayList;
import java.util.Map;

public class Plan {
    ArrayList<Exercise_Workout> exercise_workouts;
    Map<String,Exercise_Workout> exercise_workoutMap;

    public Plan(ArrayList<Exercise_Workout> exercise_workouts, Map<String, Exercise_Workout> exercise_workoutMap) {
        this.exercise_workouts = exercise_workouts;
        this.exercise_workoutMap = exercise_workoutMap;
    }

    public Plan() {
    }

    public ArrayList<Exercise_Workout> getExercise_workouts() {
        return exercise_workouts;
    }

    public void setExercise_workouts(ArrayList<Exercise_Workout> exercise_workouts) {
        this.exercise_workouts = exercise_workouts;
    }

    public Map<String, Exercise_Workout> getExercise_workoutMap() {
        return exercise_workoutMap;
    }

    public void setExercise_workoutMap(Map<String, Exercise_Workout> exercise_workoutMap) {
        this.exercise_workoutMap = exercise_workoutMap;
    }
//    public Plan(ArrayList<Exercise_Workout> exercise_workouts) {
//        this.exercise_workouts = exercise_workouts;
//    }
//
//    public Plan() {
//    }
//
//    public ArrayList<Exercise_Workout> getExercise_workouts() {
//        return exercise_workouts;
//    }
//
//    public void setExercise_workouts(ArrayList<Exercise_Workout> exercise_workouts) {
//        this.exercise_workouts = exercise_workouts;
//    }
}

