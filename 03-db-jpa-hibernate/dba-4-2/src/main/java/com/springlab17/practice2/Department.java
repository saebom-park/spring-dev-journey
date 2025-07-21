package com.springlab17.practice2;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    private String name;

    @OneToMany(mappedBy = "department")
    List<Employee> employees = new ArrayList<>();

    // Constructor
    public Department() {}
    public Department(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }

    // 연관관계 편의 메서드
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }
}