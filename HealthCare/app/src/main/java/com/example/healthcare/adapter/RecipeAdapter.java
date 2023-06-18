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

public class RecipeAdapter extends BaseAdapter {
    private List<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recipeList.size();
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
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_likely_home,null);
            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView) view.findViewById(R.id.item_likelyrecipe_pic);
            viewHolder.title = (TextView) view.findViewById(R.id.item_home_name);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Recipe recipe = recipeList.get(i);
        Glide.with(context).load(recipe.getRecipe_title_square()).into(viewHolder.pic);
        viewHolder.title.setText(recipe.getTitle());

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
