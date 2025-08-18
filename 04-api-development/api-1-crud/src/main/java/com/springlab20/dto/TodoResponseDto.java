package com.springlab20.dto;

public class TodoResponseDto {
    private Long id;
    private String title;
    private boolean completed;

    // constructor
    public TodoResponseDto() {}
    public TodoResponseDto(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    // responseDto는 setter 생략
}