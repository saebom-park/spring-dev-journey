package com.todo.dto;

import java.time.LocalDateTime;

public class ScheduleUpdateRequestDto {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    // constructor
    public ScheduleUpdateRequestDto() {}
    public ScheduleUpdateRequestDto(LocalDateTime startDate, LocalDateTime dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getDueDate() { return dueDate; }
}