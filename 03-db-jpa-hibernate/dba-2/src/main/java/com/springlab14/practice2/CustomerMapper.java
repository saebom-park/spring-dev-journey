package com.springlab14.practice2;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<Customer> findAll();
    Customer findById(int id);
    void insert(Customer customer);
    List<Customer> findByCondition(Map<String, Object> param);
}