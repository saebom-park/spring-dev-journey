package com.springlab8;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) {
        return new BookDto(bookId, "스프링 완전 정복", "온이");
    }
}