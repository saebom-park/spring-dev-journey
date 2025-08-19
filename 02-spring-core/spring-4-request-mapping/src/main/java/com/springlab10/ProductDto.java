package com.springlab10;

public class ProductDto {
    private Long productId;
    private String name;
    private int price;

    public ProductDto(Long productId, String name, int price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Long getProductId() {return productId;}
    public String getName() {return name;}
    public int getPrice() {return price;}
}