package com.example.flipper_app.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class FavoriteItem {

    // Primary key increments upon new table entry.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double initialPrice;
    private double soldPrice;
    private String picPath;

    public FavoriteItem(String name, double initialPrice, double soldPrice) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.soldPrice = soldPrice;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public double getSoldPrice() {
        return soldPrice;
    }



    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getName() {
        return name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }
}
