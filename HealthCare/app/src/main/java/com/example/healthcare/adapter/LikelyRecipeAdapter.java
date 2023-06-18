package com.example.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.healthcare.R;
import com.example.healthcare.entity.Recipe;

import java.util.List;

// 还没有做优化！！！！！

public class LikelyRecipeAdapter extends BaseAdapter {
    private List<Recipe> list;
    private Context ctx;

    public LikelyRecipeAdapter(List<Recipe> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.item_likely_recipe,null);
            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView) view.findViewById(R.id.item_likelyrecipe_pic);
            viewHolder.title = (TextView) view.findViewById(R.id.item_likelyrecipe_title);
            viewHolder.calories = (TextView) view.findViewById(R.id.item_likelyrecipe_calorie);
            viewHolder.tag1 = (TextView) view.findViewById(R.id.item_likelyrecipe_tag1);
            viewHolder.tag2 = (TextView) view.findViewById(R.id.item_likelyrecipe_tag2);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Recipe recipe = list.get(i);

        Glide.with(ctx).load(recipe.getRecipe_title_square()).into(viewHolder.pic);
        // pic.setImageResource(recipe.getRecipe_title_square());

//        TextView title = view.findViewById(R.id.item_likelyrecipe_title);
        viewHolder.title.setText(recipe.getTitle());

//        TextView calories = view.findViewById(R.id.item_likelyrecipe_calorie);
        viewHolder.calories.setText(recipe.getAll_calories()+"kcal");

//        TextView tag1 = view.findViewById(R.id.item_likelyrecipe_tag1);
        viewHolder.tag1.setText(recipe.getTag1());

//        TextView tag2 = view.findViewById(R.id.item_likelyrecipe_tag2);
        viewHolder.tag2.setText(recipe.getTag2());

        return view;
    }
    private class ViewHolder{
        ImageView pic;
        TextView title;
        TextView calories;
        TextView tag1;
        TextView tag2;
    }
}
