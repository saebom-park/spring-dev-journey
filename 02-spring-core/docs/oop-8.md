# [OOP-8단계] 객체 협력 관계(cooperation)

> ✨ "기능을 나눠서 여러 객체가 함께 일하게 하려면?"
> 
> 
> 👉 바로 **객체 간 협력 구조(cooperation)를 설계**하면 돼!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 협력 (Cooperation) | 객체들이 서로 역할을 나눠 메시지를 주고받으며 함께 작업 |
| 메시지 전달 | 메서드 호출을 통해 다른 객체에게 작업을 위임 |
| 책임 분리 | 객체는 자신이 맡은 역할만 처리하고 나머지는 협력 |
| 의존 관계 | 한 객체가 다른 객체를 사용하여 동작하는 관계 |
| 생성자 주입 | 협력 객체를 생성자에서 주입받는 방식으로 의존성 구성 |

---

### 🧾 예시 코드

```java
// UnivStudent.java
public class UnivStudent {
    private String name;

    public UnivStudent(String name) {
        this.name = name;
    }

    public void takeCourse(Professor professor) {
        System.out.println(name + "이(가) 수업을 듣습니다.");
        professor.teach(this);
    }

    public String getName() {
        return name;
    }
}
```

```java
// Professor.java
public class Professor {
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public void teach(UnivStudent univStudent) {
        System.out.println(name + " 교수가 " + univStudent.getName() + "에게 강의합니다.");
    }
}
```

```java
// SchoolExample.java
public class SchoolExample {
    public static void main(String[] args) {
        UnivStudent univStudent = new UnivStudent("봄이");
        Professor professor = new Professor("김교수");

        univStudent.takeCourse(professor);
    }
}
```

---

### 📌 포인트 요약

- 객체 협력이란 **작업을 나눠서 각 객체가 책임지고 실행하는 구조**
- `Student → Professor`는 **메시지 전달을 통한 협력**
- 협력 구조에서는 **강한 결합보다 느슨한 연결**을 유도해야 함
- 생성자 주입을 통해 **역할 분리 + 테스트 용이성**까지 확보 가능
- 실무에서는 **서비스 → 리포지토리 구조**도 협력 관계로 구성됨

---

### 🧪 실습 미션

> 🎯 협력 구조를 직접 만들어보기!
> 

### 시나리오

- 고객(`Customer`)이 커피를 주문
- 바리스타(`Barista`)는 커피머신(`CoffeeMachine`)을 사용해 커피 제조
- 각 역할은 책임만 수행하고, 나머지는 협력으로 처리

### 클래스 설계

1. `Customer` 클래스 → `orderCoffee(Barista barista)`
2. `Barista` 클래스 → `makeCoffee(CoffeeMachine machine)`
3. `CoffeeMachine` 클래스 → `brew()`
4. 실행 클래스: `CafeExample.java`에서 전체 실행 흐름 테스트

### 기대 출력 예시

```
봄이 고객이 커피를 주문합니다.
바리스타가 커피를 준비합니다.
커피머신으로 커피를 내립니다.
커피가 준비되었습니다!
```