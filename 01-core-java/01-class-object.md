# 클래스와 객체 (2025-04-18)

자바의 가장 기본 단위인 클래스와 객체 생성 실습입니다.

## 주요 개념
- 클래스 정의
- 객체 생성 및 필드 접근
- 메서드 호출

## 예제 코드

```java
public class Dog {
    String name;
    int age;

    public void introduce() {
        System.out.println("안녕! 내 이름은 " + name + "라고해. 나이는 " + age + "살이야! 만나서 반가워~");
    }

    public static void main(String[] args) {
        Dog dobi = new Dog();
        Dog aboo = new Dog();

        dobi.name = "도비";
        aboo.name = "아부";

        dobi.age = 9;
        aboo.age = 7;

        dobi.introduce();
        aboo.introduce();
    }
}