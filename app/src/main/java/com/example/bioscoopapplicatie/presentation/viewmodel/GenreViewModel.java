package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

public class GenreViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<Genre>> allGenres;

    public GenreViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "GenreViewModel called");
        repository = new TheMovieRepository(application);
    }

    public LiveData<List<Genre>> getAllGenres() {
        allGenres = repository.getAllGenres();
        return allGenres;
    }
}
