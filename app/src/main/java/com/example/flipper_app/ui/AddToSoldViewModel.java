package com.example.flipper_app.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddToSoldViewModel  extends ViewModel {

    private MutableLiveData<String> saved;
    private String itemName;
    private double initPrice;
    private int quantity;
    private String platform;
    private String desc;
    private double soldPrice;
    private String picPath;

    public AddToSoldViewModel() {
        saved = new MutableLiveData<>();
        saved.setValue("false");
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setSaved(String val) {
        saved.setValue(val);
    }

    public LiveData<String> getSaved() {
        return saved;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSoldPrice(double soldPrice){
        this.soldPrice = soldPrice;
    }

    public double getSoldPrice(){
        return soldPrice;
    }

}