# [13단계] 실수노트

> 💻 실습 코드: SafeCalculator.java
> 

---

### 😅 실수 1 — `Scanner.nextDouble()` → 클래스 이름 실수

```java
double numeric1 = Scanner.nextDouble(); // ❌ 컴파일 에러 발생
```

✅ 정답:

```java
double numeric1 = scanner.nextDouble(); // ✅ 소문자 변수 사용
```

📌 **설명**:

- `Scanner`는 클래스 이름이라 `.nextDouble()`을 직접 호출할 수 없어!
- `scanner`는 `new Scanner(System.in)`으로 생성한 **변수 이름**이라서, 이걸 써야 해!

---

### 😅 실수 2 — `Scanner` 인스턴스를 3개나 만들었음

```java
Scanner operator = new Scanner(System.in);
Scanner numeric1 = new Scanner(System.in);
Scanner numeric2 = new Scanner(System.in);
```

✅ 정답:

```java
Scanner scanner = new Scanner(System.in); // ✅ 하나로 통합
```

📌 **설명**:

- `System.in`을 여러 번 연결하면 입력 스트림 충돌이 생길 수 있어!
- 하나의 `Scanner`만 만들어서 연산자, 숫자1, 숫자2 모두 처리하는 게 정석이야.

---

### 😅 실수 3 — 입력값을 변수에 저장하지 않음

```java
operator.next(); // ❌ 입력은 받았지만 변수에 저장 안 함
```

✅ 정답:

```java
String operator = scanner.next(); // ✅ 저장 후 사용
```

📌 **설명**:

- `next()`로 값을 받았으면 **반드시 변수에 저장**해야 계산에 쓸 수 있어!

---

### 📌 요약 포인트

- `Scanner`는 클래스, `scanner`는 변수 → 혼동 주의!
- 입력은 하나의 `Scanner`로 처리하는 게 안정적
- 사용하려는 입력값은 반드시 **변수에 저장 후 사용**
- 변수 이름 오타 하나로도 **컴파일 에러** 발생할 수 있음!