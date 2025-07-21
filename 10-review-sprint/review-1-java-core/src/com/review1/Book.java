package com.review1;

public class Book {
    private String name;
    private String author;
    private int price;
    public static int totalBookCount = 0;

    // Constructor
    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    // getter
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public int getPrice() { return price; }

    @Override
    public String toString() {
        return "- 제목: " + getName() + ", 저자: " + getAuthor() + ", 가격: " + getPrice();
    }

}