package oop;

public class Person {
    private String name;    // 외부에서 직접 접근 불가
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public void introduce() {
        System.out.println("안녕하세요! 제 이름은 " + name + ", 나이는 " + age + "살이에요.");
    }

    public static void main(String[] args) {
        Person p = new Person("봄이", 28);
        p.setAge(30);   // setter로 값 변경
        System.out.println("이름: " + p.getName());   // getter로 읽기
        p.introduce();
    }
}