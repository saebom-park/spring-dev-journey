package com.todo.dto;

import com.todo.enums.TodoStatus;
import java.time.LocalDateTime;

public class TodoUpdateResponseDto {
    private Long id;
    private TodoStatus status;
    private LocalDateTime completedAt;

    // constructor
    public TodoUpdateResponseDto() {}
    public TodoUpdateResponseDto(Long id, TodoStatus status, LocalDateTime completedAt) {
        this.id = id;
        this.status = status;
        this.completedAt = completedAt;
    }

    // getter
    public Long getId() { return id; }
    public TodoStatus getStatus() { return status; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}