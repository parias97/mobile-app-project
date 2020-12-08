package com.example.flipper_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteItemRepository {

    private FavoriteItemDao favoriteItemDao;
    private LiveData<List<FavoriteItem>> allFavoriteItems;

    public FavoriteItemRepository(Application application){
        ItemDatabase database = ItemDatabase.getInstance(application);
        favoriteItemDao = database.favoriteItemDao();
        allFavoriteItems = favoriteItemDao.getAllFavoriteItems();
    }

    // Methods to operate on database.
    public void insert(FavoriteItem item) {
        new InsertFavoriteItemAsyncTask(favoriteItemDao).execute(item);
    }

    public void update(FavoriteItem item) {
        new UpdateFavoriteItemAsyncTask(favoriteItemDao).execute(item);
    }

    public void delete(FavoriteItem item) {
        new DeleteFavoriteItemAsyncTask(favoriteItemDao).execute(item);
    }

    public void deleteAllFavoriteItems() {
        new DeleteAllFavoriteItemsAsyncTask(favoriteItemDao).execute();
    }

    public LiveData<List<FavoriteItem>> getAllFavoriteItems() {
        return allFavoriteItems;
    }

    // Allow these operations to run in the background.
    private static class InsertFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private InsertFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }
        @Override
        protected Void doInBackground(FavoriteItem... items) {
            favoriteItemDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private UpdateFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }
        @Override
        protected Void doInBackground(FavoriteItem ... items) {
            favoriteItemDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteFavoriteItemAsyncTask extends AsyncTask<FavoriteItem, Void, Void> {
        private FavoriteItemDao favoriteItemDao;
        private DeleteFavoriteItemAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }
        @Override
        protected Void doInBackground(FavoriteItem... items) {
            favoriteItemDao.delete(items[0]);
            return null;
        }
    }

    private static class DeleteAllFavoriteItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteItemDao favoriteItemDao;

        private DeleteAllFavoriteItemsAsyncTask(FavoriteItemDao favoriteItemDao) {
            this.favoriteItemDao = favoriteItemDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            favoriteItemDao.deleteAllFavoriteItems();
            return null;
        }
    }
}
