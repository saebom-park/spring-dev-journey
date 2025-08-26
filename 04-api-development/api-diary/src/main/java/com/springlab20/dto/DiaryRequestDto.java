package com.springlab20.dto;

import jakarta.validation.constraints.NotBlank;

public class DiaryRequestDto {
    @NotBlank(message="다이어리 제목을 입력해 주세요.")
    private String title;

    @NotBlank(message="다이어리 내용을 입력해 주세요.")
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