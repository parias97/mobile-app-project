package com.example.flipper_app.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.FavoriteItemRepository;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.ItemRepository;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.model.SoldItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private SoldItemRepository repository2;
    private FavoriteItemRepository repository3;

    private LiveData<List<Item>> allItems;
    private LiveData<List<SoldItem>> allSoldItems;
    private LiveData<List<FavoriteItem>> allFavoriteItems;

    public double totalProf = 0;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
        repository2 = new SoldItemRepository(application);
        repository3 = new FavoriteItemRepository(application);
        allSoldItems = repository2.getAllSoldItems();
        allFavoriteItems = repository3.getAllFavoriteItems();
    }

    public void insert(Item item) {
        repository.insert(item);
    }
    public void update(Item item) {
        repository.update(item);
    }
    public void decrement(Item item) { repository.delete(item); }
    public void delete(Item item) {
        repository.delete(item);
    }
    public void deleteAllItems() {
        repository.deleteAllItems();
    }
    public Item getItem(int id) {
        repository.id = id; return repository.getItem(id);
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    public void insert(SoldItem item) {
        totalProf += item.getQuantity()*(item.getSoldPrice() - item.getInitialPrice()); repository2.insert(item);
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

    public void insert(FavoriteItem item) {
        repository3.insert(item);
    }
    public void update(FavoriteItem item) {
        repository3.update(item);
    }
    public void delete(FavoriteItem item) {
        repository3.delete(item);
    }
    public void deleteAllFavoriteItems() {
        repository3.deleteAllFavoriteItems();
    }
    public LiveData<List<FavoriteItem>> getAllFavoriteItems() {
        return allFavoriteItems;
    }


}
