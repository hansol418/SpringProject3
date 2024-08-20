package com.busanit501.springproject3.Token;

public class Tokens {
    private String accessToken;
    private String refreshToken;

    public Tokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

