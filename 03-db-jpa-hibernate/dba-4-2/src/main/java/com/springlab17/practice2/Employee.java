package com.springlab17.practice2;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    // Constructor
    public Employee() {}
    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public Department getDepartment() { return department; }

    // setter
    public void setDepartment(Department department) { this.department = department; }
}