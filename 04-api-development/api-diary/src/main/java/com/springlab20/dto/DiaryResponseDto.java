package com.springlab20.dto;

import java.time.LocalDateTime;

public class DiaryResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    // constructor
    public DiaryResponseDto() {}
    public DiaryResponseDto(Long id, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedDate() { return createdDate; }
}