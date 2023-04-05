package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;

import java.util.List;

public class MediaListViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<List<MediaList> >allMediaLists;

    public MediaListViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new TheMovieRepository(application);
    }
    public LiveData<List<MediaList>> getAllMediaLists() {
        allMediaLists = repository.getAllMediaLists();
        return allMediaLists;
    }
    public void insertMediaList(MediaList mediaList) {
        repository.insert(mediaList);
        repository.postMediaList(mediaList);
    }

    public void insertMediaToList(MediaListMedia mediaListMedia) {
        repository.insertMediaToList(mediaListMedia);
    }

    public void deleteList(MediaList mediaList, int mediaListId){
        repository.deleteListFromMediaListMedia(mediaListId);
        repository.deleteList(mediaList);
    }
}
