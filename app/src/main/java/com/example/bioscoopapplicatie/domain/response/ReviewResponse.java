package com.example.bioscoopapplicatie.domain.response;

import com.example.bioscoopapplicatie.domain.Review;
import java.util.List;

public class ReviewResponse {
    private List<Review> results;
    private int id;
    public List<Review> getResults() {
        return this.results;
    }
    public int getId() {return this.id; }
}
