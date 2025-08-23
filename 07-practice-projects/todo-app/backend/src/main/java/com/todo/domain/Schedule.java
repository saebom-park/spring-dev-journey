package com.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private Long id;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="due_date")
    private LocalDate dueDate;

    // constructor
    public Schedule() {}
    public Schedule(LocalDate startDate, LocalDate dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public Long getId() { return id; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}