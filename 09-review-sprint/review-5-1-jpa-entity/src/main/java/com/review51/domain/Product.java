package com.review51.domain;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name")
    private String name;

    @Column(name="product_price")
    private int price;

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

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
}