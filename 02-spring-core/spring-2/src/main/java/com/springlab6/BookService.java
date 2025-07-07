package com.springlab6;

import org.springframework.stereotype.Component;

@Component
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBook(Long id) {
        bookRepository.save(id);
        System.out.println("✅ 도서 등록 완료: ID = " + id);
    }
}