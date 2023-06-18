package com.example.healthcare.entity;

import com.example.healthcare.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Food implements Serializable {
    public Food() {
    }
    //带参数的构造方法，创建食物实例
    public Food(String name, String type, String desc, int image, String unit, List<Nutrition> list) {
              }
    //定义变量
    public String name;
    public String desc;
    public String type;
    public int image;
    public String unit;
    public List<Nutrition> list;

    //静态成员变量，List<Food> 类型的列表，用于存储多个不同的 Food 类实例
    public static List<Food> foodList = new ArrayList<>();

    //静态代码块，创建了一个 ArrayList<Nutrition> 类型的nutritions列表，向其中添加了多个 Nutrition 类实例
    static {
        ArrayList<Nutrition> nutritions = new ArrayList<>();
        ArrayList<Nutrition> nutrition1 = new ArrayList<>();

                      
        //image 成员变量的类型是 int，用于存储食物图片的资源ID-对应着 R.drawable 中的整数常量
        foodList.add(new Food("苹果", "水果类","苹果的简单描述", R.drawable.person_header_girl, "15克", nutritions));
        foodList.add(new Food("梨子", "水果类", "梨子的简单描述", R.drawable.person_header_girl, "15克", nutrition1));
        foodList.add(new Food("香蕉", "水果类", "香蕉的简单描述", R.drawable.person_header_girl, "15克", nutritions));

        foodList.add(new Food("猪肉", "肉蛋奶", "猪肉的描述", R.drawable.person_header_girl, "20克", nutritions));
        foodList.add(new Food("牛肉", "肉蛋奶", "牛肉的描述", R.drawable.person_header_girl, "20克", nutritions));
        foodList.add(new Food("鸡肉", "肉蛋奶", "鸡肉的描述", R.drawable.person_header_girl, "20克", nutritions));

        foodList.add(new Food("面条", "主食类", "面条的描述", R.drawable.person_header_girl, "30克", nutritions));
        foodList.add(new Food("米饭", "主食类", "米饭的描述", R.drawable.person_header_girl, "30克", nutritions));
        foodList.add(new Food("土豆", "主食类", "土豆的描述", R.drawable.person_header_girl, "30克", nutritions));
    }
}
