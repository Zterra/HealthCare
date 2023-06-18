package com.example.healthcare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthcare.bean.Food;
import com.example.healthcare.databinding.ItemRecipeBinding;


import java.util.List;
//用于RecyclerView控件与数据之间的数据绑定和交互
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> recipeList;
    private OnItemClickListener listener;
    //构造方法，传入数据集合和监听器对象
    public FoodAdapter(List<Food> recipeList, OnItemClickListener listener) {
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = recipeList.get(position);
        holder.binding.tvName.setText(food.name);
        holder.binding.tvDesc.setText(food.desc);
        // 通过Glide或Picasso等库加载食物图片
        Glide.with(holder.itemView).load(food.image).into(holder.binding.ivImage);

    }

    @Override
    //返回数据集合的大小
    public int getItemCount() {
        return recipeList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemRecipeBinding binding;

        public FoodViewHolder(@NonNull ItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(recipeList.get(position));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Food food);
    }
}
