# [10단계] 실수노트

---

### 😅 실수 1 — 클래스 안에서 실행문 사용

```java
class Animal {
    Bark bark;
    bark.sound = "멍멍!"; // ❌ 클래스 영역에서는 실행문 사용 불가
}
```

✅ 해결 방법: 생성자 안에서 처리

```java
class Animal {
    Bark bark;

    Animal() {
        bark = new Bark();
        bark.sound = "멍멍!";
    }
}
```

---

### 😅 실수 2 — 타입 불일치

```java
aboo.bark = "멍멍!"; // ❌ Bark는 객체, "멍멍!"은 문자열
```

✅ 해결 방법:

```java
Bark bark = new Bark();
bark.sound = "멍멍!";
aboo.bark = bark;
```

---

### 😅 실수 3 — 객체 생성 없이 메서드 호출

```java
bark.sound(); // ❌ NullPointerException
```

✅ 해결 방법:

```java
if (bark != null) {
    bark.sound();
}
```

또는

```java
bark = new Bark();
bark.sound = "멍멍!";
bark.sound();
```

---

### 😅 실수 4 — 객체 타입 혼동

```java
aboo.bark = bark.sound; // ❌ String → Bark 할당 불가
```

✅ 해결 방법:

```java
aboo.bark = bark;
```

---

### 📌 포인트 요약

- 실행문은 반드시 **생성자나 메서드 안에서만**
- 타입이 다르면 컴파일 오류 발생 (`Bark` vs `"멍멍!"`)
- `null` 체크는 항상 필수!
- 필드의 타입과 할당하는 값의 타입이 일치해야 함