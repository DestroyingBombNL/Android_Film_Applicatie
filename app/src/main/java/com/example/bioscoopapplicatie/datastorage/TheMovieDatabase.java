package com.example.bioscoopapplicatie.datastorage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
import com.example.bioscoopapplicatie.domain.response.GenreResponse;
import com.example.bioscoopapplicatie.domain.response.MediaListResponse;
import com.example.bioscoopapplicatie.domain.response.MediaResponse;
import com.example.bioscoopapplicatie.domain.response.ReviewResponse;
import com.example.bioscoopapplicatie.domain.response.SessionResponse;
import com.example.bioscoopapplicatie.domain.response.TokenResponse;
import com.example.bioscoopapplicatie.domain.response.UserResponse;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WordRoomDatabase. Includes code to create the database.
 * After the app creates the database, all further interactions
 * with it happen through the WordViewModel.
 */

@Database(entities = {Media.class, MediaList.class, MediaListMedia.class, Review.class, AuthorDetail.class, Genre.class, User.class, GenreMedia.class}, version = 142, exportSchema = false)
public abstract class TheMovieDatabase extends RoomDatabase {
    private final static String TAG = TheMovieDatabase.class.getSimpleName();

    public abstract MediaDAO mediaDao();
    public abstract MediaListDAO mediaListDao();
    public abstract ReviewDAO reviewDao();
    public abstract MediaListMediaDAO mediaListMediaDao();
    public abstract GenreDAO genreDao();
    public abstract AuthorDetailDAO authorDetailDao();
    public abstract UserDAO userDao();
    public abstract GenreMediaDAO genreMediaDao();
    private static TheMovieDatabase INSTANCE;
    public static TheMovieDatabase getDatabase(final Context context) {
        Log.d(TAG, "getDatabase");
        if (INSTANCE == null) {
            synchronized (TheMovieDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TheMovieDatabase.class, "the_movie_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // This callback is called when the database has opened.
    // In this case, use PopulateDbAsync to populate the database
    // with the initial data set if the database has no entries.
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final String TAG = this.getClass().getSimpleName();
        private TheMovieAPI jsonApi;
        private final MediaDAO mediaDao;
        private final MediaListDAO mediaListDao;
        private final ReviewDAO reviewDao;
        private final MediaListMediaDAO mediaListMediaDao;
        private final GenreDAO genreDao;
        private final AuthorDetailDAO authorDetailDao;
        private final UserDAO userDao;
        private final GenreMediaDAO genreMediaDao;
        private int pageNumber;
        private int listId;
        private String apiKey;
        private String userToken;
        private String sessionId;
        private User user;
        PopulateDbAsync(TheMovieDatabase db) {
            mediaDao = db.mediaDao();
            mediaListDao = db.mediaListDao();
            reviewDao = db.reviewDao();
            mediaListMediaDao = db.mediaListMediaDao();
            genreDao = db.genreDao();
            authorDetailDao = db.authorDetailDao();
            userDao = db.userDao();
            genreMediaDao = db.genreMediaDao();
            apiKey = "8a27b4ebdf0d58efaf6c4450b7718cc7";
            userToken = "";
            sessionId = "";
            pageNumber = 1;
            listId = 1;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log.d(TAG, "doInBackground");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            this.jsonApi = retrofit.create(TheMovieAPI.class);

            generateToken();
            authorizeSession();
            createSessionId();

            //Get all genres
            Call<GenreResponse> callGenres = jsonApi.getGenres(apiKey);
            try {
                Response<GenreResponse> genreResponse = callGenres.execute();
                if (genreResponse.isSuccessful()) {
                    for (Genre genre : genreResponse.body().getGenres()) {
                        genreDao.insert(genre);
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "Error whilst trying to get mediaLists from API");
            }

            //Repeat this process 6 times: Go through the pagenumber and add every media object to the database
            for (int i = 0; i < 3; i++) {
                Call<MediaResponse> callMedia = jsonApi.getAllMedia(apiKey, pageNumber);
                Call<MediaResponse> callTvShow = jsonApi.getTvShows(apiKey, pageNumber);
                try {
                    // Execute the first API call
                    Response<MediaResponse> responseMedia = callMedia.execute();
                    if (responseMedia.isSuccessful()) {
                        for (Media media : responseMedia.body().getResult()) {
                            mediaDao.insert(media);
                            int mediaReviewId = media.getId();
                            checkMediaHasReview(mediaReviewId);
                            checkMediaHasGenre(media);
                        }
                    } else {
                        Log.e(TAG, "Error, no access to API");
                    }

                    // Execute the second API call
                    Response<MediaResponse> responseTvShow = callTvShow.execute();
                    if (responseTvShow.isSuccessful()) {
                        for (Media tvShow : responseTvShow.body().getResult()) {
                            mediaDao.insert(tvShow);
                            int tvShowReviewId = tvShow.getId();
                            checkMediaHasReview(tvShowReviewId);
                            checkMediaHasGenre(tvShow);
                        }
                    } else {
                        Log.e(TAG, "Error, no access to TV show API");
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error whilst trying to get media from API");
                    throw new RuntimeException(e);
                }
                pageNumber++;
            }

            //Repeat this process 20 times: Get an API request with an entity called mediaList, for every Media in that object check whether it's already in the database. If the media isn't, then add it to the database
            for (int i = 0; i < 20; i++) {
                Call<MediaListResponse> callMediaLists = jsonApi.getAllMediaLists(listId, apiKey);
                try {
                    Response<MediaListResponse> responseMediaLists = callMediaLists.execute();
                    if (responseMediaLists.isSuccessful()) {
                        MediaList mediaList = new MediaList(
                                responseMediaLists.body().getId(),
                                responseMediaLists.body().getCreatedBy(),
                                responseMediaLists.body().getDescription(),
                                responseMediaLists.body().getFavoriteCount());
                        mediaListDao.insert(mediaList);
                        for (Media media : responseMediaLists.body().getItems()) {
                            if (mediaDao.getMediaById(String.valueOf(media.getId())) == null) {
                                mediaDao.insert(media);
                                int mediaReviewId = media.getId();
                                checkMediaHasReview(mediaReviewId);
                                checkMediaHasGenre(media);
                            }
                            MediaListMedia mediaListMedia = new MediaListMedia(responseMediaLists.body().getId(), media.getId());
                            mediaListMediaDao.insertMediaToList(mediaListMedia);
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error whilst trying to get mediaLists from API");
                    throw new RuntimeException(e);
                }
                listId++;
            }

            Call<UserResponse> callUser = jsonApi.getUser(apiKey, sessionId);
            try {
                Response<UserResponse> userResponse = callUser.execute();
                if (userResponse.isSuccessful()) {
                    user = new User(userResponse.body().getId(), userResponse.body().getUsername(), userResponse.body().getName());
                    user.setSessionId(this.sessionId);
                    userDao.insert(user);
                }
            } catch (IOException e) {
                Log.e(TAG, "Error when trying to get user from API");
            }

            //Get all of the current users lists
            Call<MediaListResponse> callUserMediaLists = jsonApi.getAllMediaListsWithUser(user.getId(), apiKey, sessionId);
            try {
                Response<MediaListResponse> userMediaListsResponse = callUserMediaLists.execute();
                if (userMediaListsResponse.isSuccessful()) {
                    MediaList mediaList = new MediaList(
                            userMediaListsResponse.body().getId(),
                            userMediaListsResponse.body().getCreatedBy(),
                            userMediaListsResponse.body().getDescription(),
                            userMediaListsResponse.body().getFavoriteCount());
                    mediaListDao.insert(mediaList);
                    if (!(userMediaListsResponse.body().getItems() == null)) {
                        for (Media media : userMediaListsResponse.body().getItems()) {
                            if (mediaDao.getAllFilteredMedia("id = " + media.getId()) == null) {
                                mediaDao.insert(media);
                                int mediaReviewId = media.getId();
                                checkMediaHasReview(mediaReviewId);
                            }
                            MediaListMedia mediaListMedia = new MediaListMedia(userMediaListsResponse.body().getId(), media.getId());
                            mediaListMediaDao.insertMediaToList(mediaListMedia);
                        }
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "Error whilst trying to get mediaLists from the user from the API");
            }

            return null;
        }

        //For every media in the database, check whether it has a review attached to it
        private void checkMediaHasReview(int mediaReviewId) {
            Call<ReviewResponse> callReviews = jsonApi.getReviews(mediaReviewId, apiKey);
            try {
                Response<ReviewResponse> reviewResponse = callReviews.execute();
                if (reviewResponse.isSuccessful()) {
                    for (Review review : reviewResponse.body().getResults()) {
                        Review insertReview = review;
                        reviewDao.insert(insertReview);
                        reviewDao.updateReview(String.valueOf(reviewResponse.body().getId()), insertReview.getId());
                        authorDetailDao.insert(review.getAuthorDetails());
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "There is no review for this specific movie");
                throw new RuntimeException(e);
            }
        }

        private void checkMediaHasGenre(Media media) {
            for (int id : media.getGenres()) {
                if (id == genreDao.getFilteredGenre(String.valueOf(id))) {
                    GenreMedia genreMedia = new GenreMedia(id, media.getId());
                    genreMediaDao.insert(genreMedia);
                }
            }
        }

        private void generateToken() {
            Call<TokenResponse> callTokenResponse = this.jsonApi.getToken(apiKey);
            try {
                Response<TokenResponse> tokenResponse = callTokenResponse.execute();
                if (tokenResponse.isSuccessful()) {
                    this.userToken = tokenResponse.body().getToken();
                }
            } catch (IOException e) {
                Log.e(TAG, "Error whilst trying to get token from API");
            }
        }
        private void authorizeSession() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"username\":\"");
            stringBuilder.append("DestroyingBombNL\",");
            stringBuilder.append("\"password\":\"");
            stringBuilder.append("Pizza2017\",");
            stringBuilder.append("\"request_token\":\"");
            stringBuilder.append(this.userToken);
            stringBuilder.append("\"}");
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), stringBuilder.toString());
            Call<TokenResponse> callSessionResponse = this.jsonApi.getSession(apiKey, requestBody);
            try {
                Response<TokenResponse> sessionResponse = callSessionResponse.execute();
                if (sessionResponse.isSuccessful()) {
                    this.userToken = sessionResponse.body().getToken();
                }
            } catch (IOException e) {
                Log.e(TAG, "Error whilst trying to get sessionID from API");
            }
        }

        private void createSessionId() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"request_token\":\"");
            stringBuilder.append(this.userToken);
            stringBuilder.append("\"}");
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), stringBuilder.toString());
            Call<SessionResponse> call = jsonApi.createSession(apiKey, requestBody);
            call.enqueue(new retrofit2.Callback<SessionResponse>() {
                @Override
                public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                    if (response.isSuccessful()) {
                        sessionId = response.body().getSessionId();
                    } else {
                        Log.e(TAG, "An error has occurred when trying to get the token out of the request body");
                    }
                }
                @Override
                public void onFailure(Call<SessionResponse> call, Throwable t) {
                    Log.e(TAG, "An error has occured when trying to make a connection to the api");
                }
            });
        }
    }
}