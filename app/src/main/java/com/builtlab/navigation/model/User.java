package com.builtlab.navigation.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Date;

import kotlinx.serialization.Serializable;

@Serializable
public class User {
    private String id;
    private String email;
    private Date createdAt;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public User(String id, String email, Date createdAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User fromString(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, User.class);
    }

    @NonNull
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
