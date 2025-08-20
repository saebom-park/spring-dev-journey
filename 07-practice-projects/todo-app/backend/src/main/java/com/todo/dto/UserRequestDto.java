package com.todo.dto;

public class UserRequestDto {
    private String userName;
    private String nickName;

    // constructor
    public UserRequestDto() {}
    public UserRequestDto(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    // getter
    public String getUserName() { return userName; }
    public String getNickName() { return nickName; }
}