package com.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private Long id;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="due_date")
    private LocalDateTime dueDate;

    // constructor
    public Schedule(LocalDateTime startDate, LocalDateTime dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // getter
    public Long getId() { return id; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getDueDate() { return dueDate; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
}