package com.example.healthcare.bean;


import com.example.healthcare.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Food implements Serializable {
    public Food() {
    }
    //带参数的构造方法，创建食物实例
    public Food(String name, String type, String desc, int image, String unit, List<Nutrition> list) {
        this.name = name;
        this.desc = desc; //简单描述
        this.type = type;
        this.image = image;
        this.unit = unit; //单位
        this.list = list; //营养成分列表
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

    public static List<Food> breakfastList = new ArrayList<>();
    public static List<Food> addbreakfastList = new ArrayList<>();
    public static List<Food> lunchlist = new ArrayList<>();
    public static List<Food> addlunchlist = new ArrayList<>();
    public static List<Food> dinnerlist = new ArrayList<>();

    //静态代码块，创建了一个 ArrayList<Nutrition> 类型的nutritions列表，向其中添加了多个 Nutrition 类实例
    static {
        ArrayList<Nutrition> nutritions = new ArrayList<>();
        ArrayList<Nutrition> nutrition1 = new ArrayList<>();

        nutritions.add(new Nutrition("热量", "50大卡", "3%"));
        nutritions.add(new Nutrition("水分", "2克", "2%"));
        nutritions.add(new Nutrition("蛋白质", "5克", "5%"));
        nutritions.add(new Nutrition("脂肪", "5克", "5%"));
        nutritions.add(new Nutrition("膳食纤维", "5克", "5%"));
        nutritions.add(new Nutrition("碳水化物", "5克", "5%"));
        nutritions.add(new Nutrition("视黄醇当量", "5克", "5%"));
        nutritions.add(new Nutrition("硫胺素(VB1)", "5克", "5%"));
        nutritions.add(new Nutrition("核黄素(VB2)", "5克", "5%"));
        nutritions.add(new Nutrition("尼克酸", "5克", "5%"));
        nutritions.add(new Nutrition("维生素E", "5克", "5%"));
        nutritions.add(new Nutrition("钠", "5克", "5%"));
        nutritions.add(new Nutrition("钙", "5克", "5%"));
        nutritions.add(new Nutrition("铁", "5克", "5%"));
        nutritions.add(new Nutrition("VC", "5克", "5%"));
        nutritions.add(new Nutrition("胆固醇", "null", "5%"));

        breakfastList.add(new Food("脱脂牛奶", "肉蛋奶", "描述", R.drawable.freefat_milk, "1杯", nutritions));
        breakfastList.add(new Food("荷包蛋", "肉蛋类", "51千卡/每100克", R.drawable.pear, "15克", nutrition1));
        breakfastList.add(new Food("全麦面包", "主食类", "32千卡/每100克", R.drawable.whole_wheat_bread, "2厚片", nutritions));
        addbreakfastList.add(new Food("苹果", "水果类", "53千卡/每100克", R.drawable.apple, "1个", nutritions));
        lunchlist.add(new Food("蒸红薯", "主食类", "46千卡/每100克", R.drawable.sweet_potato, "300克", nutrition1));
        lunchlist.add(new Food("鳕鱼（煎）", "肉蛋奶", "低脂", R.drawable.cod, "200克", nutritions));
        lunchlist.add(new Food("蔬菜拉沙", "蔬菜及菌类", "45千卡/每100克", R.drawable.vegetable_salad, "300克", nutritions));
        addlunchlist.add(new Food("燕麦牛奶", "肉蛋奶", "描述", R.drawable.oats_milk, "1杯", nutritions));
        addlunchlist.add(new Food("梨", "水果类", "51千卡/每100克", R.drawable.pear, "15克", nutrition1));
        dinnerlist.add(new Food("土豆泥", "主食类", "35千卡/每100克", R.drawable.mash, "200克", nutrition1));
        dinnerlist.add(new Food("胡萝卜蔬菜汤", "蔬菜及菌类", "44千卡/每100克", R.drawable.carrot_soup, "150克", nutritions));
        dinnerlist.add(new Food("煎鸡胸肉", "肉蛋奶", "低脂", R.drawable.chicken, "100克", nutritions));






        //image 成员变量的类型是 int，用于存储食物图片的资源ID-对应着 R.drawable 中的整数常量
        foodList.add(new Food("苹果", "水果类", "53千卡/每100克", R.drawable.apple, "1个", nutritions));
        foodList.add(new Food("梨", "水果类", "51千卡/每100克", R.drawable.pear, "15克", nutrition1));
        foodList.add(new Food("香蕉", "水果类", "93千卡/每100克", R.drawable.banana, "15克", nutritions));
        foodList.add(new Food("西瓜", "水果类", "32千卡/每100克", R.drawable.watermelon, "15克", nutritions));
        foodList.add(new Food("水蜜桃", "水果类", "46千卡/每100克", R.drawable.peach, "15克", nutrition1));
        foodList.add(new Food("葡萄", "水果类", "45千卡/每100克", R.drawable.grape, "15克", nutritions));
        foodList.add(new Food("菠萝", "水果类", "44千卡/每100克", R.drawable.pineapple, "15克", nutritions));
        foodList.add(new Food("芒果", "水果类", "35千卡/每100克", R.drawable.mango, "15克", nutrition1));
        foodList.add(new Food("樱桃", "水果类", "46千卡/每100克", R.drawable.cherry, "15克", nutritions));
        foodList.add(new Food("猕猴桃", "水果类", "61千卡/每100克", R.drawable.kiwi_fruit, "15克", nutrition1));
        foodList.add(new Food("橙子", "水果类", "47千卡/每100克", R.drawable.orange, "15克", nutritions));

        foodList.add(new Food("全麦面包", "主食类", "32千卡/每100克", R.drawable.whole_wheat_bread, "2厚片", nutritions));
        foodList.add(new Food("蒸红薯", "主食类", "46千卡/每100克", R.drawable.sweet_potato, "300克", nutrition1));
        foodList.add(new Food("土豆泥", "主食类", "35千卡/每100克", R.drawable.mash, "200克", nutrition1));
        foodList.add(new Food("煮面条", "主食类", "301千卡/每100克", R.drawable.noddles, "30克", nutritions));
        foodList.add(new Food("米饭", "主食类", "346千卡/每100克", R.drawable.rice, "30克", nutritions));
        foodList.add(new Food("猪肉馅包子", "主食类", "227千卡/每100克", R.drawable.meat_bread, "30克", nutritions));
        foodList.add(new Food("煮玉米", "主食类", "112千卡/每100克", R.drawable.corn, "200克", nutrition1));
        foodList.add(new Food("紫薯", "主食类", "123千卡/每100克", R.drawable.purple_potato, "30克", nutritions));
        foodList.add(new Food("芋头", "主食类", "56千卡/每100克", R.drawable.taro, "30克", nutritions));
        foodList.add(new Food("小米粥", "主食类", "46千卡/每100克", R.drawable.millet, "30克", nutritions));


        foodList.add(new Food("蔬菜拉沙", "蔬菜类", "45千卡/每100克", R.drawable.vegetable_salad, "300克", nutritions));
        foodList.add(new Food("胡萝卜蔬菜汤", "蔬菜类", "44千卡/每100克", R.drawable.carrot_soup, "150克", nutritions));


        foodList.add(new Food("脱脂牛奶", "肉蛋奶", "描述", R.drawable.freefat_milk, "1杯", nutritions));
        foodList.add(new Food("燕麦牛奶", "肉蛋奶", "描述", R.drawable.oats_milk, "1杯", nutritions));
        foodList.add(new Food("煎鸡胸肉", "肉蛋奶", "低脂", R.drawable.chicken, "100克", nutritions));
        foodList.add(new Food("鳕鱼（煎）", "肉蛋奶", "低脂", R.drawable.cod, "200克", nutritions));
        foodList.add(new Food("荷包蛋", "肉蛋奶", "低脂", R.drawable.egg, "1个", nutritions));

    }
}
