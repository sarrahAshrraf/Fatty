package com.example.faty.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.faty.HomeFragment;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.CountryList;
import com.example.faty.pojo.CategoryList;
import com.example.faty.pojo.MealList;
import com.example.faty.retrofit.MealApi;
import com.example.faty.retrofit.RetrofitClient;

import java.util.List;

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
        Call<CategoryList> call = mealApi.getMealCategoriesList();
        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                if (response.isSuccessful()) {
                    CategoryList categoryList = response.body();
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
            public void onFailure(Call<CategoryList> call, Throwable t) {
                view.displayError("API request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void getMealsByCountry() {
        Call<CountryList> call = mealApi.getMealsCountry();
        call.enqueue(new Callback<CountryList>() {
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                if (response.isSuccessful()) {
                    CountryList countryList = response.body();
                    if (countryList != null) {
                        List<Country> countries = countryList.getCountries();

                            view.showCountries(countries);

                    } else {
                        Log.i("NOOOOOOOOOOOOOOOOOOOO", "Fail: ");
                        view.displayError("No countries found");
                    }
                } else {
                    view.displayError("API request failed");
                }
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {
                Log.i("NOOOOOOOOOOOOOO2345OOOOOO", "Fail: ");

                view.displayError("API request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void getMealsByCategory(String category) {
        Call<MealList> call = mealApi.getMealsOfCertainCategory(category);
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


}