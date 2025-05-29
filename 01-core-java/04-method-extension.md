# 기능 메서드 추가 (2025-04-28)

run, bark 등 클래스의 기능을 확장하는 연습입니다.

## 주요 개념
- 메서드 추가 및 호출
- `this` 없이 필드 접근

## 예제 코드

```java
public class Dog {
    String name;
    int age;

    public Dog (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("안녕! 나는 사랑스러운 봄이의 강아지 " + name + "라고해. 나이는 " + age + "살이야! 만나서 반가워.");
    }

    public void introduce(String greeting) {
        System.out.println(greeting + "나는 귀염둥이 " + name + "라고해! 나이는 " + age + "살이야! 온이를 알게 되어서 정말 좋다~!");
    }

    public void run() {
        System.out.println(name + "가 들판을 신나게 달리고 있어요.");
    }

    public void bark() {
        System.out.println(name + "가 짖고 있어요.");
    }

    public static void main(String[] args) {
        Dog dobi = new Dog("도비", 9);
        Dog aboo = new Dog("아부", 7);

        dobi.introduce();
        aboo.introduce("안녀엉 온아!");

        dobi.run();
        aboo.bark();
    }
}