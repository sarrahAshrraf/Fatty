package com.example.faty.Presenter;

import com.example.faty.pojo.Meal;

public interface MealContract {
    interface View {
        void displayMeal(Meal meal);
        void displayError(String message);
    }

    interface Presenter {
        void getRandomMeal();
    }
}