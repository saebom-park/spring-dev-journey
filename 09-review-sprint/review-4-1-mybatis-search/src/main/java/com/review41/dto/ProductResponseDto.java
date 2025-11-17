package com.review41.dto;

public class ProductResponseDto {
    private String name;
    private int price;
    private String categoryName;

    // constructor
    public ProductResponseDto() {}
    public ProductResponseDto(String name, int price, String categoryName) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    // getter
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getCategoryName() { return categoryName; }

    // setter
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}