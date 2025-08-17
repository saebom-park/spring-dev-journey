package com.todo.dto;

import com.todo.enums.TodoPriority;

public class TodoUpdateRequestDto {
    private String content;
    private TodoPriority priority;
    private Long categoryId;

    // constructor
    public TodoUpdateRequestDto() {}
    public TodoUpdateRequestDto(String content, TodoPriority priority, Long categoryId) {
        this.content = content;
        this.priority = priority;
        this.categoryId = categoryId;
    }

    // getter
    public String getContent() { return content; }
    public TodoPriority getPriority() { return priority; }
    public Long getCategoryId() { return categoryId; }
}