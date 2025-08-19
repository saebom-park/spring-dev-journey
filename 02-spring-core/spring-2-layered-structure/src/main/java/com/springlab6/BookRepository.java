package com.springlab6;

import org.springframework.stereotype.Component;

@Component
public class BookRepository {
    public void save(Long id) {
        System.out.println("💾 도서 저장 완료: ID = " + id);
    }
}