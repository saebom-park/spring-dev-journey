package com.todo.dto;

import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import java.time.LocalDateTime;

public class TodoDetailResponseDto {
    private Long id;
    private String content;
    private TodoStatus status;
    private TodoPriority priority;
    private CategoryDto categoryDto;
    private String nickName;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private ScheduleDto scheduleDto;
    private RepeatSettingDto repeatSettingDto;

    // constructor
    public TodoDetailResponseDto() {}
    public TodoDetailResponseDto(Long id, String content, TodoStatus status, TodoPriority priority, CategoryDto categoryDto, String nickName, LocalDateTime createdAt, LocalDateTime completedAt, ScheduleDto scheduleDto, RepeatSettingDto repeatSettingDto) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.priority = priority;
        this.categoryDto = categoryDto;
        this.nickName = nickName;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.scheduleDto = scheduleDto;
        this.repeatSettingDto = repeatSettingDto;
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
    public ScheduleDto getScheduleDto() { return scheduleDto; }
    public RepeatSettingDto getRepeatSettingDto() { return repeatSettingDto; }
}