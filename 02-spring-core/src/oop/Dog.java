package oop;

public class Dog {
    String name;
    int age;

    Dog (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void bark() {
        System.out.println("멍멍!");
    }

    public void introduce() {
        System.out.println("안녕! 내 이름은 " + name + "라고 해! 나이는 귀여운 " + age + "살이야! 만나서 반가워~");
    }

    public static void main(String[] args) {
        String[] names = {"아부", "도비"};
        int[] ages = {7, 9};

        Dog aboo = new Dog(names[0], ages[0]);
        Dog dobi = new Dog(names[1], ages[1]);

        aboo.introduce();
        aboo.bark();
        dobi.introduce();
        dobi.bark();
    }
}