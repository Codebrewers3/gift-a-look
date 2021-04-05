package com.example.giftalook.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.OutfitBoardProductDao;
import com.example.giftalook.Model.OutfitBoardProductDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.OutfitBoardProductDao;
import com.example.giftalook.Model.OutfitBoardProductDatabase;

import java.util.List;

    public class OutfitBoardProductRepository {

        private OutfitBoardProductDao productDao;
        private LiveData<List<OutfitBoardProduct>> allOutfitBoardProductsLiveData;

        // Constructor to assign necessary variables
        public OutfitBoardProductRepository(Application application) {
            OutfitBoardProductDatabase messageDbInstance = OutfitBoardProductDatabase.getInstance(application);
            productDao = messageDbInstance.outfitBoardProductDao();
            allOutfitBoardProductsLiveData = productDao.getAllProducts();
        }

        // Creating methods for all database operations specified

        public LiveData<List<OutfitBoardProduct>> getAllOutfitBoardProducts() {
            return allOutfitBoardProductsLiveData;
        }

        public void addProduct(OutfitBoardProduct product) {
            new com.example.giftalook.Repository.OutfitBoardProductRepository.InsertAsyncTask(productDao).execute(product);
        }

        public void updateProduct(OutfitBoardProduct product) {
            new com.example.giftalook.Repository.OutfitBoardProductRepository.UpdateAsyncTask(productDao).execute(product);
        }

        public void deleteProduct(OutfitBoardProduct product) {
            new com.example.giftalook.Repository.OutfitBoardProductRepository.DeleteAsyncTask(productDao).execute(product);
        }

        public void deleteAllProducts() {
            new com.example.giftalook.Repository.OutfitBoardProductRepository.DeleteAllAsyncTask(productDao).execute();
        }

        /* AsyncTask classes */

        private static class InsertAsyncTask extends AsyncTask<OutfitBoardProduct, Void, Void> {
            private OutfitBoardProductDao productDao;

            private InsertAsyncTask(OutfitBoardProductDao productDao) {
                this.productDao = productDao;
            }

            @Override
            protected Void doInBackground(OutfitBoardProduct... products) {
                productDao.addProduct(products[0]);
                return null;
            }
        }

        private static class UpdateAsyncTask extends AsyncTask<OutfitBoardProduct, Void, Void> {
            private OutfitBoardProductDao productDao;

            private UpdateAsyncTask(OutfitBoardProductDao productDao) {
                this.productDao = productDao;
            }

            @Override
            protected Void doInBackground(OutfitBoardProduct... products) {
                productDao.updateProduct(products[0]);
                return null;
            }
        }

        private static class DeleteAsyncTask extends AsyncTask<OutfitBoardProduct, Void, Void> {
            private OutfitBoardProductDao productDao;

            private DeleteAsyncTask(OutfitBoardProductDao productDao) {
                this.productDao = productDao;
            }

            @Override
            protected Void doInBackground(OutfitBoardProduct... products) {
                productDao.deleteProduct(products[0]);
                return null;
            }
        }

        private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
            private OutfitBoardProductDao productDao;

            private DeleteAllAsyncTask(OutfitBoardProductDao productDao) {
                this.productDao = productDao;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                productDao.deleteAllProducts();
                return null;
            }
        }

    }
