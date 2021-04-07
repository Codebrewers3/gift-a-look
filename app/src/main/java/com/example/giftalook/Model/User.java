package com.example.giftalook.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * This class defines a User of our app.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String userId;
    public String bio;
    public String imageUrl;
    public ArrayList<OutfitBoardProduct> myProducts;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String userId) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.bio = "";
        this.imageUrl = "";
        myProducts = new ArrayList<>();
    }

    public User(String username, String email, String userId, String bio, String imageUrl) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.bio = bio;
        this.imageUrl = imageUrl;
        myProducts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<OutfitBoardProduct> getMyProducts() {
        return myProducts;
    }

    public void setMyProducts(ArrayList<OutfitBoardProduct> myProducts) {
        this.myProducts = myProducts;
    }
}
