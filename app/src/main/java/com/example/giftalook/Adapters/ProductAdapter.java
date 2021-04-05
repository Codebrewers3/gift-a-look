package com.example.giftalook.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giftalook.Model.Product;
import com.example.giftalook.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> productList = new ArrayList<>();

    public ProductAdapter(ArrayList<Product> models) {
        productList = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_grid_item, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = productList.get(position);

        String productTitle = currentProduct.getProductName();
        String productBrand = currentProduct.getProductBrand();
        float productCost = (float) Math.round(currentProduct.getProductCost() * 100.0) / 100;
        String productDescription = currentProduct.getProductDescription();
        String imageUri = currentProduct.getProductImageUri();

        holder.productTitle.setText(productTitle);
        holder.productBrand.setText(productBrand);
        holder.productCost.setText("Rs. " + productCost);
        holder.productDescription.setText(productDescription);
        holder.productImageView.setImageResource(Integer.parseInt(imageUri));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public Product getProductAt(int position) {
        return productList.get(position);
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        public TextView productTitle;
        public TextView productBrand;
        public TextView productCost;
        public TextView productDescription;
        public ImageView productImageView;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title_textview);
            productBrand = itemView.findViewById(R.id.product_brand_textview);
            productCost = itemView.findViewById(R.id.product_cost_textview);
            productDescription = itemView.findViewById(R.id.product_description_textview);
            productImageView = itemView.findViewById(R.id.product_image_view);
        }
    }

}