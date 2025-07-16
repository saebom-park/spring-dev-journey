package com.springlab16;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Member() {}

    public Member(String name) { this.name = name; }

    public String getName() { return name; }
}