package com.example.flipper_app.ui.addItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flipper_app.model.Item;

import java.util.List;

public class AddItemViewModel extends ViewModel {

    private MutableLiveData<String> saved;
    private String itemName;
    private double initPrice;
    private int quantity;
    private String platform;
    private String desc;

    public AddItemViewModel() {
        saved = new MutableLiveData<>();
        saved.setValue("false");
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

    /*public LiveData<String> getText() {
        return mText;
    }*/
}