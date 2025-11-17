package com.review42.domain;

public class Category {
    private Long id;
    private String name;

    // constructor
    public Category() {}
    public Category(String name) { this.name = name;}

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}