package com.example.bioscoopapplicatie.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    private List<Review> results;
    public List<Review> getResults() {
        return this.results;
    }
}
