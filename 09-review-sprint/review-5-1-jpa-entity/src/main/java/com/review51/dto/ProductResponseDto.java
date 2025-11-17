package com.review51.dto;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;

    // constructor
    public ProductResponseDto() {}
    public ProductResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
}