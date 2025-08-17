package com.todo.dto;

import java.time.LocalDateTime;

public class ScheduleDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    // constructor
    public ScheduleDto() {}
    public ScheduleDto(Long id, LocalDateTime startDate, LocalDateTime dueDate) {
        this.id = id;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public Long getId() { return id; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getDueDate() { return dueDate; }
}