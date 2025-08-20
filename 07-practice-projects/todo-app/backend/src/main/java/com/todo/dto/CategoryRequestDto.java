package com.todo.dto;

public class CategoryRequestDto {
    private String name;
    private String color;

    // constructor
    public CategoryRequestDto() {}
    public CategoryRequestDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // getter
    public String getName() { return name; }
    public String getColor() { return color; }
}