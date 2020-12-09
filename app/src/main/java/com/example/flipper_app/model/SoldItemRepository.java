package com.example.flipper_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import android.util.Log;
import com.example.flipper_app.MainActivity;

import java.util.List;

import com.example.flipper_app.ui.inventory.InventoryFragment;

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

        public void insert(SoldItem item) {
            new InsertSoldItemAsyncTask(soldItemDao).execute(item);
            totalProf += getProfit();
            Log.d("inserted profit", String.valueOf(totalProf));
            getCount();
        }

    public void update(SoldItem item) {
        new UpdateSoldItemAsyncTask(soldItemDao).execute(item);
    }

    public void delete(SoldItem item) {
        new DeleteSoldItemAsyncTask(soldItemDao).execute(item);
    }

    public void getCount() {
        new SoldItemRepository.GetCountAsyncTask(soldItemDao).execute();
    }

    public void deleteAllSoldItems() {
        new DeleteAllSoldItemsAsyncTask(soldItemDao).execute();
    }

    public double getProfit() {
        double profit = 0.0;
        GetProfitAsyncTask getProfitAsyncTask = new GetProfitAsyncTask(soldItemDao);
        getProfitAsyncTask.execute();
        profit = getProfitAsyncTask.profit;
        Log.d("get profit", String.valueOf(profit));

        return profit;
    }

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
            soldItemDao.insert(items[0]);
            return null;
        }
    }

    private static class GetProfitAsyncTask extends AsyncTask<SoldItem, Void, Void> {
        private SoldItemDao soldItemDao;
        private double profit;

        private GetProfitAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(SoldItem... items) {
            profit = soldItemDao.getProfit();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity.profit = profit;
            InventoryFragment.update();
            totalProf = profit;
            Log.d("testing onpost", String.valueOf(totalProf));
        }

    }

    private static class GetCountAsyncTask extends AsyncTask<Item, Void, Void> {
        private SoldItemDao soldItemDao;
        int count = 0;
        private GetCountAsyncTask(SoldItemDao soldItemDao) {
            this.soldItemDao = soldItemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            count = soldItemDao.getCount();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity.sold = count;
            InventoryFragment.update();
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
