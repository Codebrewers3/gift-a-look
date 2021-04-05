package com.example.giftalook.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {OutfitBoardProduct.class}, version = 1)
public abstract class OutfitBoardProductDatabase extends RoomDatabase {

    public static OutfitBoardProductDatabase productDbInstance;

    public abstract OutfitBoardProductDao outfitBoardProductDao();

    public static synchronized OutfitBoardProductDatabase getInstance(Context context) {
        if(productDbInstance == null) {
            productDbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    OutfitBoardProductDatabase.class, "my_product_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return productDbInstance;
    }

}
