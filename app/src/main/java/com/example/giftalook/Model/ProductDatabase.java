package com.example.giftalook.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public static ProductDatabase productDbInstance;

    public abstract ProductDao productDao();

    public static synchronized ProductDatabase getInstance(Context context) {
        if(productDbInstance == null) {
            productDbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return productDbInstance;
    }

}
