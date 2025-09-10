package com.springlab21.dto;

public class TokenResponse {
    private String message;
    private String accessToken;
    private String refreshToken;

    // constructor
    public TokenResponse() {}
    public TokenResponse(String message, String accessToken, String refreshToken) {
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getMessage() { return message; }
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
}