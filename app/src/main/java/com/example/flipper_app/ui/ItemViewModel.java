package com.example.flipper_app.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.ItemRepository;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.model.SoldItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<Item>> allItems;
    private SoldItemRepository repository2;
    private LiveData<List<SoldItem>> allSoldItems;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
        repository2 = new SoldItemRepository(application);
        allSoldItems = repository2.getAllSoldItems();
    }

    public void insert(SoldItem item) {
        repository2.insert(item);
    }
    public void update(SoldItem item) {
        repository2.update(item);
    }
    public void delete(SoldItem item) {
        repository2.delete(item);
    }
    public void deleteAllSoldItems() {
        repository2.deleteAllSoldItems();
    }
    public LiveData<List<SoldItem>> getAllSoldItems() {
        return allSoldItems;
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
