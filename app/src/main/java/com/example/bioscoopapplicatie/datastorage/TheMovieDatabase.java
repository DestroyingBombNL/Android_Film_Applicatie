package com.example.bioscoopapplicatie.datastorage;

import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WordRoomDatabase. Includes code to create the database.
 * After the app creates the database, all further interactions
 * with it happen through the WordViewModel.
 */

@Database(entities = {Media.class}, version = 28, exportSchema = false)
public abstract class TheMovieDatabase extends RoomDatabase {
    private final static String TAG = TheMovieDatabase.class.getSimpleName();

    public abstract MediaDAO mediaDao();

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
        PopulateDbAsync(TheMovieDatabase db) {
            mediaDao = db.mediaDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            Log.d(TAG, "doInBackground");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TheMovieAPI jsonApi = retrofit.create(TheMovieAPI.class);
            Call<MediaResponse> call = jsonApi.getAllMedia();
            try {
                Response<MediaResponse> response = call.execute();
                Log.e(TAG, String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    for (Media media : response.body().getResult()) {
                        mediaDao.insert(media);
                    }
                } else {
                    Log.e(TAG, "Error, no access to API");
                }
            } catch (IOException e) {
                Log.e(TAG, "Error whilst trying to get meals from API");
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}