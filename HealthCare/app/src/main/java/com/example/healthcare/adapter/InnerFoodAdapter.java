package com.example.healthcare.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthcare.bean.Food;
import com.example.healthcare.databinding.ItemFoodBinding;

import java.util.List;

public class InnerFoodAdapter extends RecyclerView.Adapter<InnerFoodAdapter.RecipeViewHolder> {

    private List<Food> foodList;


    public InnerFoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodBinding binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.binding.tvName.setText(food.name);
        holder.binding.tvUnit.setText(food.unit);
        Glide.with(holder.itemView).load(food.image).into(holder.binding.ivImage);

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ItemFoodBinding binding;

        public RecipeViewHolder(@NonNull ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
