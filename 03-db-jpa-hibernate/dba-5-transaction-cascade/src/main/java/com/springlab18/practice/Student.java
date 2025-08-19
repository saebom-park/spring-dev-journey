package com.springlab18.practice;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "level")
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;

    // Constructor
    public Student() {}
    public Student(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name;}
    public String getLevel() { return level; }
    public Academy getAcademy() { return academy; }

    // setter
    public void setAcademy(Academy academy) { this.academy = academy; }
}