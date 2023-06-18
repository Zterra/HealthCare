package com.example.healthcare.entity;

import java.io.Serializable;

public class DailyIntake implements Serializable {
    int breakfast;
    int lunch;
    int dinner;
    int extra;


    public DailyIntake() {
        this.breakfast = -1;
        this.lunch = -1;
        this.dinner = -1;
        this.extra = -1;
    }

    public DailyIntake(int breakfast, int lunch, int dinner, int extra) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.extra = extra;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }
}
