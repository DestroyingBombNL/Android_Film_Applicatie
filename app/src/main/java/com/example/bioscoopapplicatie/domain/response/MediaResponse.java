package com.example.bioscoopapplicatie.domain.response;

import android.util.Log;

import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;
public class MediaResponse {
    private List<Media> results;
    public List<Media> getResult() {
        return this.results;
    }
}
