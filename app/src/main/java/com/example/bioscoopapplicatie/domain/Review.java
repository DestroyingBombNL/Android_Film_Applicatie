package com.example.bioscoopapplicatie.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName="review_table")
public class Review {
    @NonNull
    private String author;
    @Ignore
    @SerializedName("author_details")
    private AuthorDetails authorDetails;

    @SerializedName("content")
    private String description;
    @SerializedName("created_at")
    private String createdAt;
    @PrimaryKey
    @NonNull
    private String id;

    public Review(String author, String description, String createdAt, String id) {
        this.author = author;
        this.description = description;
        this.createdAt = createdAt;
        this.id = id;
    }

    @Ignore
    public Review(String author, AuthorDetails authorDetails, String description, String createdAt, String id) {
        this.author = author;
        this.authorDetails = authorDetails;
        this.description = description;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public AuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
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
}
