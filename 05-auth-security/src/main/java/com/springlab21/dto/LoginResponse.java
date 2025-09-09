package com.springlab21.dto;

public class LoginResponse {
    private String message;
    private String token;

    // constructor
    public LoginResponse() {}
    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // getter
    public String getMessage() { return message; }
    public String getToken() { return token; }
}