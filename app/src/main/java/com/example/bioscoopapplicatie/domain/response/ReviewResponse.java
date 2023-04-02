package com.example.bioscoopapplicatie.domain.response;

import com.example.bioscoopapplicatie.domain.Review;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReviewResponse {
    private List<Review> results;
    public List<Review> getResults() {
        return this.results;
    }
}
