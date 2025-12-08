package com.saebom.bulletinboard.dto.member;

import com.saebom.bulletinboard.validation.Password;

public class PasswordCheckForm {

    @Password
    private String password;

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}