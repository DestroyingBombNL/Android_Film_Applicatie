package com.example.bioscoopapplicatie.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="author_detail_table")
public class AuthorDetail {
    private String name;
    @PrimaryKey
    @NonNull
    private String username;
    @SerializedName("avatar_path")
    private String avatarPath;
    private double rating;
    public AuthorDetail(String name, String username, String avatarPath, double rating) {
        this.name = name;
        this.username = username;
        this.avatarPath = avatarPath;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
