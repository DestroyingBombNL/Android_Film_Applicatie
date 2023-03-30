package com.example.bioscoopapplicatie.datastorage;

import com.example.bioscoopapplicatie.domain.LoginResponse;
import com.example.bioscoopapplicatie.domain.MediaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TheMovieAPI {
    @GET("discover/movie?api_key=8a27b4ebdf0d58efaf6c4450b7718cc7")
    Call<MediaResponse> getAllMedia();

    /*
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body() LoginData loginData);

    @GET("api/user/profile")
    Call<LoginResponse> getUserProfile(@Header("Authorization") String token);*/
}