package com.saebom.bulletinboard.dto.member;

import com.saebom.bulletinboard.validation.Password;

public class PasswordCheckForm {

    @Password
    private String password;

    // getter
    public String getPassword() { return password; }

    // setter
    public void setPassword(String password) { this.password = password; }

}