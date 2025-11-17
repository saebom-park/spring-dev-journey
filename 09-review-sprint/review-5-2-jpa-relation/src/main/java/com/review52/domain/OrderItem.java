package com.review52.domain;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    private int quantity;

    @Column(name="order_price")
    private int orderPrice;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    // constructor
    public OrderItem() {}
    public OrderItem(int quantity, int orderPrice, Product product) {
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.product = product;
    }

    // getter
    public Long getId() { return id; }
    public int getQuantity() { return quantity; }
    public int getOrderPrice() { return orderPrice; }
    public Product getProduct() { return product; }
    public Order getOrder() { return order; }

    // setter
    public void setProduct(Product product) { this.product = product; }
    public void setOrder(Order order) { this.order = order; }
}