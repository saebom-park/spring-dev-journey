package com.springlab20.dto;

public class DiaryRequestDto {
    private String title;
    private String content;

    // constructor
    public DiaryRequestDto() {}
    public DiaryRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getter
    public String getTitle() { return title; }
    public String getContent() { return content; }
}