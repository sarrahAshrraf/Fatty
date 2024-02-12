package com.example.faty.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faty.R;
import com.example.faty.database.MealDTO;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<MealDTO> mealList;

    public FavoriteAdapter(List<MealDTO> mealList) {
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO meal = mealList.get(position);
        holder.mealNameTextView.setText(meal.getStrMeal());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImageView;
        TextView mealNameTextView;
        ImageButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImageView = itemView.findViewById(R.id.mealImageView);
            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}