package com.example.bioscoopapplicatie.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class MediaListResponse {

    @SerializedName("created_by")
    private String createdBy;

    @SerializedName("description")
    private String description;

    @SerializedName("favorite_count")
    private int favoriteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("items")
    private List<Media> items;

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getId() {
        return id;
    }

    public List<Media> getItems() {
        return items;
    }
}
