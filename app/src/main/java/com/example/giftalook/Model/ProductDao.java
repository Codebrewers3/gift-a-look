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
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE FROM product_details_table")
    void deleteAllProducts();

    // Method returns LiveData which we can observe, to enable updates in real time
    @Query("SELECT * FROM product_details_table")
    LiveData<List<Product>> getAllProducts();

}
