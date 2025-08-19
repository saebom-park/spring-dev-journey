package com.springlab16;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Constructor
    public Order() {}
    public Order(String itemName, Member member) {
        this.itemName = itemName;
        this.member = member;
    }

    // getter
    public Long getId() { return id; }
    public String getItemName() { return itemName; }
    public Member getMember() { return member; }


}