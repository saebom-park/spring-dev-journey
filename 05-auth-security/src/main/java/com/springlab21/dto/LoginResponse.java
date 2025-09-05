package com.springlab21.dto;

public class LoginResponse {
    private String message;
    private String username;

    // constructor
    public LoginResponse() {}
    public LoginResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    // getter
    public String getMessage() { return message; }
    public String getUsername() { return username; }
}