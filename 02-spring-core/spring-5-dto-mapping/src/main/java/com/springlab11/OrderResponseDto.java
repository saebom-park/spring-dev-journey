package com.springlab11;

public class OrderResponseDto {
    private String item;
    private int quantity;
    private String message;

    public OrderResponseDto(String item, int quantity, String message) {
        this.item = item;
        this.quantity = quantity;
        this.message = message;
    }

    public String getItem() {return item;}
    public int getQuantity() {return quantity;}
    public String getMessage() {return message;}
}