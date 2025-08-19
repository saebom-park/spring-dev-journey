package com.springlab17;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    //@OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST) → 실무에서는 신중하게!
    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    // Constructor
    public Member() {}
    public Member(String name){ this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }

    // 연관관계 편의 메서드 (객체 양쪽 필드를 항상 일관되게 동기화 하기 위함)
    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }
}