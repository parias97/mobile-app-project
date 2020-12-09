package com.example.flipper_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SoldItemRepository {

    private SoldItemDao soldItemDao;
    private LiveData<List<SoldItem>> allSoldItems;
    public static double totalProf = 0;

    public SoldItemRepository(Application application){
        ItemDatabase database = ItemDatabase.getInstance(application);
        soldItemDao = database.soldItemDao();
        allSoldItems = soldItemDao.getAllSoldItems();
        getProfit();
    }

    // Methods to operate on database.
    public void insert(SoldItem item) {
        totalProf += item.getProfit();
        new InsertSoldItemAsyncTask(soldItemDao).execute(item);
    }

    public void update(SoldItem item) {
        new UpdateSoldItemAsyncTask(soldItemDao).execute(item);
    }

    public void delete(SoldItem item) {
        new DeleteSoldItemAsyncTask(soldItemDao).execute(item);
    }

    public void deleteAllSoldItems() {
        new DeleteAllSoldItemsAsyncTask(soldItemDao).execute();
    }

    public void getProfit() { new GetProfitAsyncTask(soldItemDao).execute(); }

    public LiveData<List<SoldItem>> getAllSoldItems() {
        return allSoldItems;
    }

    // Allow these operations to run in the background.
    private static class InsertSoldItemAsyncTask extends AsyncTask<SoldItem, Void, Void> {
        private SoldItemDao soldItemDao;
        private InsertSoldItemAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(SoldItem... items) {
            totalProf = soldItemDao.getProfit();
            return null;
        }
    }

    // Allow these operations to run in the background.
    private static class GetProfitAsyncTask extends AsyncTask<SoldItem, Void, Void> {
        private SoldItemDao soldItemDao;
        private GetProfitAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(SoldItem... items) {
            soldItemDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateSoldItemAsyncTask extends AsyncTask<SoldItem, Void, Void> {
        private SoldItemDao soldItemDao;
        private UpdateSoldItemAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(SoldItem... items) {
            soldItemDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteSoldItemAsyncTask extends AsyncTask<SoldItem, Void, Void> {
        private SoldItemDao soldItemDao;
        private DeleteSoldItemAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(SoldItem... items) {
            soldItemDao.delete(items[0]);
            return null;
        }
    }

    private static class DeleteAllSoldItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SoldItemDao soldItemDao;

        private DeleteAllSoldItemsAsyncTask(SoldItemDao itemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            soldItemDao.deleteAllSoldItems();
            return null;
        }
    }
}
