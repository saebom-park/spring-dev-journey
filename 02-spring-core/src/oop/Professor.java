package oop;

public class Professor {
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public void teach(UnivStudent univStudent) {
        System.out.println(name + "교수가 " + univStudent.getName() + "에게 강의합니다.");
    }
}