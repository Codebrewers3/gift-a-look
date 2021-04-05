package com.example.giftalook.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.giftalook.Model.Product;
import com.example.giftalook.Model.ProductDao;
import com.example.giftalook.Model.ProductDatabase;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProductsLiveData;

    // Constructor to assign necessary variables
    public ProductRepository(Application application) {
        ProductDatabase messageDbInstance = ProductDatabase.getInstance(application);
        productDao = messageDbInstance.productDao();
        allProductsLiveData = productDao.getAllProducts();
    }

    // Creating methods for all database operations specified

    public LiveData<List<Product>> getAllProducts() {
        return allProductsLiveData;
    }

    public void addProduct(Product product) {
        new InsertAsyncTask(productDao).execute(product);
    }

    public void updateProduct(Product product) {
        new UpdateAsyncTask(productDao).execute(product);
    }

    public void deleteProduct(Product product) {
        new DeleteAsyncTask(productDao).execute(product);
    }

    public void deleteAllProducts() {
        new DeleteAllAsyncTask(productDao).execute();
    }

    /* AsyncTask classes */

    private static class InsertAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.addProduct(products[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private UpdateAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.updateProduct(products[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private DeleteAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.deleteProduct(products[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        private DeleteAllAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllProducts();
            return null;
        }
    }

}

