package com.saebom.bulletinboard.dto.member;

import com.saebom.bulletinboard.validation.Username;

public class UsernameCheckForm {

    @Username
    private String username;

    public UsernameCheckForm() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}