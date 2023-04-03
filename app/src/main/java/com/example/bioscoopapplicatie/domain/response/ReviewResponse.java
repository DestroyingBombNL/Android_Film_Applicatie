package com.example.bioscoopapplicatie.domain.response;

import com.example.bioscoopapplicatie.domain.Review;
import java.util.List;

public class ReviewResponse {
    private List<Review> results;
    public List<Review> getResults() {
        return this.results;
    }
}
