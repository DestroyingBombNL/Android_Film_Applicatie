package com.example.bioscoopapplicatie.domain;

public class Review {
    private String title;
    private int rating;
    private String description;
    private String reviewDate;
    private Media media;
    private User user;

    public Review(String title, int rating, String description, String reviewDate, Media media, User user) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.reviewDate = reviewDate;
        this.media = media;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
