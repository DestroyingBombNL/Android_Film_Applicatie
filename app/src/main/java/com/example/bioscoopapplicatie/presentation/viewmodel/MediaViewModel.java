package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.MediaRepository;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

public class MediaViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private MediaRepository repository;
    private LiveData<List<Media>> allMedia;

    public MediaViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new MediaRepository(application);
    }
    public LiveData<List<Media>> getAllMedia() {
        allMedia = repository.getAllMedia();
        return allMedia;
    }

    public LiveData<List<Media>> getFilteredMedia(String filter) {
        allMedia = repository.getAllFilteredMedia(filter);
        return allMedia;
    }
}