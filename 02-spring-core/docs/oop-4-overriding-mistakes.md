# [OOP-4단계] 실수노트

> 💻 실습 코드: MessageSender.java / SenderExample.java
> 

---

### 😅 실수 1 — `super`를 객체처럼 사용하려 함

```java
// 잘못된 예시
a2.super.speak(); // ❌ 컴파일 에러 발생
```

✅ 정답:

```java
// 자식 클래스 내부에서만 사용 가능
super.speak(); // ✅ 부모 메서드 호출
```

📌 **설명**:

- `super`는 키워드라서 객체처럼 `a2.super` 이런 식으로 쓸 수 없어!
- 자식 클래스 내부에서 부모의 기능을 가져다 쓰고 싶을 때만 `super.메서드()` 형식으로 사용 가능!

---

### 😅 실수 2 — `@Override` 생략

```java
public void send() {
    System.out.println("...");
}
```

✅ 정답:

```java
@Override
public void send() {
    System.out.println("...");
}
```

📌 **설명**:

- `@Override`는 생략해도 실행되긴 하지만, 실수 방지 + 유지보수 측면에서 필수!
- 실무에서는 **무조건 붙이는 습관**을 들이는 게 좋아!

---

### 📌 요약 포인트

- `super`는 **자식 클래스 내부에서만 직접 사용 가능**
- `@Override`는 생략 가능하지만, **붙이는 게 정석**
- 오버라이딩은 **동적 바인딩**이기 때문에 **객체 타입보다 인스턴스 기준으로 동작**