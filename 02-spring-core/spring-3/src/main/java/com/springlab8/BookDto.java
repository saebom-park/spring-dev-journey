package com.springlab8;

public class BookDto {
    private Long bookId;
    private String title;
    private String author;

    public BookDto(Long bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public Long getBookId() {return bookId;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
}