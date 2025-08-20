package com.todo.dto;

import java.time.LocalDateTime;

public class ScheduleRequestDto {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    // constructor
    public ScheduleRequestDto() {}
    public ScheduleRequestDto(LocalDateTime startDate, LocalDateTime dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getDueDate() { return dueDate; }
}