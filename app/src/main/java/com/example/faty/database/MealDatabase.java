package com.example.faty.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MealDTO.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "meals.db";

    private static MealDatabase instance;

    public abstract MealDao mealDao();

    public static synchronized MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}