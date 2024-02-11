package com.example.faty.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.faty.pojo.Meal;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(MealDTO meal);

    @Query("SELECT * FROM favmeals")
    List<MealDTO> getAllMeals();

    @Query("SELECT * FROM favmeals WHERE idMeal = :mealId")
    MealDTO getMealById(String mealId);

    @Delete
    void deleteMeal(MealDTO meal);
}