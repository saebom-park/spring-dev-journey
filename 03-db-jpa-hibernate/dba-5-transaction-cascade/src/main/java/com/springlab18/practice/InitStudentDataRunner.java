package com.springlab18.practice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitStudentDataRunner implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final AcademyRepository academyRepository;

    // Constructor
    public InitStudentDataRunner(StudentRepository studentRepository, AcademyRepository academyRepository) {
        this.studentRepository = studentRepository;
        this.academyRepository = academyRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Academy a1 = new Academy("코딩스쿨");
        Student s1 = new Student("봄이", "초급");
        Student s2 = new Student("온이", "고급");

        a1.addStudents(s1);
        a1.addStudents(s2);
        academyRepository.save(a1);
        studentRepository.save(s1);
        studentRepository.save(s2);

        academyRepository.findAll().forEach(acd -> {
            System.out.println(acd.getName() + "의 수강생 명단");
            acd.getStudents().forEach(stu -> System.out.println("- " + stu.getName() + " 수강생 (" + stu.getLevel() + ")"));
        });
    }
}