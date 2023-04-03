package com.example.bioscoopapplicatie.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName="media_list_table")
public class MediaList {
    @PrimaryKey
    @NonNull
    private int id;
    private String name;
    private String description;
    private int favoriteCount;
    public MediaList(int id, String name, String description, int favoriteCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.favoriteCount = favoriteCount;
    }

    @Ignore
    public MediaList(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
