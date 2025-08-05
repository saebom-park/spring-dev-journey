package com.review52.dto;

import com.review52.domain.OrderItem;
import java.util.List;
import java.util.ArrayList;

public class OrderResponseDto {
    private Long orderId;
    private String memberName;
    private String orderDate;
    private String status;
    private List<OrderItemDto> items;

    // constructor
    public OrderResponseDto() {}
    public OrderResponseDto(Long orderId, String memberName, String orderDate, String status, List<OrderItemDto> items) {
        this.orderId = orderId;
        this.memberName = memberName;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
    }

    // getter
    public Long getOrderId() { return orderId; }
    public String getMemberName() { return memberName; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public List<OrderItemDto> getItems() { return items; }

    // setter
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public void setStatus(String status) { this.status = status; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }
}