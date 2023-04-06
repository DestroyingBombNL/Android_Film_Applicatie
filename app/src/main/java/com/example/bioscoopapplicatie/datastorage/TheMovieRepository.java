package com.example.bioscoopapplicatie.datastorage;

import android.app.Application;
import android.os.AsyncTask;
import android.text.method.NumberKeyListener;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.bioscoopapplicatie.datastorage.dao.AuthorDetailDAO;
import com.example.bioscoopapplicatie.datastorage.dao.GenreDAO;
import com.example.bioscoopapplicatie.datastorage.dao.GenreMediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaListDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaListMediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.ReviewDAO;
import com.example.bioscoopapplicatie.datastorage.dao.UserDAO;
import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.domain.User;
import com.example.bioscoopapplicatie.domain.linkingtable.GenreMedia;
import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;
import com.example.bioscoopapplicatie.domain.response.ListIdResponse;
import com.example.bioscoopapplicatie.domain.response.SessionResponse;
import com.example.bioscoopapplicatie.domain.response.TokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieRepository implements MediaDAO, GenreDAO, MediaListDAO, ReviewDAO, UserDAO, AuthorDetailDAO, GenreMediaDAO, MediaListMediaDAO {
    private final String TAG = this.getClass().getSimpleName();
    private String apiKey;
    private String contentType;
    private TheMovieAPI jsonApi;
    private MediaDAO mediaDao;
    private MediaListDAO mediaListDao;
    private ReviewDAO reviewDao;
    private GenreDAO genreDao;
    private AuthorDetailDAO authorDetailDao;
    private UserDAO userDao;
    private GenreMediaDAO genreMediaDao;
    private MediaListMediaDAO mediaListMediaDao;
    private LiveData<List<Media>> allMedia;
    private LiveData<List<Integer>> allMediaId;
    private LiveData<List<Genre>> allGenres;
    private LiveData<List<MediaList>> allMediaLists;
    private LiveData<List<Review>> allReviews;
    private LiveData<List<AuthorDetail>> allAuthorDetails;
    private LiveData<List<Review>> filteredReviews;
    private LiveData<User> user;
    private LiveData<List<GenreMedia>> allGenreMedia;
    private LiveData<List<Media>> mediaInList;
    public TheMovieRepository(Application application) {
        TheMovieDatabase db = TheMovieDatabase.getDatabase(application);
        mediaDao = db.mediaDao();
        mediaListDao = db.mediaListDao();
        reviewDao = db.reviewDao();
        genreDao = db.genreDao();
        userDao = db.userDao();
        authorDetailDao = db.authorDetailDao();
        genreMediaDao = db.genreMediaDao();
        mediaListMediaDao = db.mediaListMediaDao();
        this.apiKey = "8a27b4ebdf0d58efaf6c4450b7718cc7";
        this.contentType = "application/json;charset=utf-8";
        setRetrofit();
    }
    
    private void setRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.jsonApi = retrofit.create(TheMovieAPI.class);
    }

    public void postMediaList(MediaList mediaList) {
        try {
            new PostMediaListAsyncTask(this.TAG, this.contentType, this.apiKey, "f0325ea82b693953ece330904aede3c7d54726dd", this.jsonApi, mediaList, mediaListDao).execute();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void insertMediaToList(MediaListMedia mediaListMedia) {
        new insertMediaListMediaAsyncTask(mediaListMediaDao).execute(mediaListMedia);
    }

    @Override
    public LiveData<List<Media>> getAllMediaInList(String listId) {
        this.mediaInList = mediaListMediaDao.getAllMediaInList(listId);
        return this.mediaInList;
    }

    @Override
    public void deleteListFromMediaListMedia(int mediaListId) {
        new deleteListFromMediaListMediaAsyncTask(mediaListMediaDao).execute(mediaListId);
    }

    @Override
    public LiveData<List<Media>> getAllFilteredMediaListByGenre(int genreId) {
        mediaInList = mediaListMediaDao.getAllFilteredMediaListByGenre(genreId);
        return mediaInList;
    }

    @Override
    public void insert(Media media) {
        new insertMediaAsyncTask(mediaDao).execute(media);
    }

    @Override
    public LiveData<List<Media>> getAllMedia() {
        allMedia = mediaDao.getAllMedia();
        return allMedia;
    }
    @Override
    public LiveData<List<Integer>> getAllMediaId() {
        allMediaId = mediaDao.getAllMediaId();
        return allMediaId;
    }

    @Override
    public LiveData<Media> getMediaById(String id) {
        return mediaDao.getMediaById(id);
    }

    @Override
    public LiveData<List<Media>> getAllFilteredMedia(String filter) {
        allMedia = mediaDao.getAllFilteredMedia(filter);
        return allMedia;
    }
    @Override
    public LiveData<List<Media>> getAllOrderedVoteAverageMedia() {
        allMedia = mediaDao.getAllOrderedVoteAverageMedia();
        return allMedia;
    }

    @Override
    public LiveData<List<Media>> getAllOrderedReleaseDateMedia() {
        allMedia = mediaDao.getAllOrderedReleaseDateMedia();
        return allMedia;
    }

    @Override
    public void deleteList(MediaList mediaList) {
        new deleteMediaListAsyncTask(mediaListDao).execute(mediaList);
    }

    @Override
    public LiveData<List<Media>> getAllFilteredMediaByGenre(int genreId) {
        allMedia = mediaDao.getAllFilteredMediaByGenre(genreId);
        return allMedia;
    }

    @Override
    public void insert(MediaList mediaList) {
        new insertMediaListAsyncTask(mediaListDao).execute(mediaList);
    }

    @Override
    public LiveData<List<MediaList>> getAllMediaLists() {
        allMediaLists = mediaListDao.getAllMediaLists();
        return allMediaLists;
    }

    @Override
    public void updateMediaList(String value, String name) {
        mediaListDao.updateMediaList(value, name);
    }

    @Override
    public void insert(Review review) {
        new insertReviewAsyncTask(reviewDao).execute(review);
    }

    @Override
    public LiveData<List<Review>> getAllReviews() {
        allReviews = reviewDao.getAllReviews();
        return allReviews;
    }

    @Override
    public void insert(AuthorDetail authorDetail) {
        new insertAuthorDetailAsyncTask(authorDetailDao).execute(authorDetail);
    }

    @Override
    public LiveData<List<AuthorDetail>> getAllAuthorDetails() {
        allAuthorDetails = authorDetailDao.getAllAuthorDetails();
        return allAuthorDetails;
    }

    @Override
    public LiveData<List<Review>> getFilteredReviews(String mediaId) {
        filteredReviews = reviewDao.getFilteredReviews(mediaId);
        return filteredReviews;
    }

    @Override
    public void updateReview(String mediaId, String id) {
        reviewDao.updateReview(mediaId, id);
    }

    @Override
    public void insert(Genre genre) {
        new insertGenreAsyncTask(genreDao).execute(genre);
    }

    @Override
    public LiveData<List<Genre>> getAllGenres() {
        allGenres = genreDao.getAllGenres();
        return allGenres;
    }

    @Override
    public int getFilteredGenre(String filter) {
        return genreDao.getFilteredGenre(filter);
    }

    @Override
    public void insert(User user) {
        new insertUserAsyncTask(userDao).execute(user);
    }

    @Override
    public LiveData<User> getUser() {
        this.user = userDao.getUser();
        return user;
    }

    @Override
    public void insert(GenreMedia genreMedia) {
        new insertGenreMediaAsyncTask(genreMediaDao).execute(genreMedia);
    }

    @Override
    public LiveData<List<GenreMedia>> getAllGenreMedia() {
        this.allGenreMedia = genreMediaDao.getAllGenreMedia();
        return allGenreMedia;
    }


    /**
     * Inserts a media into the database.
     */
    private static class insertMediaAsyncTask extends AsyncTask<Media, Void, Void> {
        private MediaDAO mAsyncTaskDao;
        public insertMediaAsyncTask(MediaDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Media... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertMediaListAsyncTask extends AsyncTask<MediaList, Void, Void> {

        private MediaListDAO mAsyncTaskDao;

        public insertMediaListAsyncTask(MediaListDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MediaList... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertReviewAsyncTask extends AsyncTask<Review, Void, Void> {

        private ReviewDAO mAsyncTaskDao;

        public insertReviewAsyncTask(ReviewDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Review... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAuthorDetailAsyncTask extends AsyncTask<AuthorDetail, Void, Void> {

        private AuthorDetailDAO mAsyncTaskDao;

        public insertAuthorDetailAsyncTask(AuthorDetailDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AuthorDetail... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertGenreAsyncTask extends AsyncTask<Genre, Void, Void> {

        private GenreDAO mAsyncTaskDao;

        public insertGenreAsyncTask(GenreDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Genre... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private class deleteMediaListAsyncTask extends AsyncTask<MediaList, Void, Void>{
        private MediaListDAO mAsyncTaskDao;

        public deleteMediaListAsyncTask(MediaListDAO mediaListDao) {mAsyncTaskDao = mediaListDao;}
        @Override
        protected Void doInBackground(final MediaList... params) {
            mAsyncTaskDao.deleteList(params[0]);
            return null;
        }
    }

    private static class deleteListFromMediaListMediaAsyncTask extends AsyncTask<Integer, Void, Void> {
        private MediaListMediaDAO mediaListMediaDao;

        public deleteListFromMediaListMediaAsyncTask(MediaListMediaDAO dao) {
            mediaListMediaDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            mediaListMediaDao.deleteListFromMediaListMedia(params[0]);
            return null;
        }
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO mAsyncTaskDao;

        public insertUserAsyncTask(UserDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertGenreMediaAsyncTask extends AsyncTask<GenreMedia, Void, Void> {

        private GenreMediaDAO mAsyncTaskDao;

        public insertGenreMediaAsyncTask(GenreMediaDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GenreMedia... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertMediaListMediaAsyncTask extends AsyncTask<MediaListMedia, Void, Void> {
        private MediaListMediaDAO mAsyncTaskDao;
        public insertMediaListMediaAsyncTask(MediaListMediaDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MediaListMedia... params) {
            mAsyncTaskDao.insertMediaToList(params[0]);
            return null;
        }
    }

    private static class UpdateMediaListAsyncTask extends AsyncTask<Void, Void, Void> {
        private MediaListDAO mediaListDao;
        private int listId;
        private String name;

        public UpdateMediaListAsyncTask(MediaListDAO mediaListDao, int listId, String name) {
            this.mediaListDao = mediaListDao;
            this.listId = listId;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mediaListDao.updateMediaList(String.valueOf(listId), name);
            return null;
        }
    }

    private static class PostMediaListAsyncTask extends AsyncTask<Void, Void, Void> {
        private String TAG;
        private String contentType;
        private String apiKey;
        private String sessionId;
        private TheMovieAPI jsonApi;
        private MediaList mediaList;
        private MediaListDAO mediaListDao;
        public PostMediaListAsyncTask(String TAG, String contentType, String apiKey, String sessionId, TheMovieAPI jsonApi, MediaList mediaList, MediaListDAO mediaListDao) {
            this.TAG = TAG;
            this.contentType = contentType;
            this.apiKey = apiKey;
            this.sessionId = sessionId;
            this.jsonApi = jsonApi;
            this.mediaList = mediaList;
            this.mediaListDao = mediaListDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if (!this.sessionId.equals("")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{\"name\":\"");
                stringBuilder.append(this.mediaList.getName());
                stringBuilder.append("\",\"description\":\"");
                stringBuilder.append(mediaList.getDescription());
                stringBuilder.append("\",\"language\":\"en\"}");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), stringBuilder.toString());
                Call<ListIdResponse> call = jsonApi.postMediaList(contentType, apiKey, sessionId, requestBody);
                call.enqueue(new Callback<ListIdResponse>() {
                    @Override
                    public void onResponse(Call<ListIdResponse> call, Response<ListIdResponse> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, "Media posted successfully with status code: " + response.code());
                            new UpdateMediaListAsyncTask(mediaListDao, response.body().getListId(), mediaList.getName());
                        } else {
                            Log.i(TAG, "The request did not register with status code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListIdResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure, error whilst trying to post mediaList");
                    }
                });
            }
            return null;
        }
    }

};
