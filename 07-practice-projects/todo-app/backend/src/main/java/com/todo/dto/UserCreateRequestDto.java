package com.todo.dto;

public class UserCreateRequestDto {
    private String userName;
    private String nickName;

    // constructor
    public UserCreateRequestDto() {}
    public UserCreateRequestDto(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    // getter
    public String getUserName() { return userName; }
    public String getNickName() { return nickName; }
}