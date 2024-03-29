package com.example.bioscoopapplicatie.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName="review_table")
public class Review {
    @NonNull
    private String author;
    @Ignore
    @SerializedName("author_details")
    private AuthorDetail authorDetail;
    @SerializedName("content")
    private String description;
    @SerializedName("created_at")
    private String createdAt;
    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private int mediaId;

    public Review(String author, String description, String createdAt, String id, int mediaId) {
        this.author = author;
        this.description = description;
        this.createdAt = createdAt;
        this.id = id;
        this.mediaId = mediaId;
    }

    @Ignore
    public Review(String author, AuthorDetail authorDetail, String description, String createdAt, String id, int mediaId) {
        this.author = author;
        this.authorDetail = authorDetail;
        this.description = description;
        this.createdAt = createdAt;
        this.id = id;
        this.mediaId = mediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public AuthorDetail getAuthorDetails() {
        return authorDetail;
    }

    public void setAuthorDetails(AuthorDetail authorDetail) {
        this.authorDetail = authorDetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }
}
