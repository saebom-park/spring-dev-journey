package com.springlab17.practice;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    List<Product> products = new ArrayList<>();

    // Constructor
    public Category() {}
    public Category(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Product> getProducts() { return products; }

    // 연관관계 편의 메서드
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

}