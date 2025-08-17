package com.todo.dto;

public class CategoryResponseDto {
    private Long id;
    private String name;
    private String color;
    private String nickName;

    // constructor
    public CategoryResponseDto() {}
    public CategoryResponseDto(Long id, String name, String color, String nickName) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.nickName = nickName;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public String getNickName() { return nickName; }
}