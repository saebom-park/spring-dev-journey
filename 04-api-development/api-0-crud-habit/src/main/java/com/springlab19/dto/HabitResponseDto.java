package com.springlab19.dto;

public class HabitResponseDto {
    private Long id;
    private String name;
    private int goalPerDay;

    // constructor
    public HabitResponseDto() {}
    public HabitResponseDto(Long id, String name, int goalPerDay) {
        this.id = id;
        this.name = name;
        this.goalPerDay = goalPerDay;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getGoalPerDay() { return goalPerDay; }
    
    // responseDto는 setter 생략
}