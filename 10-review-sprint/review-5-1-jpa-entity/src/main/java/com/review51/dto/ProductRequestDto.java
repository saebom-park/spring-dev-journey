package com.review51.dto;

public class ProductRequestDto {
    private String name;
    private int price;
    
    // constructor
    public ProductRequestDto() {}
    public ProductRequestDto(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    // getter
    public String getName() { return name; }
    public int getPrice() { return price; }
    
    // requestDto는 setter 불필요
}