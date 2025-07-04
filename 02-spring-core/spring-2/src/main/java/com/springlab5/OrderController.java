package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void placeOrder(Long id) {
        System.out.println("🧾 주문 접수됨: ID = " + id);
        orderService.processOrder(id);
    }

}