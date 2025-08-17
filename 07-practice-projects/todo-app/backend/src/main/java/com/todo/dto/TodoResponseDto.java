package com.todo.dto;

import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import java.time.LocalDateTime;

public class TodoResponseDto {
    private Long id;
    private String content;
    private TodoStatus status;
    private TodoPriority priority;
    private CategoryDto categoryDto;
    private String nickName;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private boolean hasSchedule;
    private boolean hasRepeat;

    // constructor
    public TodoResponseDto() {}
    public TodoResponseDto(Long id, String content, TodoStatus status, TodoPriority priority, CategoryDto categoryDto, String nickName, LocalDateTime createdAt, LocalDateTime completedAt, boolean hasSchedule, boolean hasRepeat) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.priority = priority;
        this.categoryDto = categoryDto;
        this.nickName = nickName;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.hasSchedule = hasSchedule;
        this.hasRepeat = hasRepeat;
    }

    // getter
    public Long getId() { return id; }
    public String getContent() { return content; }
    public TodoStatus getStatus() { return status; }
    public TodoPriority getPriority() { return priority; }
    public CategoryDto getCategoryDto() { return categoryDto; }
    public String getNickName() { return nickName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public boolean getHasSchedule() { return hasSchedule; }
    public boolean getHasRepeat() { return hasRepeat; }

    // setter
    public void setHasSchedule(boolean hasSchedule) { this.hasSchedule = hasSchedule; }
    public void setHasRepeat(boolean hasRepeat) { this.hasRepeat = hasRepeat; }
}