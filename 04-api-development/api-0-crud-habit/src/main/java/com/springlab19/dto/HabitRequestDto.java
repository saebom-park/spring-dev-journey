package com.springlab19.dto;

public class HabitRequestDto {
    private String name;
    private int goalPerDay;

    // constructor
    public HabitRequestDto() {}
    public HabitRequestDto(String name, int goalPerDay) {
        this.name = name;
        this.goalPerDay = goalPerDay;
    }

    // getter
    public String getName() { return name; }
    public int getGoalPerDay() { return goalPerDay; }
    
    // requestDto는 setter 생략
}