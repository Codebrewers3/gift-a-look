package com.example.giftalook.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giftalook.MainActivity;
import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.Product;
import com.example.giftalook.R;
import com.example.giftalook.ViewModel.PersonalOutfitBoardViewModel;
import com.example.giftalook.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class OutfitBoardAdapter extends RecyclerView.Adapter<OutfitBoardAdapter.ProductHolder> {

    private List<OutfitBoardProduct> productList = new ArrayList<>();

    public OutfitBoardAdapter(ArrayList<OutfitBoardProduct> models) {
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
        OutfitBoardProduct currentProduct = productList.get(position);

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public OutfitBoardProduct getProductAt(int position) {
        return productList.get(position);
    }

    public void setProductList(List<OutfitBoardProduct> productList) {
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