package com.review52.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name="member_name")
    private String name;

    @OneToMany(mappedBy="member")
    private List<Order> orders = new ArrayList<>();

    // constructor
    public Member() {}
    public Member(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }

    // 사용자 편의 메서드
    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }
}