package com.springlab16;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    // Constructor
    public Member() {}
    public Member(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
}