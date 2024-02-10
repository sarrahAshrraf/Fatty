package com.example.faty.Presenter;

import com.example.faty.pojo.Category;
import com.example.faty.pojo.Meal;

import java.util.List;

public interface MealContract {
    interface View {
        void showCategories(List<Category> categoryList);

        void displayMeal(Meal meal);
        void displayError(String message);
        String getMealId();
    }

    interface Presenter {
        void getRandomMeal();
        void getMealDetails(String mealId);
        void getMealCategoriesList();

    }
}