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
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.domain.User;
import com.example.shareameal.domain.Meal;
import com.example.shareameal.domain.User;
import com.example.shareameal.presentation.viewmodel.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MediaRepository {
    private final String TAG = this.getClass().getSimpleName();
    private MediaDAO mediaDAO;
    private GenreDAO genreDAO;
    private MediaListDAO mediaListDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private LiveData<List<Media>> allMedia;
    private LiveData<List<Genre>> allGenres;
    private LiveData<List<MediaList>> allMediaLists;
    private LiveData<List<Review>> allReviews;
    private LiveData<User> user;

    public MediaRepository(Application application) {
        TheMovieDatabase db = TheMovieDatabase.getDatabase(application);
        mediaDAO = db.mediaDao();
        mediaDAO.getAllMeals();
    }

    public void postAMeal(Meal newMeal, ViewModelStoreOwner viewModelStoreOwner, Application application) {
        MealRepository mealRepositoryDatabase = new MealRepository(application);
        ViewModelProvider provider = new ViewModelProvider(viewModelStoreOwner, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(application));
        UserViewModel mUserViewModel = provider.get(UserViewModel.class);
        User user = mUserViewModel.getUserData().getValue();
        Gson gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://shareameal-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        JsonApi service = retrofit.create(JsonApi.class);

        String token = "Bearer " + user.getToken();
        Call<Void> call = service.postMeal(token, newMeal);
        Log.d(TAG, user.getToken());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Meal posted successfully with status code: " + response.code());
                    mealRepositoryDatabase.insert(newMeal);
                } else {
                    Log.i(TAG, "The request did not register with status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }
    public LiveData<List<Meal>> getVegetarianMeals() {
        allMeals = mealDao.getVegetarianMeals();
        return allMeals;
    }

    public LiveData<List<Meal>> getVeganMeals() {
        allMeals = mealDao.getVeganMeals();
        return allMeals;
    }

    public LiveData<List<Meal>> getTakeHomeMeals() {
        allMeals = mealDao.getTakeHomeMeals();
        return allMeals;
    }

    public LiveData<List<Meal>> getAllMeals() {
        allMeals = mealDao.getAllMeals();
        return allMeals;
    }

    public void insert(Meal meal) {
        new insertAsyncTask(mealDao).execute(meal);
    }

    // Static inner classes below here to run database interactions in the background.

    /**
     * Inserts a meal into the database.
     */
    private static class insertAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDao mAsyncTaskDao;

        insertAsyncTask(MealDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
