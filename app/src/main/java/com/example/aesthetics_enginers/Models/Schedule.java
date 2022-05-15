package com.example.aesthetics_enginers.Models;

import java.util.Map;

public class Schedule {
    Days Days;

    public Schedule(Days days) {
        Days = days;
    }

    public Schedule() {
    }

    public com.example.aesthetics_enginers.Models.Days getDays() {
        return Days;
    }

    public void setDays(com.example.aesthetics_enginers.Models.Days days) {
        Days = days;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "Days=" + Days +
                '}';
    }

}


