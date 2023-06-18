package com.example.healthcare.bean;

public class DetectFood {
    String name;
    String calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public DetectFood(String name, String calories) {
        this.name = name;
        this.calories = calories;
    }
}
