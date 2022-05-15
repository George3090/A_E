package com.example.aesthetics_enginers.Models;

import com.google.firebase.firestore.PropertyName;

import java.util.Map;

import kotlinx.coroutines.SchedulerTaskKt;

public class Workout {
    @PropertyName("Title")
    String Title;
    String Main_Image;
    String Duration;
    String Experience_Level;
    String Description;
    String Img1;
    String Img2;
    String img3;
    @PropertyName("Schedule")
    Schedule schedule;


    public Workout(String title, String main_Image, String duration, String experience_Level, String description, String img1, String img2, String img3, Schedule schedule) {
        Title = title;
        Main_Image = main_Image;
        Duration = duration;
        Experience_Level = experience_Level;
        Description = description;
        Img1 = img1;
        Img2 = img2;
        this.img3 = img3;
        this.schedule = schedule;
    }

    public Workout(){

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMain_Image() {
        return Main_Image;
    }

    public void setMain_Image(String main_Image) {
        Main_Image = main_Image;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getExperience_Level() {
        return Experience_Level;
    }

    public void setExperience_Level(String experience_Level) {
        Experience_Level = experience_Level;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "Title='" + Title + '\'' +
                ", Main_Image='" + Main_Image + '\'' +
                ", Duration='" + Duration + '\'' +
                ", Experience_Level='" + Experience_Level + '\'' +
                ", Description='" + Description + '\'' +
                ", Img1='" + Img1 + '\'' +
                ", Img2='" + Img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
