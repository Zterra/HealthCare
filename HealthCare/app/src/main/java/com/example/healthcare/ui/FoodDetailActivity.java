//FoodDetailActivity 是用于展示食物详细信息的 Activity
package com.example.healthcare.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.healthcare.adapter.NutritionAdapter;
import com.example.healthcare.bean.Food;
import com.example.healthcare.databinding.ActivityFoodDetailBinding;

public class FoodDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFoodDetailBinding binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //从 Intent 中获取传递过来的 Food 对象
        Food food = (Food) getIntent().getSerializableExtra("food");
        ////使用 Glide 库将食物的图片加载到 ImageView 中
        Glide.with(this).load(food.image).into(binding.ivImage);
        //将食物名称设置到 TextView 中
        binding.tvName.setText(food.name);
        //将食物营养成分列表展示在 RecyclerView 中
        binding.rvRecipe.setAdapter(new NutritionAdapter(food.list));
    }
}