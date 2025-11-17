package com.review43.dto;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private CategoryDto category;

    // constructor
    public ProductResponseDto() {}
    public ProductResponseDto(Long id, String name, int price, CategoryDto category) {
        this.id= id;
        this.name= name;
        this.price = price;
        this.category = category;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public CategoryDto getCategory() { return category; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategory(CategoryDto category) { this.category = category; }
}