# [12단계] 추가 개념 정리

### 💡 핵심 개념 요약

| 주제 | 설명 |
| --- | --- |
| default 메서드 | Java 8부터 인터페이스 내부에 **구현된 메서드**(`default`) 작성 가능 |
| static 메서드 | 인터페이스에서도 `static` 메서드 정의 가능 (클래스명으로 호출) |
| 인터페이스 상속 | 인터페이스는 `extends` 키워드로 다른 인터페이스 상속 가능 |
| 다중 구현 | 하나의 클래스가 **여러 인터페이스를 동시에 구현** 가능 |
| 실무 활용 | 역할 기반 구조 (`Repository`, `Service`) 설계 시 인터페이스 사용 |

---

### 🧾 예시 코드 1 — default 메서드

```java
interface MyInterface {
    void doSomething();

    default void sayHello() {
        System.out.println("안녕!");
    }
}
```

---

### 🧾 예시 코드 2 — static 메서드

```java
interface MathUtil {
    static int add(int a, int b) {
        return a + b;
    }
}

// 사용
int sum = MathUtil.add(3, 5); // 8
```

---

### 🧾 예시 코드 3 — 인터페이스 상속

```java
interface A {
    void methodA();
}

interface B extends A {
    void methodB();
}
```

---

### 🧾 예시 코드 4 — 다중 구현

```java
interface Flyable {
    void fly();
}

interface Runnable {
    void run();
}

class Bird implements Flyable, Runnable {
    public void fly() {
        System.out.println("난다!");
    }

    public void run() {
        System.out.println("뛴다!");
    }
}
```

---

### 📌 포인트 요약

- `default` / `static` 메서드 덕분에 인터페이스도 일부 구현 가능
- `interface B extends A` 구조로 **인터페이스끼리도 상속 가능**
- 한 클래스는 `implements`로 **여러 인터페이스를 동시에 구현 가능**
- 실무에서는 설계 유연성을 위해 인터페이스 적극 활용 (예: `Service`, `Repository` 구조)