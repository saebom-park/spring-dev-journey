package com.springlab14.practice;

public class Order {
    private int id;
    private String item_name;
    private int price;

    // getter
    public int getId() {return id;}
    public String getItemName() {return item_name;}
    public int getPrice() {return price;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setItemName(String item_name) {this.item_name = item_name;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        return "Order{id=" + id + ", item_name='" + item_name + "', price=" + price + "}";
    }
}