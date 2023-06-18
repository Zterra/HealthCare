package com.example.healthcare.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthcare.databinding.ItemTypeBinding;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    private List<String> mData;
    private OnItemClickListener mListener;
    private int mSelectedPosition = 0;

    public TypeAdapter(List<String> data) {
        mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTypeBinding binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position), position == mSelectedPosition);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTypeBinding mBinding;

        ViewHolder(ItemTypeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mSelectedPosition = position;
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.onItemClick(mData.get(position));
                    }
                }
            });
        }

        void bind(String data, boolean isSelected) {
            mBinding.tvName.setText(data);
            mBinding.getRoot().setBackgroundColor(isSelected ?Color.parseColor("#f6f6f6"):Color.parseColor("#ffffff"));
            mBinding.tvName.setTextColor(isSelected ? Color.BLACK : Color.parseColor("#cccccc"));
            mBinding.vFlag.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        }
    }
}
