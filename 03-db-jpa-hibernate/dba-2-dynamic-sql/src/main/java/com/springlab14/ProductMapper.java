package com.springlab14;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    List<Product> findAll();
    Product findById(int id);
    void insert(Product product);
    List<Product> findByCondition(Map<String, Object> param);
}