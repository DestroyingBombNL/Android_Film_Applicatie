package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.Review;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<Review>> allReviews;
    private LiveData<List<Review>> filteredReviews;

    public ReviewViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new TheMovieRepository(application);
    }
    public LiveData<List<Review>> getAllReviews() {
        allReviews = repository.getAllReviews();
        return allReviews;
    }

    public LiveData<List<Review>> getFilteredReviews(String mediaId) {
        filteredReviews = repository.getFilteredReviews(mediaId);
        return filteredReviews;
    }
}
