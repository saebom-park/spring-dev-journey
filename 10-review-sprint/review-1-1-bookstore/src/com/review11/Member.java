package com.review11;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String email;

    List<Book> books = new ArrayList<>();

    // Constructor
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Book> getBooks() { return books; }

    public void buyBook(Book book) throws InvalidPriceException {
        if (book.getPrice() < 0) {
            throw new InvalidPriceException();
        }

        books.add(book);
    }
}