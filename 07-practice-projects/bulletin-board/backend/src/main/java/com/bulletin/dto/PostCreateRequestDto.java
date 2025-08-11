package com.bulletin.dto;

public class PostCreateRequestDto {
    private String title;
    private String content;

    // constructor
    public PostCreateRequestDto() {}
    public PostCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getter
    public String getTitle() { return title; }
    public String getContent() { return content; }
}