package com.example.healthcare.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.healthcare.R;
import com.example.healthcare.adapter.FoodAdapter;
import com.example.healthcare.adapter.TypeAdapter;
import com.example.healthcare.bean.Food;
import com.example.healthcare.databinding.ActivityRecipeBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FoodActivity extends AppCompatActivity  implements FoodAdapter.OnItemClickListener, TypeAdapter.OnItemClickListener{
    ActivityRecipeBinding binding;
    //显示某类别食物列表的适配器
    private FoodAdapter foodAdapter;
    //显示食物分类的适配器
    private TypeAdapter typeAdapter;
    //存储数据
    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Food> origin = new ArrayList<>(Food.foodList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关键代码
        binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);
        //初始化foodAdapter
        foodAdapter = new FoodAdapter(foodList, this);
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("水果类");
        typeList.add("肉蛋奶");
        typeList.add("主食类");
        typeList.add("大豆及制品");
        typeList.add("蔬菜及菌类");
        typeList.add("饮料类");
        typeList.add("油脂类");
        typeList.add("调味品");
        typeList.add("其他");
        //初始化适配器
        typeAdapter = new TypeAdapter(typeList);
        binding.rvType.setAdapter(typeAdapter);
        binding.rvData.setAdapter(foodAdapter);
        typeAdapter.setOnItemClickListener(this);
        //默认选择第一个食物
        onItemClick(typeList.get(0));


    }

    @Override
    public void onItemClick(Food food) {
        startActivity(new Intent(FoodActivity.this,
                FoodDetailActivity.class).putExtra("food",food));

    }

    @Override
    public void onItemClick(String data) {
        foodList.clear();
        //将筛选后的数据添加到食谱列表中的语句
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            foodList.addAll(origin.stream().filter(recipe -> recipe.type.equals(data)).collect(Collectors.toList()));
        }
        foodAdapter.notifyDataSetChanged();
    }
}