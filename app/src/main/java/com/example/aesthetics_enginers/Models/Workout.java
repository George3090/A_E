package com.example.aesthetics_enginers.Models;

public class Workout {
    String Title;
    String Main_Image;
    String Duration;
    String Experience_Level;
    String Descriptio;
    String Img1;
    String Img2;
    String img3;

    public Workout(String title, String main_Image, String duration, String experience_Level, String descriptio, String img1, String img2, String img3) {
        Title = title;
        Main_Image = main_Image;
        Duration = duration;
        Experience_Level = experience_Level;
        Descriptio = descriptio;
        Img1 = img1;
        Img2 = img2;
        this.img3 = img3;
    }


    public Workout(){

    }

    public String getTitle() {
        return Title;
    }

    public String getMain_Image() {
        return Main_Image;
    }

    public String getDuration() {
        return Duration;
    }

    public String getExperience_Level() {
        return Experience_Level;
    }

    public String getDescriptio() {
        return Descriptio;
    }

    public String getImg1() {
        return Img1;
    }

    public String getImg2() {
        return Img2;
    }

    public String getImg3() {
        return img3;
    }
}
