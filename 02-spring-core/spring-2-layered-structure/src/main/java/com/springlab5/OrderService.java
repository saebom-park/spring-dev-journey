package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void processOrder(Long id) {
        orderRepository.save(id);
        System.out.println("📦 주문 처리 완료: ID = " + id);
    }
}