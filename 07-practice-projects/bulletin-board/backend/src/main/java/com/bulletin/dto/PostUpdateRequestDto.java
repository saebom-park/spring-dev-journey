package com.bulletin.dto;

public class PostUpdateRequestDto {
    private String title;
    private String content;

    // constructor
    public PostUpdateRequestDto() {}
    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getter
    public String getTitle() { return title; }
    public String getContent() { return content; }
}