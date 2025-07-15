package com.springlab14.practice;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    List<Order> findAll();
    Order findById(int id);
    void insert(Order order);
    List<Order> findByCondition(Map<String, Object> param);
}