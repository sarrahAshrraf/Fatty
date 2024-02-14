package com.example.faty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faty.Presenter.MealCategoryAdapter;
import com.example.faty.Presenter.MealContract;
import com.example.faty.Presenter.MealPresenter;
import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;

import java.util.ArrayList;
import java.util.List;


public class CatigoriesFragment extends Fragment implements MealContract.View {


        private TextView postxt;
        private RecyclerView recyclerView;
        private MealCategoryAdapter adapter;
        private MealContract.Presenter presenter;
        private String categoryName;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_catigories, container, false);

            postxt = view.findViewById(R.id.categoryCount);
            recyclerView = view.findViewById(R.id.RecView_meals);
            presenter = new MealPresenter(this);

            Bundle bundle = getArguments();
            if (bundle != null) {
                categoryName = bundle.getString("catName");
                presenter.getMealsByCategory(categoryName);
            }

            postxt.setText(categoryName);

            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new MealCategoryAdapter(new ArrayList<>(), new MealCategoryAdapter.OnCategoryClickListener() {
                @Override
                public void onCategoryClick(int position, Category category, String categoryName) {
                    // Handle category click if needed
                }
            });
            recyclerView.setAdapter(adapter);

            return view;
        }

        @Override
        public void showCategories(List<Category> categoryList) {
            adapter.setData(categoryList);
        }

        @Override
        public void displayMeal(Meal meal) {
            // Not used in this fragment
        }



    @Override
        public void displayError(String message) {
            // Not used in this fragment
        }

        @Override
        public String getMealId() {
            return null;
        }

        @Override
        public String getCategoryName() {
            return categoryName;
        }

        @Override
        public void showCountries(List<Country> countryList) {
            // Not used in this fragment
        }
    }