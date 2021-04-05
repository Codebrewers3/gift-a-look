package com.example.giftalook.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OutfitBoardProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(OutfitBoardProduct product);

    @Update
    void updateProduct(OutfitBoardProduct product);

    @Delete
    void deleteProduct(OutfitBoardProduct product);

    @Query("DELETE FROM personal_products_table")
    void deleteAllProducts();

    // Method returns LiveData which we can observe, to enable updates in real time
    @Query("SELECT * FROM personal_products_table")
    LiveData<List<OutfitBoardProduct>> getAllProducts();

}
