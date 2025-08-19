package com.springlab18.practice;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academies")
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academy_id")
    private Long id;

    @Column(name = "academy_name")
    private String name;

    //@OneToMany(mappedBy = "academy", cascade = CascadeType.PERSIST)
    @OneToMany(mappedBy = "academy")
    List<Student> students = new ArrayList<>();

    // Constructor
    public Academy() {}
    public Academy(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Student> getStudents() { return students; }

    // 연관관계 편의 메서드
    public void addStudents(Student student) {
        students.add(student);
        student.setAcademy(this);
    }
}