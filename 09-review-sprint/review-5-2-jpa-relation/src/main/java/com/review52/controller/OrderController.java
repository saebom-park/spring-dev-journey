package com.review52.controller;

import com.review52.service.OrderService;
import com.review52.dto.OrderRequestDto;
import com.review52.dto.OrderResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    // constructor
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders() {
        return orderService.getOrders();
    }
}