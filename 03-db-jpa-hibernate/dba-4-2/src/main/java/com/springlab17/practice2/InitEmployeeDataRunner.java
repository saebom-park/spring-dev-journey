package com.springlab17.practice2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitEmployeeDataRunner implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // Constructor
    public InitEmployeeDataRunner(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Department d1 = new Department("개발팀");
        Employee e1 = new Employee("봄이", "대리");
        Employee e2 = new Employee("온이", "과장");

        d1.addEmployee(e1);
        d1.addEmployee(e2);
        departmentRepository.save(d1);
        employeeRepository.save(e1);
        employeeRepository.save(e2);

        departmentRepository.findAll().forEach(dep -> {
            System.out.println("[" + dep.getName() + "]");
            dep.getEmployees().forEach(emp -> System.out.println(emp.getName() + "(" + emp.getPosition() + ")"));
        });
    }
}