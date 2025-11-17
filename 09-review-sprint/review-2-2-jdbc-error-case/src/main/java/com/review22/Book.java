package com.review22;

public class Book {
    private int id;
    private String title;
    private String author;
    private int price;

    // constructor
    public Book() {}
    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // getter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPrice() { return price; }

    // setter
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return "- 책 번호: " + id + " / 책 제목: " + title + " / 저자: " + author + " / 정가: " + price;
    }
}