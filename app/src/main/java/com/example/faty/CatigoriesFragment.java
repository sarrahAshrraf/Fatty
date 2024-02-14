package com.example.faty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faty.Presenter.MealContract;
import com.example.faty.Presenter.MealPresenter;
import com.example.faty.database.MealDTO;
import com.example.faty.database.MealDatabase;
import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;

import java.util.List;


public class CatigoriesFragment extends Fragment implements MealContract.View {
        TextView postxt;
        private MealContract.Presenter presenter;
        private  String categoryName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catigories, container, false);

        postxt = view.findViewById(R.id.categoryCount);
        presenter = new MealPresenter(this);
       // presenter.getMealsByCategory(categoryName);

        Bundle bundle = getArguments();
        if (bundle != null) {
            presenter = new MealPresenter(this);
            presenter.getMealsByCategory(categoryName);

        }
        postxt.setText(categoryName);
        return view;
    }

    @Override
    public void showCategories(List<Category> categoryList) {

    }

    @Override
    public void displayMeal(Meal meal) {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public String getMealId() {
        return null;
    }

    @Override
    public String getCategoryName() {
        return null;
    }

    @Override
    public void showCountries(List<Country> countryList) {

    }
}