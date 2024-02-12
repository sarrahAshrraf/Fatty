package com.example.faty.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favmeals")
public class MealDTO {
    @PrimaryKey
    @NonNull




    private String idMeal;

   private String strCategory;

    private String strArea;


    private String strInstructions;

    private String strYoutube;
    private String strMeal;

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }

    public String getStrArea() { return strArea; }
    public void setStrArea(String value) { this.strArea = value; }

    public String getIdMeal() { return idMeal; }
    public void setIdMeal(String idMeal) { this.idMeal = idMeal; }

    public String getStrInstructions() { return strInstructions; }
    public void setStrInstructions(String value) { this.strInstructions = value; }


    public String getStrYoutube() { return strYoutube; }
    public void setStrYoutube(String value) { this.strYoutube = value; }

    public String getStrMeal() { return strMeal; }
    public void setStrMeal(String value) { this.strMeal = value; }

}
