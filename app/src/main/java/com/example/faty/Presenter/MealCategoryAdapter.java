package com.example.faty.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faty.R;
import com.example.faty.pojo.Category;

import java.util.List;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private OnCategoryClickListner listner;

    public MealCategoryAdapter(List<Category> categoryList, OnCategoryClickListner listner) {
        this.categoryList = categoryList;
        this.listner = listner;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            String catName =null;
            if (listner != null) {
                listner.onItemClick(position,catName);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImageView;
        private TextView categoryNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.CategoryImg);
            categoryNameTextView = itemView.findViewById(R.id.categoryText);
        }

        public void bind(Category category) {
            Glide.with(itemView.getContext())
                    .load(category.getStrCategoryThumb())
                    .centerCrop()
                    .into(categoryImageView);

            categoryNameTextView.setText(category.getStrCategory());
        }
    }
}