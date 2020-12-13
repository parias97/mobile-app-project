package com.example.flipper_app.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class, SoldItem.class, FavoriteItem.class}, version = 2)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;

    public abstract ItemDao itemDao();

    public abstract SoldItemDao soldItemDao();

    public abstract FavoriteItemDao favoriteItemDao();
    /* Create an ItemDatabase singleton. Only one database is needed.
     * This method is synchronized to prevent multiple threads from
     * creating a new database instance.
     */
    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class, "item_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // Populate database with existing data.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;
        private SoldItemDao soldItemDao;
        private FavoriteItemDao favoriteItemDao;

        private PopulateDbAsyncTask(ItemDatabase db) {
            itemDao = db.itemDao();
            soldItemDao = db.soldItemDao();
            favoriteItemDao = db.favoriteItemDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
