package com.example.bioscoopapplicatie.domain.response;

import com.google.gson.annotations.SerializedName;
public class TokenResponse {
    @SerializedName("request_token")
    private String token;
    public String getToken() {
        return this.token;
    }
}
