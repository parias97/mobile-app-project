package com.example.flipper_app.ui.sold;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoldViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SoldViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the sold fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}