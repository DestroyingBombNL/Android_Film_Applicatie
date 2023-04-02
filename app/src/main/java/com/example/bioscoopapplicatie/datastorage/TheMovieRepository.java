package com.example.bioscoopapplicatie.datastorage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.bioscoopapplicatie.datastorage.dao.AuthorDetailDAO;
import com.example.bioscoopapplicatie.datastorage.dao.GenreDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaListDAO;
import com.example.bioscoopapplicatie.datastorage.dao.ReviewDAO;
import com.example.bioscoopapplicatie.datastorage.dao.UserDAO;
import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.domain.User;

import java.util.List;

public class TheMovieRepository implements MediaDAO, GenreDAO, MediaListDAO, ReviewDAO, UserDAO, AuthorDetailDAO {
    private final String TAG = this.getClass().getSimpleName();
    private MediaDAO mediaDao;
    private MediaListDAO mediaListDao;
    private ReviewDAO reviewDao;
    private GenreDAO genreDao;
    private AuthorDetailDAO authorDetailDao;
    private UserDAO userDao;
    private LiveData<List<Media>> allMedia;
    private LiveData<List<Integer>> allMediaId;
    private LiveData<List<Genre>> allGenres;
    private LiveData<List<MediaList>> allMediaLists;
    private LiveData<List<Review>> allReviews;
    private LiveData<List<AuthorDetail>> allAuthorDetails;
    private LiveData<User> user;

    public TheMovieRepository(Application application) {
        TheMovieDatabase db = TheMovieDatabase.getDatabase(application);
        mediaDao = db.mediaDao();
        mediaListDao = db.mediaListDao();
        reviewDao = db.reviewDao();
        genreDao = db.genreDao();
        authorDetailDao = db.authorDetailDao();
        //userDao.getUser();
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
    public LiveData<List<Media>> getAllFilteredMedia(String filter) {
        allMedia = mediaDao.getAllFilteredMedia(filter);
        return allMedia;
    }

    @Override
    public void insert(MediaList mediaList) {
        new insertMediaListAsyncTask(mediaListDao).execute(mediaList);
    }

    @Override
    public void insert(Review review) {
        new insertReviewAsyncTask(reviewDao).execute(review);
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
    public void insert(Genre genre) {
        new insertGenreAsyncTask(genreDao).execute(genre);
    }

    @Override
    public LiveData<List<Genre>> getAllGenres() {
        allGenres = genreDao.getAllGenres();
        return allGenres;
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
};
