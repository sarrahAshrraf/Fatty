package com.example.faty.retrofit;

import com.example.faty.pojo.CountryList;
import com.example.faty.pojo.CategoryList;
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
    Call<CategoryList> getMealCategoriesList();

    @GET("list.php?a=list")
    Call<CountryList> getMealsCountry();

    //show meals of certain category when click on a category in Home
    //filter.php?c=Chicken
    @GET("filter.php")
    Call<MealList> getMealsOfCertainCategory(@Query("c") String category);


}
