package com.springlab16.practice;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor
    public Product() {}
    public Product(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public Category getCategory() { return category; }
}