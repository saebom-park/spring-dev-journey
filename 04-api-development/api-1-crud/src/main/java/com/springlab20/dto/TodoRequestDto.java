package com.springlab20.dto;

public class TodoRequestDto {
    private String title;
    private boolean completed;

    // constructor
    public TodoRequestDto() {}
    public TodoRequestDto(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    // getter
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    // requestDto는 setter 생략
}