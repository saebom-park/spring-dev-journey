package com.springlab13.practice;

import java.util.List;

public interface OrderMapper {
    List<Order> findAll();
    Order findById(int id);
    void insert(Order order);
}