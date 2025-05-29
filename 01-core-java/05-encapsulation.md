# 접근 제어자 & 캡슐화 (2025-05-19)

접근 제어자 `private`와 `getter/setter` 메서드를 활용해  
**객체 지향에서의 정보 은닉과 데이터 보호** 개념을 실습합니다.

---

## 주요 개념

- `private` 접근 제어자를 통해 **외부로부터 필드 보호**
- `getter`, `setter` 메서드로 간접 접근 구현
- **유효성 검사 로직**을 통해 객체 내부의 상태를 안전하게 유지
- 설계 관점에서의 **캡슐화(Encapsulation)** 실습

---

## 예제 코드 1 – `Person`

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 130) {
            this.age = age;
        } else {
            System.out.println("나이 값이 유효하지 않습니다.");
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("봄이", 25);
        String name = p1.getName();
        System.out.println("이름: " + name);

        p1.setAge(30);
        System.out.println("나이: " + p1.getAge());
    }
}

---

## 예제 코드 2 – `Car`
public class Car {
    private String model;
    private int speed;

    public Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    public String getModel() {
        return model;
    }

    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 200) {
            this.speed = speed;
        } else {
            System.out.println("제한 속도를 벗어난 값입니다.");
        }
    }

    public int getSpeed() {
        return speed;
    }

    public static void main(String[] args) {
        Car myCar = new Car("bomCar", 150);
        String myModel = myCar.getModel();

        System.out.println("차량모델: " + myModel);

        myCar.setSpeed(200);
        System.out.println("차랑스피드: " + myCar.getSpeed());
    }
}