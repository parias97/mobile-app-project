package com.example.flipper_app.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;

    public abstract ItemDao itemDao();

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
        private PopulateDbAsyncTask(ItemDatabase db) {
            itemDao = db.itemDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // date added, sold (boolean)
            // handle edge cases (quantity 1, sold 5)
            itemDao.insert(new Item("Transformer",  2.30, 1, "eBay", "./test1"));
            itemDao.insert(new Item("Narnia Book", 3.23, 2, "eBay", "./test2"));
            itemDao.insert(new Item("Jay-Z Album", 6.78, 3,"eBay", "./test3"));
            return null;
        }
    }
}
