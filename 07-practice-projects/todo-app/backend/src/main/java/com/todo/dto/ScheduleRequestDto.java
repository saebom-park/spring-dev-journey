package com.todo.dto;

import java.time.LocalDate;

public class ScheduleRequestDto {
    private LocalDate startDate;
    private LocalDate dueDate;

    // constructor
    public ScheduleRequestDto() {}
    public ScheduleRequestDto(LocalDate startDate, LocalDate dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
}