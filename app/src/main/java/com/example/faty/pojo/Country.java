package com.example.faty.pojo;

public class Country {
    private String strArea;

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
    @Override
    public String toString() {
        return "Country{" +
                "strArea='" + strArea + '\'' +
                '}';}

}