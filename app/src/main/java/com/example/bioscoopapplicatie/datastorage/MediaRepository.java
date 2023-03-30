package com.example.bioscoopapplicatie.datastorage;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.MediaResponse;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.domain.User;
import com.example.bioscoopapplicatie.presentation.viewmodel.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MediaRepository implements MediaDAO, GenreDAO, MediaListDAO, ReviewDAO, UserDAO {
    private final String TAG = this.getClass().getSimpleName();
    private MediaDAO mediaDao;
    private GenreDAO genreDao;
    private MediaListDAO mediaListDao;
    private ReviewDAO reviewDao;
    private UserDAO userDao;
    private LiveData<List<Media>> allMedia;
    private LiveData<List<Genre>> allGenres;
    private LiveData<List<MediaList>> allMediaLists;
    private LiveData<List<Review>> allReviews;
    private LiveData<User> user;

    public MediaRepository(Application application) {
        TheMovieDatabase db = TheMovieDatabase.getDatabase(application);
        mediaDao = db.mediaDao();
        /*genreDao.getAllGenres();
        mediaListDao.getAllMediaLists();
        reviewDao.getAllReviews();
        userDao.getUser();*/
    }

    @Override
    public void insert(Media media) {
        new insertAsyncTask(mediaDao).execute(media);
    }

    @Override
    public LiveData<List<Media>> getAllMedia() {
        allMedia = mediaDao.getAllMedia();
        return allMedia;
    }
    @Override
    public LiveData<List<Media>> getAllFilteredMedia(String filter) {
        allMedia = mediaDao.getAllMedia();
        return null;
    }


    /**
     * Inserts a meal into the database.
     */
    private static class insertAsyncTask extends AsyncTask<Media, Void, Void> {

        private MediaDAO mAsyncTaskDao;

        insertAsyncTask(MediaDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Media... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
};
