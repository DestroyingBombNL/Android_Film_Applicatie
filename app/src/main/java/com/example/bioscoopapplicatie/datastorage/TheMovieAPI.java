package com.example.bioscoopapplicatie.datastorage;

import com.example.bioscoopapplicatie.domain.LoginResponse;
import com.example.bioscoopapplicatie.domain.MediaListResponse;
import com.example.bioscoopapplicatie.domain.MediaResponse;
import com.example.bioscoopapplicatie.domain.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieAPI {
    @GET("discover/movie")
    Call<MediaResponse> getAllMedia(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("list/{list_id}")
    Call<MediaListResponse> getAllMediaLists(@Path("list_id") int listId, @Query("api_key") String apiKey);
    @GET("movie/{media_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("media_id") int mediaId, @Query("api_key") String apiKey);
    /*
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body() LoginData loginData);

    @GET("api/user/profile")
    Call<LoginResponse> getUserProfile(@Header("Authorization") String token);*/
}