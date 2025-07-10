package com.springlab11;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrderController {

    @PostMapping("/orders")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return new OrderResponseDto(
                requestDto.getItem(),
                requestDto.getQuantity(),
                "주문이 성공적으로 접수되었습니다."
        );
    }
}