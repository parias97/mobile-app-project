package com.example.flipper_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sold_table")
public class SoldItem {
    // Primary key increments upon new table entry.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String desc;
    private double initialPrice;
    private int quantity;
    private String platform;
    private String picturePath;
    private double soldPrice;
    private double profit;
    private boolean isSold;

//
//    public SoldItem(int quantity, double soldPrice) {
//        this.quantity = quantity;
//        this.soldPrice = soldPrice;
//    }

    public SoldItem(String name, String desc, double soldPrice, int quantity, String platform, String picturePath) {
        this.name = name;
        this.desc = desc;
        this.soldPrice = soldPrice;
        this.quantity = quantity;
        this.platform = platform;
        this.picturePath = picturePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setName(String name) {
        this.name = name;
    }


}
