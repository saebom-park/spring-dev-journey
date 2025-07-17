package com.springlab16.practice;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    // Constructor
    public Category() {}
    public Category(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
}