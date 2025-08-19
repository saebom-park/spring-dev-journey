package com.springlab12;

public class ProductResponseDto {
    private String name;
    private int price;
    private String message;

    public ProductResponseDto(String name, int price, String message) {
        this.name = name;
        this.price = price;
        this.message = message;
    }

    public String getName() {return name;}
    public int getPrice() {return price;}
    public String getMessage() {return message;}
}