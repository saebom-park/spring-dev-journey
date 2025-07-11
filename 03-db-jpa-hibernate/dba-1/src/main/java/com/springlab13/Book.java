package com.springlab13;

public class Book {
    private int id;
    private String title;
    private String author;

    // getter
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "'}";
    }
}