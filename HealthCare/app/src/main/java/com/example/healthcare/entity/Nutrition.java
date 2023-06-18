package com.example.healthcare.entity;

public class Nutrition {
    public String name;
    public String value;
    public String nrv;

    public Nutrition(String name, String value, String nrv) {
        this.name = name;
        this.value = value;
        this.nrv = nrv;
    }

    public Nutrition() {
    }
}
