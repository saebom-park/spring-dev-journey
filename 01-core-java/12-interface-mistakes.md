# [12단계] 실수노트

---

### 😅 실수 1 — 인터페이스 타입 오타

```java
Playalbe myPiano = new Piano(); // ❌ 오타
```

✅ 정답:

```java
Playable myPiano = new Piano(); // ✅
```

---

### 😅 실수 2 — 클래스 중복 선언

```java
public class Main { ... }
...
public class Main { ... } // ❌ 클래스 중복 선언
```

✅ `public class`는 하나의 파일에서 한 번만 선언 가능!

---

### 😅 실수 3 — for-each 문에서 타입 불일치

```java
for (String playList : playLists) {
    playList.play(); // ❌ 오류 발생
}
```

✅ 정답:

```java
for (Playable playList : playLists) {
    playList.play(); // ✅
}
```

---

### 😅 실수 4 — void 메서드에 대해 println 사용

```java
System.out.println(playList.play()); // ❌ 컴파일 에러
```

✅ 정답:

```java
playList.play(); // ✅ void 메서드는 단독 호출
```

---

### 📌 포인트 요약

- 변수 타입 선언 시 오타 주의 (예: `Playable` vs `Playalbe`)
- Java에서는 `public class`는 파일당 하나만 존재 가능
- for-each에서 **타입이 일치해야 메서드 호출 가능**
- `void` 반환 메서드는 `System.out.println()`과 함께 쓸 수 없음