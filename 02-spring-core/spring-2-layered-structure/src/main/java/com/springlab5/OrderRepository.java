package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderRepository {
    public void save(Long id) {
        System.out.println("💾 주문 저장됨: ID = " + id);
    }
}