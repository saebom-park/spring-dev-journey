package com.review51.domain;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="member_name")
    private String name;

    // constructor
    public Member() {}
    public Member(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}