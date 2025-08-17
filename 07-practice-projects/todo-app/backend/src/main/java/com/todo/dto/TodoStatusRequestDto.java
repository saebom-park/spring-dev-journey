package com.todo.dto;

import com.todo.enums.TodoStatus;

public class TodoStatusRequestDto {
    private TodoStatus status;

    // constructor
    public TodoStatusRequestDto() {}
    public TodoStatusRequestDto(TodoStatus status) { this.status = status; }

    // getter
    public TodoStatus getStatus() { return status; }
}