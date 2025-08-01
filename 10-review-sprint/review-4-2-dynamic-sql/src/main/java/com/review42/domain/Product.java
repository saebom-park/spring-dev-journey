package com.review42.domain;

public class Product {
    private Long id;
    private String name;
    private int price;
    private Long categoryId;

    // constructor
    public Product() {}
    public Product(String name, int price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}