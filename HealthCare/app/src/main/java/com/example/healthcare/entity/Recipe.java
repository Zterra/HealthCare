package com.example.healthcare.entity;

import com.example.healthcare.R;

public class Recipe {

    private String title;  //题目
    private int all_calories; //总卡路里
    private String tag1; //标签1
    private String tag2; //标签2
    private int position; //菜谱序列号
    private int recipe_title_square; //菜谱图片id
    private boolean islike; //是否收藏
    private int[] square_pic_list = {R.drawable.recipe_title_square1,R.drawable.recipe_title_square2,R.drawable.recipe_title_square3,R.drawable.recipe_title_square4,R.drawable.recipe_title_square5,R.drawable.recipe_title_square6,R.drawable.recipe_title_square7,R.drawable.recipe_title_square8}; //所有菜谱图片id
    public Recipe() {
    }

    public Recipe(String title , int all_calories, String tag1, String tag2, int position) {
        this.title = title;
        this.all_calories = all_calories;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.position = position;
        this.recipe_title_square = square_pic_list[position % 8];
        this.islike = false;
    }
    public Recipe(String title , int all_calories, String tag1, String tag2,
                  int position,int bitmapId) {
        this.title = title;
        this.all_calories = all_calories;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.position = position;
        this.recipe_title_square=bitmapId;
//        this.recipe_title_square = square_pic_list[position % 8];
        this.islike = false;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAll_calories() {
        return all_calories;
    }

    public void setAll_calories(int all_calories) {
        this.all_calories = all_calories;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    public int getRecipe_title_square() {
        return recipe_title_square;
    }

    public void setRecipe_title_square(int recipe_title_square) {
        this.recipe_title_square = recipe_title_square;
    }
}
