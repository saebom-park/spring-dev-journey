# 메서드 오버로딩 (2025-04-28)

같은 이름의 메서드를 매개변수에 따라 다르게 구현하는 오버로딩 개념 실습입니다.

## 주요 개념
- 메서드 오버로딩
- 출력 분기

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
        System.out.println("왈왈! 안녕! 나는 봄이의 기엽둥이 " + name + "라고해. 나이는 " + age + "살이야~ 만나서 반가워~!");
    }

    public void introduce(String greeting) {
        System.out.println(greeting + "나는 봄이의 기염둥이 " + name + "라고해. 나이는 " + age + "살이야~ 만나서 반가워~!");
    }

    public static void main (String[] args) {
        Dog dobi = new Dog("도비", 9);
        Dog aboo = new Dog("아부", 7);

        dobi.introduce();
        aboo.introduce("멍멍멍 안녕!");
    }
}