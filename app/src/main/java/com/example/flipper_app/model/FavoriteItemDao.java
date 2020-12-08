package com.example.flipper_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteItemDao {

    @Insert
    void insert(FavoriteItem item);

    @Update
    void update(FavoriteItem item);

    @Delete
    void delete(FavoriteItem item);

    @Query("DELETE FROM favorite_table")
    void deleteAllFavoriteItems();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<FavoriteItem>> getAllFavoriteItems();
}
