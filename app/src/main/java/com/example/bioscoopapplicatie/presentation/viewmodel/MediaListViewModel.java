package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.MediaRepository;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

public class MediaListViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private MediaRepository repository;
    private LiveData<List<Media>> allMedia;

    public MediaListViewModel (Application application) {
        super(application);
        repository = new MediaRepository(application);
    }

    public LiveData<List<Media>> getAllMedia() {
        this.repository.getALlMedia();
    }

    public LiveData<List<Media>> getFilteredMedia() {
        this.repository.getAllFilteredMedia();
    }
}
