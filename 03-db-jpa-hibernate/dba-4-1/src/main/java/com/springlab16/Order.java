package com.springlab16;

import jakarta.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Order() {}

    public Order(String itemName, Member member) {
        this.itemName = itemName;
        this.member = member;
    }

    public String getItem() {
        return itemName;
    }

    public Member getMember() {
        return member;
    }
}