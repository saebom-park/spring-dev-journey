package com.springlab21.service;

public interface UserService {
    boolean authenticate(String username, String password);
}