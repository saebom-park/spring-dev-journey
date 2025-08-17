package com.todo.dto;

public class CategoryUpdateRequestDto {
    private String name;
    private String color;

    // constructor
    public CategoryUpdateRequestDto() {}
    public CategoryUpdateRequestDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // getter
    public String getName() { return name; }
    public String getColor() { return color;  }
}