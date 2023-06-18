package com.example.healthcare.person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.healthcare.R;
import com.example.healthcare.adapter.LikelyRecipeAdapter;
import com.example.healthcare.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class LikelyRecipeActivity extends AppCompatActivity {
    private LinearLayout return_back; //返回键
    private ListView listView;
    private List<Recipe> list = new ArrayList<>();
    private LikelyRecipeClickListener likelyRecipeClickListener = new LikelyRecipeClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likely_recipe);

        initView();
        initData();
        BaseAdapter adapter = new LikelyRecipeAdapter(list,this);
        listView.setAdapter(adapter);
    }


    private void initView(){
        return_back = findViewById(R.id.likelyrecipe_return);
        listView = findViewById(R.id.likelyrecipe_listview);

        return_back.setOnClickListener(likelyRecipeClickListener);

        listView.setDivider(null);
        listView.setDividerHeight(0);

    }

    private void initData(){
        for(int i = 0; i < 1; i++){
            Recipe recipe = new Recipe("地中海饮食健康食谱",1833,"心血管","海鱼",i);
            list.add(recipe);
        }

    }

    private class LikelyRecipeClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.likelyrecipe_return:
                    Log.d("LikelyRecipe","Return Button Click");
                    finish();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }
}