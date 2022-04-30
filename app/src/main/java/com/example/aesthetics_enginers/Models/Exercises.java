package com.example.aesthetics_enginers.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercises implements Parcelable {

    private String Title;
    private String Muscle;
    private String Equipment;
    private String Instruction;
    private String Image_1;
    private String Image_2;
    private String Video;

    public Exercises(String Title,String Muscle, String Equipment,String Instruction, String Image_1, String Image_2, String Video ){
        this.Title = Title;
        this.Muscle = Muscle;
        this.Equipment = Equipment;
        this.Instruction = Instruction;
        this.Image_1 = Image_1;
        this.Image_2 = Image_2;
        this.Video = Video;
    }

    public Exercises(){

    }


    protected Exercises(Parcel in) {
        Title = in.readString();
        Muscle = in.readString();
        Equipment = in.readString();
        Instruction = in.readString();
        Image_1 = in.readString();
        Image_2 = in.readString();
        Video = in.readString();
    }

    public static final Creator<Exercises> CREATOR = new Creator<Exercises>() {
        @Override
        public Exercises createFromParcel(Parcel in) {
            return new Exercises(in);
        }

        @Override
        public Exercises[] newArray(int size) {
            return new Exercises[size];
        }
    };

    public static Creator<Exercises> getCREATOR() {
        return CREATOR;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String Muscle) { this.Title = Title; }

    public String getMuscle() {
        return Muscle;
    }
    public void setMuscle(String Muscle) {
        this.Muscle = Muscle;
    }

    public String getEquipment() {
        return Equipment;
    }
    public void setEquipment(String Equipment) {
        this.Equipment = Equipment;
    }

    public String getInstruction() {
        return Instruction;
    }
    public void setInstruction(String Instruction) {
        this.Instruction = Instruction;
    }

    public String getImage_1() {
        return Image_1;
    }
    public void setImage_1(String Image_1) {
        this.Image_1 = Image_1;
    }

    public String getImage_2() {
        return Image_2;
    }
    public void setImage_2(String Image_2) {
        this.Image_2 = Image_2;
    }

    public String getVideo() {
        return Video;
    }
    public void setVideo(String Muscle) {
        this.Video = Video;
    }


    @Override
    public String toString() {
        return "Exercises{" +
                "Title='" + Title + '\'' +
                ", Muscle='" + Muscle + '\'' +
                ", Equipment='" + Equipment + '\'' +
                ", Instruction='" + Instruction + '\'' +
                ", Image_1='" + Image_1 + '\''+
                ", Image_2='" + Image_2 + '\''+
                ", Video='" + Video + '\''+
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Muscle);
        dest.writeString(Equipment);
        dest.writeString(Instruction);
        dest.writeString(Image_1);
        dest.writeString(Image_2);
        dest.writeString(Video);
    }
}
