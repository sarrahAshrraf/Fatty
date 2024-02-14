package com.example.faty.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.faty.R;
import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;

import java.util.List;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countryList;

    public CountryAdapter(List<Country> countryLIst) {
        this.countryList = countryLIst;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.bind(country);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //private ImageView categoryImageView;
        private TextView countryTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //categoryImageView = itemView.findViewById(R.id.CategoryImg);
            countryTxt = itemView.findViewById(R.id.countryText);
        }

        public void bind(Country country) {
            countryTxt.setText(country.getStrArea());
        }
    }
}