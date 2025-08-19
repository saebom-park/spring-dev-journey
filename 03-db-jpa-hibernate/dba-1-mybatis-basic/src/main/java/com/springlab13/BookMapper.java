package com.springlab13;

import java.util.List;

public interface BookMapper {
    List<Book> findAll();
    Book findById(int id);
    void insert(Book book);
}