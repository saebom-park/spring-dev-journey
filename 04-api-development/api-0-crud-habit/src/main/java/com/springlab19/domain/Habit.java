package com.springlab19.domain;

import jakarta.persistence.*;

@Entity
@Table(name="habits")
public class Habit {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="habit_id")
    private Long id;

    private String name;

    private int goalPerDay;

    // constructor
    public Habit() {}
    public Habit(String name, int goalPerDay) {
        this.name = name;
        this.goalPerDay = goalPerDay;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getGoalPerDay() { return goalPerDay; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGoalPerDay(int goalPerDay) { this.goalPerDay = goalPerDay; }
}