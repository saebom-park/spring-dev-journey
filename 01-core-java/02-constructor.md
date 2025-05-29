# 생성자 Constructor (2025-04-28)

객체 생성 시 값을 바로 초기화할 수 있도록 생성자 사용을 실습합니다.

## 주요 개념
- 생성자 선언과 호출
- `this` 키워드

## 예제 코드

```java
public class Dog {
    String name;
    int age;

    public void introduce() {
        System.out.println("안녕! 나는 봄이의 귀여운 강아지 " + name + "라고 해! 나이는 " + age + "살이야! 만나서 반가워 ~");
    }

    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Dog dobi = new Dog("도비", 9);
        Dog aboo = new Dog("아부", 7);

        dobi.introduce();
        aboo.introduce();
    }
}