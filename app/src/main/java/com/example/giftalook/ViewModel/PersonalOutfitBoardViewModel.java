package com.example.giftalook.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.Product;
import com.example.giftalook.Repository.OutfitBoardProductRepository;
import com.example.giftalook.Repository.ProductRepository;

import java.util.List;

public class PersonalOutfitBoardViewModel extends AndroidViewModel {

    private OutfitBoardProductRepository productRepository;
    private LiveData<List<OutfitBoardProduct>> allProductsLiveData;

    public PersonalOutfitBoardViewModel(@NonNull Application application) {
        super(application);
        productRepository = new OutfitBoardProductRepository(application);
        allProductsLiveData = productRepository.getAllOutfitBoardProducts();
    }

    /* Wrapper methods for all the database operations specified in ProductRepository.java  */

    public void addProduct(OutfitBoardProduct product) {
        productRepository.addProduct(product);
    }

    public void updateProduct(OutfitBoardProduct product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(OutfitBoardProduct product) {
        productRepository.deleteProduct(product);
    }

    public void deleteAllProducts() {
        productRepository.deleteAllProducts();
    }

    public LiveData<List<OutfitBoardProduct>> getAllProducts() {
        return allProductsLiveData;
    }

}
