package com.bulletin.dto;

import java.time.LocalDate;

public class PostListResponseDto {
    private Long id;
    private String title;
    private UserResponseDto author;
    private LocalDate createdAt;
    private int views;

    // constructor
    public PostListResponseDto() {}
    public PostListResponseDto(Long id, String title, UserResponseDto author, LocalDate createdAt, int views) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.views = views;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public UserResponseDto getAuthor() { return author; }
    public LocalDate getCreatedAt() { return createdAt; }
    public int getViews() { return views; }
}