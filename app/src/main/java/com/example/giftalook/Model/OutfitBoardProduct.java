package com.example.giftalook.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_products_table")
public class OutfitBoardProduct {

    /** Unique id for each product displayed */
    @PrimaryKey(autoGenerate = true)
    private int productId;

    /** Name of the product */
    @ColumnInfo(name = "product_name")
    private String productName;

    /** Brand (manufacturer) of said product */
    @ColumnInfo(name = "product_brand_name")
    private String productBrand;

    /** Price of said product */
    @ColumnInfo(name = "product_cost")
    private float productCost;

    /** Detailed description of product */
    @ColumnInfo(name = "product_description")
    private String productDescription;

    /** Clear image of the product, stored as a Uri */
    @ColumnInfo(name = "image_uri_string")
    private String productImageUri;

    public OutfitBoardProduct() {
        productImageUri = "";
    }

    /** Getter and Setter methods for each and every data member of the Product class */

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public float getProductCost() {
        return productCost;
    }

    public void setProductCost(float productCost) {
        this.productCost = productCost;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUri() {
        return productImageUri;
    }

    public void setProductImageUri(String productImageUri) {
        this.productImageUri = productImageUri;
    }

}
