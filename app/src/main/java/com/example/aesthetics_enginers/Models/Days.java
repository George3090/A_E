package com.example.aesthetics_enginers.Models;

public class Days {

    Boolean Completed;
    String Day_Description;
    String Day_Image;
    String Day_Nr;
    String Day_Title;
    Plan plan;

    public Days(Boolean completed, String day_Description, String day_Image, String day_Nr, String day_Title, Plan plan) {
        Completed = completed;
        Day_Description = day_Description;
        Day_Image = day_Image;
        Day_Nr = day_Nr;
        Day_Title = day_Title;
        this.plan = plan;
    }


    public Days() {
    }

    public Boolean getCompleted() {
        return Completed;
    }

    public void setCompleted(Boolean completed) {
        Completed = completed;
    }

    public String getDay_Description() {
        return Day_Description;
    }

    public void setDay_Description(String day_Description) {
        Day_Description = day_Description;
    }

    public String getDay_Image() {
        return Day_Image;
    }

    public void setDay_Image(String day_Image) {
        Day_Image = day_Image;
    }

    public String getDay_Nr() {
        return Day_Nr;
    }

    public void setDay_Nr(String day_Nr) {
        Day_Nr = day_Nr;
    }

    public String getDay_Title() {
        return Day_Title;
    }

    public void setDay_Title(String day_Title) {
        Day_Title = day_Title;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Days{" +
                "Completed=" + Completed +
                ", Day_Description='" + Day_Description + '\'' +
                ", Day_Image='" + Day_Image + '\'' +
                ", Day_Nr='" + Day_Nr + '\'' +
                ", Day_Title='" + Day_Title + '\'' +
                ", plan=" + plan +
                '}';
    }
}
