package com.example.faty.Presenter;

import com.example.faty.pojo.Meal;

public interface MealContract {
    interface View {
        void displayMeal(Meal meal);
        void displayError(String message);
        String getMealId();
    }

    interface Presenter {
        void getRandomMeal();
        void getMealDetails(String mealId);
    }
}