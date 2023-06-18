package com.example.healthcare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthcare.bean.Recipe;
import com.example.healthcare.databinding.ItemRecipeBinding;

import java.util.List;

public class RecipeFoodAdapter extends RecyclerView.Adapter<RecipeFoodAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private OnItemClickListener listener;

    public RecipeFoodAdapter(List<Recipe> recipeList, OnItemClickListener listener) {
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.binding.tvName.setText(recipe.name);
        holder.binding.tvDesc.setText(recipe.desc);
        // Load image using Glide or Picasso
        Glide.with(holder.itemView).load(recipe.image).into(holder.binding.ivImage);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemRecipeBinding binding;

        public RecipeViewHolder(@NonNull ItemRecipeBinding binding) {
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
        void onItemClick(Recipe recipe);
    }
}
