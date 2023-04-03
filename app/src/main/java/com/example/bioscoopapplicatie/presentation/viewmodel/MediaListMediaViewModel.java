package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;

import java.util.List;

public class MediaListMediaViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<Media>> allMedia;

    public MediaListMediaViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new TheMovieRepository(application);
    }

    public LiveData<List<Media>> getAllMediaInList(String listId) {
        allMedia = repository.getAllMediaInList(listId);
        return allMedia;
    }
}
