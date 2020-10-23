package com.example.flipper_app.ui.addItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddItemViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddItemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add item fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}