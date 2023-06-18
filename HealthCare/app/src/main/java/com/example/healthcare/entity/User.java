package com.example.healthcare.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.example.healthcare.R;

import java.io.Serializable;

public class User implements Serializable {
    private String phone;
    private Bitmap headers;
    private String usr_name;
    private String email;
    private String password;
    private String id;
    private int height = 0;
    private int weight = 0;
    private String sex;
    private int age = 0;
    private int blood_pressure = 0;
    private int blood_glucose = 0;

    public User() {
    }
    public User(String usr_name,String email,String password){
        this.usr_name = usr_name;
        this.email = email;
        this.password = password;
        this.headers = null;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getHeaders() {
        return headers;
    }

    public void setHeaders(Bitmap headers) {
        this.headers = headers;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(int blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public int getBlood_glucose() {
        return blood_glucose;
    }

    public void setBlood_glucose(int blood_glucose) {
        this.blood_glucose = blood_glucose;
    }


    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
