package com.review52.dto;

public class OrderItemDto {
    private Long productId;
    private int quantity;
    private int orderPrice;

    // constructor
    public OrderItemDto() {}
    public OrderItemDto(Long productId, int quantity, int orderPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    // getter
    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public int getOrderPrice() { return orderPrice; }

    // setter
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setOrderPrice(int orderPrice) { this.orderPrice = orderPrice; }
}