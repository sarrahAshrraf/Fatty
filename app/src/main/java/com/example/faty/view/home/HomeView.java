package com.example.faty.view.home;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    //void setMeal(List<Meals.Meal> meal);
    void setCategory();
    void onErrorLoading(String message);
}
