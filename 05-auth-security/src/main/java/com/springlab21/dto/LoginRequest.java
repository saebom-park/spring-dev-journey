package com.springlab21.dto;

public class LoginRequest {
    private String username;
    private String password;

    // constructor
    public LoginRequest() {}
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}