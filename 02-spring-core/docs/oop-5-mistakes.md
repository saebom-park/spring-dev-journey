# [OOP-5단계] 실수노트

> 💻 실습 코드: ShapeExample.java
> 

---

### 😅 실수 1 — 자식 전용 메서드를 업캐스팅된 상태에서 호출하려 함

```java
Shape shape = new Circle();
shape.radiusInfo(); // ❌ 컴파일 오류!
```

✅ 정답:

```java
if (shape instanceof Circle) {
    ((Circle) shape).radiusInfo(); // ✅ 다운캐스팅 후 호출
}
```

📌 **설명**:

- 업캐스팅하면 `Shape` 타입으로만 바라보기 때문에 **자식 전용 기능이 보이지 않음**
- 자식 메서드를 쓰려면 **다운캐스팅**해서 꺼내야 함

---

### 😅 실수 2 — `instanceof` 없이 무작정 다운캐스팅

```java
Shape shape = new Shape();
Circle c = (Circle) shape; // ❌ 실행 중 오류 (ClassCastException)
```

✅ 정답:

```java
if (shape instanceof Circle) {
    Circle c = (Circle) shape; // ✅ 안전하게 형 변환
}
```

📌 **설명**:

- 다운캐스팅은 **"내부적으로 진짜 자식 객체일 때만" 성공**함
- 아닌 경우 실행 중 오류 발생 → `instanceof` 체크는 필수!

---

### 😅 실수 3 — `draw()`만 보고 자식 객체라고 착각

```java
Shape shape = new Circle();
shape.draw(); // "원을 그립니다." ← 자식 메서드 실행됨

shape.radiusInfo(); // ❌ 오류! Shape 타입이므로 보이지 않음
```

📌 **설명**:

- 다형성에서는 **오버라이딩된 메서드는 자식 기준으로 실행됨**
- 하지만 자식 전용 메서드는 다운캐스팅 없이는 호출 불가
- 이 차이를 헷갈리지 말 것!

---

### 📌 요약 포인트

- ✅ 업캐스팅된 객체에서는 자식 메서드가 보이지 않음 → **다운캐스팅 필요**
- ✅ 다운캐스팅 전에 `instanceof`로 안전하게 확인
- ✅ 실행 흐름은 자식 → 컴파일 기준은 부모