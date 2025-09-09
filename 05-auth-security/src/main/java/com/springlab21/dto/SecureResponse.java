package com.springlab21.dto;

public class SecureResponse {
    private String message;
    private String username;

    // constructor
    public SecureResponse() {}
    public SecureResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    // getter
    public String getMessage() { return message; }
    public String getUsername() { return username; }
}