package com.review43.dto;

public class CategoryDto {
    private Long id;
    private String name;

    // constructor
    public CategoryDto() {}
    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}