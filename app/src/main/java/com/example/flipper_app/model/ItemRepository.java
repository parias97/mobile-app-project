package com.example.flipper_app.model;

import android.app.Application;
import android.os.AsyncTask;
import com.example.flipper_app.adapter.ItemAdapter;


import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    public static Item getItemItem;
    private LiveData<List<Item>> allItems;
    private int id;

    public ItemRepository(Application application){
        ItemDatabase database = ItemDatabase.getInstance(application);
        itemDao = database.itemDao();
        allItems = itemDao.getAllItems();
    }

    // Methods to operate on database.
    public void insert(Item item) {
        new InsertItemAsyncTask(itemDao).execute(item);
    }

    public void update(Item item) {
        new UpdateItemAsyncTask(itemDao).execute(item);
    }

    public void delete(Item item) {
        new DeleteItemAsyncTask(itemDao).execute(item);
    }

    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(itemDao).execute();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    public Item getItem(int id) { return getItemItem; }

    // Allow these operations to run in the background.

    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }

    private static class GetItemHelperAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private GetItemHelperAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            getItemItem = itemDao.getItem(getItemItem.getId());
            return null;
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private UpdateItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;
        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }

    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {

        private ItemDao itemDao;

        private DeleteAllItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAllItems();
            return null;
        }
    }
}
