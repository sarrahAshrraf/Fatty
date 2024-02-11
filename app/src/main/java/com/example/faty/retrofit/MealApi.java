package com.example.faty.retrofit;

import com.example.faty.pojo.CountryList;
import com.example.faty.pojo.Meal;
import com.example.faty.pojo.MealCategoryList;
import com.example.faty.pojo.MealList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {
    //random meals
    @GET("random.php")
    Call<MealList> getRandomMeal();
//to display categories in the meal fragment
    @GET("categories.php")
    Call<MealList> getCategories();
    //
    @GET("lookup.php?")
    Call<MealList> getMealsDetails(@Query("i") String mealId);

    @GET("categories.php")
    Call<MealCategoryList> getMealCategoriesList();

    @GET("list.php?a")
    Call<CountryList> getMealsCountry();



}
