package com.bulletin.dto;

import com.bulletin.domain.Comment;
import java.time.LocalDate;
import java.util.List;

public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private UserResponseDto author;
    private LocalDate createdAt;
    private int views;
    private List<CommentResponseDto> comments;

    // constructor
    public PostDetailResponseDto() {}
    public PostDetailResponseDto(Long id, String title, String content, UserResponseDto author, LocalDate createdAt, int views, List<CommentResponseDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.views = views;
        this.comments = comments;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public UserResponseDto getAuthor() { return author; }
    public LocalDate getCreatedAt() { return createdAt; }
    public int getViews() { return views; }
    public List<CommentResponseDto> getComments() { return comments; }

}