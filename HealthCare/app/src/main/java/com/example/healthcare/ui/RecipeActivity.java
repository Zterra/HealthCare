package com.example.healthcare.ui;
import java.util.Arrays;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.healthcare.R;
import com.example.healthcare.adapter.RecipeAdapter;
import com.example.healthcare.adapter.RecipeFoodAdapter;
import com.example.healthcare.adapter.TypeAdapter;
import com.example.healthcare.bean.Food;
import com.example.healthcare.bean.Recipe;
import com.example.healthcare.bean.RecipeItem;
import com.example.healthcare.databinding.ActivityRecipeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeActivity extends AppCompatActivity implements RecipeFoodAdapter.OnItemClickListener, TypeAdapter.OnItemClickListener{
    ActivityRecipeBinding binding;
    //声明成员变量
    private RecipeFoodAdapter recipeAdapter;  //食谱分类器
    private TypeAdapter typeAdapter;      //分类适配器
    private ArrayList<Recipe> recipeList = new ArrayList<>();   //食谱列表
    private ArrayList<Recipe> origin = new ArrayList<>();     //食谱原始数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);
        // 初始化食谱列表数据
        ArrayList<RecipeItem> items = new ArrayList<>();

        RecipeItem recipeItem1 = new RecipeItem("早餐", Food.breakfastList);
        RecipeItem recipeItem2 = new RecipeItem("早加餐", Food.addbreakfastList);
        RecipeItem recipeItem3 = new RecipeItem("午餐", Food.lunchlist);
        RecipeItem recipeItem4 = new RecipeItem("午加餐", Food.addlunchlist);
        RecipeItem recipeItem5 = new RecipeItem("晚餐", Food.dinnerlist);
        items.add(recipeItem1);
        items.add(recipeItem2);
        items.add(recipeItem3);
        items.add(recipeItem4);
        items.add(recipeItem5);



        // 添加食谱原始数据
        origin.add(new Recipe(
                "糖尿病人群",
                "糖尿病维稳食谱1",
                R.drawable.recipe1,
                "1827kcal | 轻碳 | 西式",
                "该食谱通过均匀分布一天中碳水化合物的摄入，以及合理搭配各餐中三大营养素的比例，帮助你保持一天中的血糖稳定。",
                items));
        origin.add(new Recipe(
                "糖尿病人群",
                "糖尿病维稳食谱2",
                R.drawable.recipe2,
                "1780kcal | 中式 | 东方膳食",
                "本食谱在均衡饮食的基础上，用富含不饱和脂肪的食物替代高饱和脂肪和胆固醇的食物，为你的血脂血糖管理计划助力。",
                items));
        origin.add(new Recipe(
                "高血压人群",
                "低盐放水中食谱",
                R.drawable.recipe3,
                "1749kcal | 稳血压 | 控钠",
                "低盐饮食中，盐的每日摄入量控制在1~4克，培养清淡的饮食习惯对干预防和管理慢病都有益处。",
                items));
        origin.add(new Recipe(
                "高血压人群",
                "东方健康膳食食谱",
                R.drawable.recipe4,
                "1808kcal | 适合国人 | 低盐",
                "重油盐的饮食会增加心脏病等疾病的发病风险，对于身体容易水肿的小伙伴们，限制盐的摄入可以减轻水肿症状。",
                items));

        // 初始化适配器
        recipeAdapter = new RecipeFoodAdapter(recipeList, this);
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("糖尿病人群");
        typeList.add("高血压人群");
        typeAdapter = new TypeAdapter(typeList);
        binding.rvType.setAdapter(typeAdapter);
        binding.rvData.setAdapter(recipeAdapter);
        binding.rvData.addItemDecoration(new DividerItemDecoration(RecipeActivity.this, DividerItemDecoration.VERTICAL));
        typeAdapter.setOnItemClickListener(this);
        onItemClick(typeList.get(0));


    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClick(String data) {
        recipeList.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            recipeList.addAll(origin.stream().filter(recipe -> recipe.type.equals(data)).collect(Collectors.toList()));
        }
        recipeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Recipe recipe) {
        startActivity(new Intent(RecipeActivity.this,
                RecipeDetailActivity.class).putExtra("recipe",recipe));

    }
}