package com.review52.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name="product_name")
    private String name;

    private int price;

    @OneToMany(mappedBy="product")
    private List<OrderItem> orderItems = new ArrayList<>();

    // constructor
    public Product() {}
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public List<OrderItem> getOrderItems() { return orderItems; }

    // 사용자 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setProduct(this);
    }
}