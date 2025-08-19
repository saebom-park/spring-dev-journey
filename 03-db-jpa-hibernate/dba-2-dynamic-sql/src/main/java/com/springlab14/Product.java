package com.springlab14;

public class Product {
    private int id;
    private String name;
    private int price;

    // getter
    public int getId() {return id;}
    public String getName() {return name;}
    public int getPrice() {return price;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}