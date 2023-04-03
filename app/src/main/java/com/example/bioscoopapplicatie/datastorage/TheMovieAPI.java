package com.example.bioscoopapplicatie.datastorage;

import com.example.bioscoopapplicatie.domain.response.UserResponse;
import com.example.bioscoopapplicatie.domain.response.GenreResponse;
import com.example.bioscoopapplicatie.domain.response.MediaListResponse;
import com.example.bioscoopapplicatie.domain.response.MediaResponse;
import com.example.bioscoopapplicatie.domain.response.ReviewResponse;
import com.example.bioscoopapplicatie.domain.response.TokenResponse;

import okhttp3.RequestBody;
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
    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey);
    @GET("authentication/token/new")
    Call<TokenResponse> getToken(@Query("api_key") String apiKey);
    @POST("authentication/token/validate_with_login")
    Call<TokenResponse> getSession(@Query("api_key") String apiKey, @Body RequestBody body);
    @GET("account")
    Call<UserResponse> getUser(@Query("api_key") String apiKey, @Query("session_id") String sessionId);
    @GET("account/{account_id}/lists")
    Call<MediaListResponse> getAllMediaListsWithUser(@Path("account_id") int listId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);
    @POST("list")
    Call<Void> postMediaList(@Header("Content-Type") String contentType, @Query("api_key") String apiKey, @Query("session_id") String sessionId, @Body RequestBody body);
    /*
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body() LoginData loginData);

    @GET("api/user/profile")
    Call<LoginResponse> getUserProfile(@Header("Authorization") String token);*/
}