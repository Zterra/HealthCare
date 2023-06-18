package com.example.healthcare.bean;

import java.io.Serializable;

public class Nutrition implements Serializable {
    public String name;
    public String value;
    public String nrv; //营养素参考值百分比（%NRV），指每100克或100毫升食品中所含营养素的百分比

    public Nutrition(){}
    //构造函数
    public Nutrition(String name, String value, String nrv) {
        this.name = name;
        this.value = value;
        this.nrv = nrv;
    }
}
