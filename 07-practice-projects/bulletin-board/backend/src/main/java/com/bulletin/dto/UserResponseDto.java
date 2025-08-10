package com.bulletin.dto;

public class UserResponseDto {
    private Long id;
    private String nickName;

    // constructor
    public UserResponseDto() {}
    public UserResponseDto(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    // getter
    public Long getId() { return id; }
    public String getNickName() { return nickName; }
}