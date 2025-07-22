package com.review11;

public class Book implements Printable {
    private String title;
    private String author;
    private int price;
    public static int totalBookCount = 0;

    // Constructor
    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
        totalBookCount++;
    }

    // getter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPrice() { return price; }

    @Override
    public String toString() {
        return "- 제목: " + title + ", 저자: " + author + ", 가격: " + price;
    }

    @Override
    public void printInfo() {
        System.out.println(this);
    }
}