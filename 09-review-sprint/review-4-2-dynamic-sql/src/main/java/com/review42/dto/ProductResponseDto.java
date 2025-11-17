package com.review42.dto;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String categoryName;

    // constructor
    public ProductResponseDto() {}
    public ProductResponseDto(Long id, String name, int price, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getCategoryName() { return categoryName; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}