package com.example.bioscoopapplicatie.datastorage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bioscoopapplicatie.datastorage.dao.MediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaListDAO;
import com.example.bioscoopapplicatie.datastorage.dao.MediaListMediaDAO;
import com.example.bioscoopapplicatie.datastorage.dao.ReviewDAO;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;
import com.example.bioscoopapplicatie.domain.response.MediaListResponse;
import com.example.bioscoopapplicatie.domain.response.MediaResponse;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.domain.response.ReviewResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WordRoomDatabase. Includes code to create the database.
 * After the app creates the database, all further interactions
 * with it happen through the WordViewModel.
 */

@Database(entities = {Media.class, MediaList.class, MediaListMedia.class, Review.class}, version = 30, exportSchema = false)
public abstract class TheMovieDatabase extends RoomDatabase {
    private final static String TAG = TheMovieDatabase.class.getSimpleName();

    public abstract MediaDAO mediaDao();
    public abstract MediaListDAO mediaListDao();
    public abstract ReviewDAO reviewDao();
    public abstract MediaListMediaDAO mediaListMediaDao();

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
        private final MediaDAO mediaDao;
        private final MediaListDAO mediaListDao;
        private final ReviewDAO reviewDao;
        private final MediaListMediaDAO mediaListMediaDao;
        private int pageNumber;
        private int listId;
        private int mediaReviewId;
        private String apiKey;
        PopulateDbAsync(TheMovieDatabase db) {
            mediaDao = db.mediaDao();
            mediaListDao = db.mediaListDao();
            reviewDao = db.reviewDao();
            mediaListMediaDao = db.mediaListMediaDao();
            apiKey = "8a27b4ebdf0d58efaf6c4450b7718cc7";
            pageNumber = 1;
            listId = 1;
            mediaReviewId = 1;
        }
        //Repeat this process 6 times: Go through the pagenumber and add every media object to the database
        @Override
        protected Void doInBackground(final Void... params) {
            Log.d(TAG, "doInBackground");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TheMovieAPI jsonApi = retrofit.create(TheMovieAPI.class);

            for (int i = 0; i < 6; i++) {
                Call<MediaResponse> callMedia = jsonApi.getAllMedia(apiKey, pageNumber);
                try {
                    Response<MediaResponse> response = callMedia.execute();
                    Log.e(TAG, String.valueOf(response.code()));
                    if (response.isSuccessful()) {
                        for (Media media : response.body().getResult()) {
                            mediaDao.insert(media);
                            mediaReviewId = media.getId();
                            Call<ReviewResponse> callReviews = jsonApi.getReviews(mediaReviewId, apiKey);
                            try {
                                Response<ReviewResponse> reviewResponse = callReviews.execute();
                                if (reviewResponse.isSuccessful()) {
                                    for (Review review : reviewResponse.body().getResults()) {
                                        reviewDao.insert(review);
                                    }
                                }
                            } catch (IOException e) {
                                Log.e(TAG, "THere is no review for this specific movie");
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        Log.e(TAG, "Error, no access to API");
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
                    Log.e(TAG, String.valueOf(responseMediaLists.code()));
                    if (responseMediaLists.isSuccessful()) {
                        MediaList mediaList = new MediaList(
                                responseMediaLists.body().getId(),
                                responseMediaLists.body().getCreatedBy(),
                                responseMediaLists.body().getDescription(),
                                responseMediaLists.body().getFavoriteCount());
                        mediaListDao.insert(mediaList);
                        for (Media media : responseMediaLists.body().getItems()) {
                            if (mediaDao.getAllFilteredMedia("id = " + media.getId()) == null) {
                                mediaDao.insert(media);
                                mediaReviewId = media.getId();
                                Call<ReviewResponse> callReviews = jsonApi.getReviews(mediaReviewId, apiKey);
                                try {
                                    Response<ReviewResponse> reviewResponse = callReviews.execute();
                                    if (reviewResponse.isSuccessful()) {
                                        for (Review review : reviewResponse.body().getResults()) {
                                            reviewDao.insert(review);
                                        }
                                    }
                                } catch (IOException e) {
                                    Log.e(TAG, "THere is no review for this specific movie");
                                    throw new RuntimeException(e);
                                }
                            }
                            MediaListMedia mediaListMedia = new MediaListMedia(responseMediaLists.body().getId(), media.getId());
                            mediaListMediaDao.insert(mediaListMedia);
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error whilst trying to get mediaLists from API");
                    throw new RuntimeException(e);
                }
                listId++;
            }
            //For every media in the database, check whether it has a review attached to it
            return null;
        }
    }
}