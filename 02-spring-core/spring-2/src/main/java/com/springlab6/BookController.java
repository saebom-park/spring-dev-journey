package com.springlab6;

import org.springframework.stereotype.Component;

@Component
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void placeBook(Long id) {
        System.out.println("📚 도서 등록 요청: ID = " + id);
        bookService.processBook(id);
    }
}