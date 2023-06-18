package com.example.healthcare.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthcare.bean.RecipeItem;
import com.example.healthcare.databinding.ItemRecipeItemBinding;

import java.util.List;

public class RecipeItemAdapter extends RecyclerView.Adapter<RecipeItemAdapter.RecipeItemViewHolder> {

    private List<RecipeItem> recipeItemList;

    public RecipeItemAdapter(List<RecipeItem> recipeItemList) {
        this.recipeItemList = recipeItemList;
    }

    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeItemViewHolder(ItemRecipeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder holder, int position) {
        RecipeItem recipeItem = recipeItemList.get(position);
        ItemRecipeItemBinding binding = holder.binding;
        binding.tvName.setText(recipeItem.name);
        InnerFoodAdapter innerFoodAdapter = new InnerFoodAdapter(recipeItem.foodList);
        binding.rvRecipe.setAdapter(innerFoodAdapter);
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        private ItemRecipeItemBinding binding;

        public RecipeItemViewHolder(@NonNull ItemRecipeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
