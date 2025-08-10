package com.bulletin.dto;

import java.time.LocalDate;

public class CommentResponseDto {
    private Long id;
    private String comment;
    private UserResponseDto author;
    private LocalDate createdAt;

    // constructor
    public CommentResponseDto() {}
    public CommentResponseDto(Long id, String comment, UserResponseDto author, LocalDate createdAt) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.createdAt = createdAt;
    }

    // getter
    public Long getId() { return id; }
    public String getComment() { return comment; }
    public UserResponseDto getAuthor() { return author; }
    public LocalDate getCreatedAt() { return createdAt; }
}