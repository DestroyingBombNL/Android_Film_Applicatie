package com.example.bioscoopapplicatie.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bioscoopapplicatie.datastorage.TheMovieRepository;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private TheMovieRepository repository;
    private LiveData<User> user;

    public UserViewModel (Application application) {
        super(application);
        Log.d(TAG, "MediaViewModel called");
        repository = new TheMovieRepository(application);
    }
    public LiveData<User> getUser() {
        user = repository.getUser();
        return user;
    }
}