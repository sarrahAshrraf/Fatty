package com.example.faty.Presenter;

import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;

import java.util.List;

public interface MealContract {
    interface View {
        void showCategories(List<Category> categoryList);

        void displayMeal(Meal meal);
        void displayError(String message);
        String getMealId();
        void showCountries(List<Country> countryList);
    }

    interface Presenter {
        void getRandomMeal();
        void getMealDetails(String mealId);
        void getMealCategoriesList();
        void getMealsByCountry();
       // void getMealsByCountry(String country);

    }
}