package oop;

public class SchoolExample {
    public static void main(String[] args) {
        UnivStudent univStudent = new UnivStudent("봄이");
        Professor professor = new Professor("온이");

        univStudent.takeCourse(professor);
    }
}