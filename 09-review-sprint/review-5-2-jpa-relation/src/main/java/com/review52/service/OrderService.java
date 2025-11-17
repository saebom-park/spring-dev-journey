package com.review52.service;

import com.review52.dto.OrderRequestDto;
import com.review52.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    List<OrderResponseDto> getOrders();
}