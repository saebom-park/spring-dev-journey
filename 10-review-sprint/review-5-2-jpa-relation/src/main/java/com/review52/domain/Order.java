package com.review52.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @Column(name="order_date")
    private String orderDate;

    private String status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // constructor
    public Order() {}
    // 주문은 회원에 귀속되므로 생성자에 포함하는 것이 적절함
    public Order(String orderDate, String status, Member member) {
        this.orderDate = orderDate;
        this.status = status;
        this.member = member;
    }

    // getter
    public Long getId() { return id; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public Member getMember() { return member; }
    public List<OrderItem> getOrderItems() { return orderItems; }

    // setter
    public void setMember(Member member) { this.member = member; }

    // 사용자 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}