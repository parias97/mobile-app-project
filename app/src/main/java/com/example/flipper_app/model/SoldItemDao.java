package com.example.flipper_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SoldItemDao {

    @Insert
    void insert(SoldItem item);

    @Update
    void update(SoldItem item);

    @Delete
    void delete(SoldItem item);

    @Query("DELETE FROM sold_table")
    void deleteAllSoldItems();

    @Query("SELECT SUM(profit) FROM sold_table")
    double getProfit();

    @Query("SELECT SUM(quantity) FROM sold_table")
    int getCount();

    @Query("SELECT * FROM sold_table")
    LiveData<List<SoldItem>> getAllSoldItems();
}
