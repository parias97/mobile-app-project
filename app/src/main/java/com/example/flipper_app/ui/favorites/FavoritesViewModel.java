package com.example.flipper_app.ui.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.FavoriteItemRepository;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.model.SoldItemRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private FavoriteItemRepository repository;
    private LiveData<List<FavoriteItem>> allFavoriteItems;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteItemRepository(application);
        allFavoriteItems = repository.getAllFavoriteItems();
    }
    public void insert(FavoriteItem item) {
        repository.insert(item);
    }
    public void update(FavoriteItem item) {
        repository.update(item);
    }
    public void delete(FavoriteItem item) {
        repository.delete(item);
    }
    public void deleteAllFavoriteItems() {
        repository.deleteAllFavoriteItems();
    }
    public LiveData<List<FavoriteItem>> getAllFavoriteItems() {
        return allFavoriteItems;
    }
}