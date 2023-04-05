package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

public class AuthorDetailViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<AuthorDetail>> allAuthorDetails;
    public AuthorDetailViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "AuthorDetailViewModel called");
        repository = new TheMovieRepository(application);
    }

    public LiveData<List<AuthorDetail>> getAllAuthorDetails() {
        allAuthorDetails = repository.getAllAuthorDetails();
        return allAuthorDetails;
    }
}