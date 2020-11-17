package com.example.flipper_app.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<Item>> allItems;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }
    public void insert(Item item) {
        repository.insert(item);
    }
    public void update(Item item) {
        repository.update(item);
    }
    public void delete(Item item) {
        repository.delete(item);
    }
    public void deleteAllItems() {
        repository.deleteAllItems();
    }
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }
}
