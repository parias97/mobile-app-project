package com.example.flipper_app.model;

import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    // Primary key increments upon new table entry.
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double initialPrice;
    private int quantity;
    private String platform;
    private String picturePath;

    public Item(String name, double initialPrice, int quantity, String platform, String picturePath) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.quantity = quantity;
        this.platform = platform;
        this.picturePath = picturePath;
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
}
