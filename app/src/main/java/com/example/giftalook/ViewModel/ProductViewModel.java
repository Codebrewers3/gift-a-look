package com.example.giftalook.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.giftalook.Model.Product;
import com.example.giftalook.Repository.ProductRepository;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<Product>> allProductsLiveData;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allProductsLiveData = productRepository.getAllProducts();
    }

    /* Wrapper methods for all the database operations specified in ProductRepository.java  */

    public void addProduct(Product Product) {
        productRepository.addProduct(Product);
    }

    public void updateProduct(Product Product) {
        productRepository.updateProduct(Product);
    }

    public void deleteProduct(Product Product) {
        productRepository.deleteProduct(Product);
    }

    public void deleteAllProducts() {
        productRepository.deleteAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProductsLiveData;
    }

}
