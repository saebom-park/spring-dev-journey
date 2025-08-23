package com.todo.dto;

import java.time.LocalDate;

public class ScheduleDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate dueDate;

    // constructor
    public ScheduleDto() {}
    public ScheduleDto(Long id, LocalDate startDate, LocalDate dueDate) {
        this.id = id;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public Long getId() { return id; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
}