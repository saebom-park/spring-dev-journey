# [OOP-3단계] 실수노트

> 💻 실습 코드: Vehicle.java / SmartWasher.java
> 

---

### 😅 실수 1 — `@Override` 안 붙이고 재정의만 함

```java
public void powerOn() {
    // 오버라이딩은 했지만, @Override 안 붙임
}
```

✅ 정답:

```java
@Override
public void powerOn() {
    ...
}
```

📌 **설명**:

- `@Override`는 꼭 붙이지 않아도 작동하지만,
- 실수로 **오타가 났을 때 컴파일 에러로 알려줘서** 안전해!
- 실무에서는 **습관적으로 붙이는 게 좋음**.

---

### 😅 실수 2 — `super()` 안 쓰고 필드 초기화만 함

```java
public Washer(String brand, int capacity) {
    this.brand = brand; // ❌ 부모 생성자 안 불러줌
    this.capacity = capacity;
}
```

✅ 정답:

```java
public Washer(String brand, int capacity) {
    super(brand); // ✅ 부모 생성자 호출!
    this.capacity = capacity;
}
```

📌 **설명**:

- `brand` 필드는 부모 클래스에 있으므로 `super()`로 초기화해야 맞아
- 그냥 직접 `this.brand`로 접근하려고 하면 **캡슐화 위반 + 구조 혼동**

---

### 📌 요약 포인트

- `@Override`는 **개발자의 실수 방지용으로 항상 붙이는 습관** 만들기!
- 부모 필드 초기화는 꼭 `super()`로 → **상속 구조 설계 원칙**
- 생성자 연결 순서는 항상 **부모 → 자식 순**이라는 점 잊지 말기!