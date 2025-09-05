package com.springlab21.service;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public boolean authenticate(String username, String password) {
        return "spring".equals(username) && "1234".equals(password);
    }
}