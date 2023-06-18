//用于展示菜谱详情信息
package com.example.healthcare.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.healthcare.adapter.RecipeItemAdapter;
import com.example.healthcare.bean.Recipe;
import com.example.healthcare.databinding.ActivityRecipeDetailBinding;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecipeDetailBinding binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //获取传递过来的Serializable对象，并将其转换为Recipe对象
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        //使用Binding对象设置布局文件中的TextView和RecyclerView组件
        // 分别用于显示菜谱名称、完整描述和所需材料
        binding.tvTitle.setText(recipe.name);
        binding.tvFullDesc.setText(recipe.fullDesc);
        //RecyclerView组件需要使用RecipeItemAdapter适配器来显示所需材料列表
        binding.rvRecipe.setAdapter(new RecipeItemAdapter(recipe.items));
    }
}