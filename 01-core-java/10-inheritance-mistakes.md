# [10단계] 봄이의 실수 노트 — 상속 & 포함관계 실수 총정리

### 💡 실수 유형 요약

| 실수 번호 | 유형 | 설명 |
| --- | --- | --- |
| 1️⃣ | 실행문 위치 오류 | 클래스 안에서 실행문(`bark.sound = "멍멍!"`) 사용 |
| 2️⃣ | 타입 불일치 | `Bark` 타입 필드에 `String` 대입 |
| 3️⃣ | null 체크 누락 | `bark`가 null일 때 메서드 호출 |
| 4️⃣ | 객체 전체 연결 누락 | `bark.sound`를 직접 할당하려 한 실수 |

---

### 🧾 예시 코드 & 해결 방법

---

### 1️⃣ 클래스 안에서 실행문을 바로 사용한 실수

```java
class Animal {
    Bark bark;
    bark.sound = "멍멍!"; // ❌ 실행문은 클래스 영역에서 사용할 수 없음
}
```

✅ **해결 방법**: 생성자 안에서 실행문 사용!

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

### 2️⃣ 타입 불일치 — 객체 타입 필드에 문자열을 대입한 실수

```java
aboo.bark = "멍멍!"; // ❌ Bark는 객체, "멍멍!"은 문자열
```

✅ **해결 방법**: 객체 타입을 맞춰주기

```java
Bark bark = new Bark();
bark.sound = "멍멍!";
aboo.bark = bark;
```

---

### 3️⃣ 객체 생성 없이 바로 메서드 호출한 실수

```java
bark.sound(); // ❌ NullPointerException
```

✅ **해결 방법 1**: null 체크 후 호출

```java
if (bark != null) {
    bark.sound();
}
```

✅ **해결 방법 2**: 반드시 객체 생성 후 사용

```java
bark = new Bark();
bark.sound = "멍멍!";
bark.sound();
```

---

### 4️⃣ 타입 혼동 — Bark 객체 대신 String을 연결하려 한 실수

```java
Bark bark = new Bark();
aboo.bark = bark.sound; // ❌ String → Bark 할당 불가
```

✅ **해결 방법**: 객체 전체 연결하기

```java
aboo.bark = bark; // 타입 일치
```

---

### 📌 핵심 포인트 요약

- **클래스 내부에서 실행문 사용 금지** → 생성자나 메서드 안에서만!
- **객체 타입과 변수 타입 반드시 일치**
- **null 체크 필수**! → 특히 포함관계에서
- `bark.sound`는 `String`, `bark`는 `Bark` 객체 → 서로 다른 타입