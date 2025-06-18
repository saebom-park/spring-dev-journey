package oop;

public class UnivStudent {
    private String name;

    public UnivStudent(String name) {
        this.name = name;
    }

    public void takeCourse(Professor professor) {
        System.out.println(name + "가 수업을 듣습니다.");
        professor.teach(this);
    }

    public String getName() {
        return name;
    }
}