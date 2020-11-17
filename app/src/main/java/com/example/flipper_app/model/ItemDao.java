package com.example.flipper_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Insert
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM item_table")
    void deleteAllItems();

    @Query("SELECT * FROM item_table")
    LiveData<List<Item>> getAllItems();
}
