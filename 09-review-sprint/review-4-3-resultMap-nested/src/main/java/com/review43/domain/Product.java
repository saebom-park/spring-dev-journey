package com.review43.domain;

public class Product {
    private Long id;
    private String name;
    private int price;
    private Category category;

    // constructor
    public Product() {}
    public Product(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public Category getCategory() { return category; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategory(Category category) { this.category = category; }
}