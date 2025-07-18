package com.springlab17.practice;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor
    public Product() {}
    public Product(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    // getter
    public Long getId() { return id; }
    public String getItemName() { return itemName; }
    public int getPrice() { return price; }
    public Category getCategory() { return category; }

    // setter
    public void setCategory(Category category) { this.category = category; }
}