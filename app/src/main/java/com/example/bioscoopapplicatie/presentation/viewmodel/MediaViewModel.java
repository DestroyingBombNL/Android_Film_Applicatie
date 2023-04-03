package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

public class MediaViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<Media>> allMedia;

    public MediaViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new TheMovieRepository(application);
    }
    public LiveData<List<Media>> getAllMedia() {
        allMedia = repository.getAllMedia();
        return allMedia;
    }

    public LiveData<Media> getMediaById(String id) {
        return repository.getMediaById(id);
    }

    public LiveData<List<Media>> getFilteredMedia(String filter) {
        allMedia = repository.getAllFilteredMedia(filter);
        return allMedia;
    }
}