package com.example.flipper_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    // Primary key increments upon new table entry.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String desc;
    private double initialPrice;
    private int quantity;
    private String platform;
    private String picturePath;
    private boolean isSold;


    public Item(String name, String desc, double initialPrice, int quantity, String platform, String picturePath, boolean isSold) {
        this.name = name;
        this.desc = desc;
        this.initialPrice = initialPrice;
        this.quantity = quantity;
        this.platform = platform;
        this.picturePath = picturePath;
        this.isSold = isSold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
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
}
