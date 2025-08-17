package com.todo.dto;

public class CategoryCreateRequestDto {
    private String name;
    private String color;

    // constructor
    public CategoryCreateRequestDto() {}
    public CategoryCreateRequestDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // getter
    public String getName() { return name; }
    public String getColor() { return color; }
}