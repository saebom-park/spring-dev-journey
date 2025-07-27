package com.review23;

public class Order {
    private Long id;
    private String productName;
    private int quantity;
    private int price;

    // constructor
    public Order() {}
    public Order(String productName, int quantity, int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // getter
    public Long getId() { return id; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price;}

    // setter
    public void setId(Long id) { this.id = id; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return "[주문 내역]\n- 주문 번호: " + id + " / 상품명: " + productName + " / 수량: " + quantity + " / 가격: " + price;
    }
}