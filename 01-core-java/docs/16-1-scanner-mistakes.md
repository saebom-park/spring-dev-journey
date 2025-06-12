# [16-1단계] 실수노트

> 💻 실습 코드: ScannerPractice.java
> 

---

### 😅 실수 — `nextInt()` 다음에 오는 `nextLine()`이 빈 문자열을 받아버림

```java
int age = scanner.nextInt();
String addr = scanner.nextLine(); // ❌ 입력 없이 그냥 넘어감
```

✅ 정답:

```java
int age = scanner.nextInt();
scanner.nextLine(); // 🔥 남아 있는 엔터 제거

String addr = scanner.nextLine();
```

📌 **설명**:

- `nextInt()`는 숫자까지만 읽고 엔터는 남김
- `nextLine()`은 줄 전체를 읽는데, 남아 있는 엔터만 읽고 **빈 문자열이 들어감**
- `nextInt()` 다음엔 `scanner.nextLine();`으로 개행 처리 반드시 해줘야 함

---

### 📌 요약 포인트

- `nextInt()` 다음엔 **무조건 `nextLine();` 한 줄 넣자!**
- 자바 콘솔 입력에서는 **Scanner 버퍼 처리**가 핵심!