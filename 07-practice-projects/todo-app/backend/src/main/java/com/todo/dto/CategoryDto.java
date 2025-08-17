package com.todo.dto;

public class CategoryDto {
    private Long id;
    private String name;
    private String color;

    // constructor
    public CategoryDto() {}
    public CategoryDto(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
}