package com.example.faty.Presenter;

import com.example.faty.pojo.MealCategoryList;
import com.example.faty.pojo.MealList;
import com.example.faty.retrofit.MealApi;
import com.example.faty.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MealPresenter implements MealContract.Presenter {

    private MealContract.View view;
    private MealApi mealApi;


    public MealPresenter(MealContract.View view) {
        this.view = view;
        this.mealApi = RetrofitClient.getClient().create(MealApi.class);
    }

    @Override
    public void getRandomMeal() {
        Call<MealList> call = mealApi.getRandomMeal();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                if (response.isSuccessful()) {
                    MealList mealList = response.body();
                    if (mealList != null && mealList.getMeals() != null && !mealList.getMeals().isEmpty()) {
                        view.displayMeal(mealList.getMeals().get(0));
                    } else {
                        view.displayError("No meals found");
                    }
                } else {
                    view.displayError("API request failed");
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                view.displayError("API request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void getMealDetails(String mealId) {
        Call<MealList> call = mealApi.getMealsDetails(mealId);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                if (response.isSuccessful()) {
                    MealList mealList = response.body();
                    if (mealList != null && mealList.getMeals() != null && !mealList.getMeals().isEmpty()) {
                        view.displayMeal(mealList.getMeals().get(0));
                    } else {
                        view.displayError("No meals found");
                    }
                } else {
                    view.displayError("API request failed");
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                view.displayError("API request failed: " + t.getMessage());
            }
        });
    }



    @Override
    public void getMealCategoriesList() {
        Call<MealCategoryList> call = mealApi.getMealCategoriesList();
        call.enqueue(new Callback<MealCategoryList>() {
            @Override
            public void onResponse(Call<MealCategoryList> call, Response<MealCategoryList> response) {
                if (response.isSuccessful()) {
                    MealCategoryList categoryList = response.body();
                    if (categoryList != null) {
                        view.showCategories(categoryList.getCategories());
                    } else {
                        view.displayError("No categories found");
                    }
                } else {
                    view.displayError("API request failed");
                }
            }

            @Override
            public void onFailure(Call<MealCategoryList> call, Throwable t) {
                view.displayError("API request failed: " + t.getMessage());
            }
        });
    }
}