package com.review52.dto;

import com.review52.domain.OrderItem;
import java.util.List;
import java.util.ArrayList;

public class OrderRequestDto {
    private Long memberId;
    private List<OrderItemDto> items;

    // constructor
    public OrderRequestDto() {}
    public OrderRequestDto(Long memberId, List<OrderItemDto> items) {
        this.memberId = memberId;
        this.items = items;
    }

    // getter
    public Long getMemberId() { return memberId; }
    public List<OrderItemDto> getItems() { return items; }
    
    // requestDto는 setter 불필요
}