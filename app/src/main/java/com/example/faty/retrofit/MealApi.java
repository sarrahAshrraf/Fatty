package com.example.faty.retrofit;

import com.example.faty.pojo.MealList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {
    @GET("random.php")
    Call<MealList> getRandomMeal();

    @GET("categories.php")
    Call<MealList> getCategories();
    @GET("lookup.php?")
    Call<MealList> getMealsDetails(@Query("i") String mealId);

}
