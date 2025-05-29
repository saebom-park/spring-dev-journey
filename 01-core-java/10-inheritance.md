# 10. 상속과 super(), 포함 관계 확장 (2025-05-29)

이번 실습에서는 자바의 상속 구조를 이해하고,  
부모 클래스 생성자를 호출하는 `super()`와  
포함 관계(`has-a`)를 함께 연계하여 **객체지향 모델링**을 연습합니다.

---

## 주요 개념

- `extends`로 부모 클래스를 상속받아 기능 재사용
- `super()`로 부모 생성자 호출
- `오버라이딩(Overriding)`을 통해 자식 클래스가 부모 메서드 재정의
- 포함 관계를 함께 활용해 **복합 객체 구조 설계**

---

## 예제 코드 1 – 기본 상속 구조

```java
class Animal {
    String name;

    void speak() {
        System.out.println("동물이 소리를 낸다.");
    }
}

class Dog extends Animal {
    void speak() {
        System.out.println("멍멍");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.name = "아부";
        d.speak();
        System.out.println("이름: " + d.name);
    }
}
```
---

## 예제 코드 2 – `포함 관계와 상속 결합`

```java
class Bark {
    String sound;

    void sound() {
        System.out.println(sound);
    }
}

class Animal {
    String name;
    int age;
    Bark bark;

    void speak() {
        System.out.println("동물이 짖는 소리");
    }

    void introduce() {
        System.out.println("안녕하세요! 제 이름은 " + name + "이고, 나이는 " + age + "살입니다.");
        bark.sound();
    }
}

class Dog extends Animal {
    void speak() {
        System.out.println("멍망몽멍!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog aboo = new Dog();
        aboo.name = "아부";
        aboo.age = 7;

        Bark bark = new Bark();
        bark.sound = "멍멍";
        aboo.bark = bark;

        aboo.speak();
        aboo.introduce();
    }
}
```
---

## 예제 코드 3 – `super()로 부모 생성자 호출`

```java
class Animal {
    String name;
    int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void speak() {
        System.out.println("동물이 짖는 소리");
    }

    void introduce() {
        System.out.println("안녕하세요! 제 이름은 " + name + "이고, 나이는 " + age + "살 입니다.");
    }
}

class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age);
    }

    void speak() {
        System.out.println("왕왕!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dobi = new Dog("도비", 9);
        dobi.speak();
        dobi.introduce();
    }
}
```
---

## 실습 미션 – `동물과 소리 객체 설계`

```java
class Animal {
    String name;
    int age;
    Sound sound;

    Animal(String name, int age, Sound sound) {
        this.name = name;
        this.age = age;
        this.sound = sound;
    }

    void introduce() {
        System.out.println("안녕하세요! 제 이름은 " + name + "이고, 나이는 " + age + "살 입니다.");
    }

    void speak() {
        System.out.println("동물의 소리를 표현하는 메서드 입니다.");
    }
}

class Dog extends Animal {
    Dog(String name, int age, Sound sound) {
        super(name, age, sound);
    }

    void speak() {
        System.out.println("멍멍!");
    }
}

class Cat extends Animal {
    Cat(String name, int age, Sound sound) {
        super(name, age, sound);
    }

    void speak() {
        System.out.println("냥냥~");
    }
}

class Sound {
    String soundText;

    void makeSound() {
        System.out.println(soundText);
    }
}

public class Main {
    public static void main(String[] args) {
        Sound sound1 = new Sound();
        sound1.soundText = "멍멍!멍멍!";

        Sound sound2 = new Sound();
        sound2.soundText = "냥~냥~냥~";

        Dog aboo = new Dog("아부", 7, sound1);
        Dog dobi = new Dog("도비", 9, sound1);
        Cat nabi = new Cat("나비", 2, sound2);

        aboo.speak();
        aboo.introduce();
        System.out.println(aboo.name + "의 소리:");
        aboo.sound.makeSound();
        System.out.println("-----------------------");
        dobi.speak();
        dobi.introduce();
        System.out.println(dobi.name + "의 소리:");
        dobi.sound.makeSound();
        System.out.println("-----------------------");
        nabi.speak();
        nabi.introduce();
        System.out.println(nabi.name + "의 소리:");
        nabi.sound.makeSound();
    }
}
```