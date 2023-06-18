package com.example.healthcare.bean;
import java.util.ArrayList;

import java.io.Serializable;
import java.util.List;

public class RecipeItem implements Serializable {
    //构造方法
    public RecipeItem(String name, List<Food> foodList) {
        this.name = name;
        this.foodList = foodList;
    }
    public RecipeItem() {
    }


    public String name;
    public List<Food> foodList; //食谱所需食材列表
}
