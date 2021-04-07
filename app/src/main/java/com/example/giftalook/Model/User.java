package com.example.giftalook.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

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

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String userId) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.bio = "";
        this.imageUrl = "";
    }

    public User(String username, String email, String userId, String bio, String imageUrl) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

}
