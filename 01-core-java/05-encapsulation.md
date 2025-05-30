# [5단계] 접근 제어자 & 캡슐화

> 필드를 마음대로 접근하지 못하게 막고,
> 
> 
> 메서드를 통해 **통제된 방식으로 값을 주고받는 것**,
> 
> 이게 바로 **캡슐화(Encapsulation)**!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 | 예시 |
| --- | --- | --- |
| 접근 제어자 | 클래스/변수/메서드의 접근 범위를 제한하는 키워드 | `public`, `private`, `protected`, (default) |
| 캡슐화 | 데이터를 숨기고 메서드로만 접근하게 하는 구조 | `private + getter/setter` |
| getter | 값을 반환하는 메서드 → 자료형 붙음 | `int`, `String`, `boolean` 등 |
| setter | 값을 설정하는 메서드 → `void` 사용 | `void setAge(int age)` |

---

### 🧾 예시 코드: Person 클래스

```java
public class Person {
    private String name;
    private int age;

    // 생성자
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter
    public String getName() {
        return name;
    }

    // setter
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
```

---

### 📌 포인트 요약

- `private`: 외부에서 직접 접근 ❌ → 오직 메서드를 통해서만 가능
- `getter`: 읽기 전용 역할 (값 반환)
- `setter`: 쓰기 전용 역할 (값 설정) + 검증 로직 포함 가능
- 데이터를 보호하면서 유지보수성과 안정성을 높여주는 구조

---

### 🧪 실습 미션

> ✅ Car 클래스를 만들고
> 
> 
> `private` 필드: `model`, `speed`
> 
> `getModel()`, `setSpeed()` 메서드를 만들어보자.
> 
> 단, `setSpeed()`는 **0~200 사이만 허용**!
> 

---

### 🧾 실습 예시 코드

```java
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
        System.out.println("차량모델: " + myCar.getModel());

        myCar.setSpeed(200);
        System.out.println("차량스피드: " + myCar.getSpeed());
    }
}
```