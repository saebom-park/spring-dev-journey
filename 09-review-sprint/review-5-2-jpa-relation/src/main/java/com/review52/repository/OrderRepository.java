package com.review52.repository;

import com.review52.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    @EntityGraph(attributePaths = {"orderItems", "member"})
    List<Order> findAll();
}