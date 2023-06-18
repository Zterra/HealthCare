package com.example.healthcare.bean;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    public Recipe(String type, String name, int image, String desc, String fullDesc, List<RecipeItem> items) {
        this.type = type;  //类型
        this.name = name;  //名称
        this.image = image;  //图片
        this.desc = desc;  //简单描述（几个关键字）
        this.fullDesc = fullDesc;  //一段话描述
        this.items = items;  //食谱所需的食材列表和用量
    }
    public Recipe() {

    }

    public String type;
    public String name;
    public int image;
    public String desc;
    public String fullDesc;
    public List<RecipeItem> items;
}
