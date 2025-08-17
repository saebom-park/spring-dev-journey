package com.todo.dto;

import java.time.LocalDateTime;

public class UserResponseDto {
    private Long id;
    private String userName;
    private String nickName;
    private LocalDateTime createdAt;

    // constructor
    public UserResponseDto() {}
    public UserResponseDto(Long id, String userName, String nickName, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.createdAt = createdAt;
    }

    // getter
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getNickName() { return nickName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}