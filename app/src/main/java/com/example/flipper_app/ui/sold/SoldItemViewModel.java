package com.example.flipper_app.ui.sold;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.model.SoldItemRepository;

import java.util.List;

public class SoldItemViewModel extends AndroidViewModel {

    public SoldItemRepository repository;
    private LiveData<List<SoldItem>> allSoldItems;
    public double totalProf = 0;

    public SoldItemViewModel(@NonNull Application application) {
        super(application);
        repository = new SoldItemRepository(application);
        totalProf = repository.totalProf;
        allSoldItems = repository.getAllSoldItems();
    }
    public void insert(SoldItem item) {
        repository.insert(item);
    }

    public void update(SoldItem item) {
        repository.update(item);
    }
    public void delete(SoldItem item) {
        repository.delete(item);
    }
    public void deleteAllSoldItems() {
        repository.deleteAllSoldItems();
    }
    public LiveData<List<SoldItem>> getAllSoldItems() {
        return allSoldItems;
    }
}