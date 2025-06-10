# [15-2단계] 실수노트

> 💻 실습 코드: FormatPractice.java
> 

---

### 😅 실수 1 — `parse()`에 기본 포맷 아닌 문자열 사용

```java
LocalDate parsed = LocalDate.parse("2025/08/18"); // ❌ 런타임 오류 발생!
```

✅ 정답:

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
LocalDate parsed = LocalDate.parse("2025/08/18", formatter); // ✅
```

📌 **설명**:

- `LocalDate.parse()`는 ISO-8601 형식 `"yyyy-MM-dd"`만 자동 인식해
- `"2025/08/18"`처럼 **슬래시(`/`)가 들어간 형식은 포맷터를 직접 지정**해야 함
- 포맷터가 없으면 **DateTimeParseException 예외 발생**

---

### 📌 요약 포인트

- `LocalDate.parse()`는 기본 형식 아니면 반드시 `DateTimeFormatter` 필요
- 포맷에는 문자열에 사용된 구분자도 정확히 포함해야 함
- 자주 쓰는 포맷: `"yyyy/MM/dd"`, `"yyyy년 MM월 dd일"`, `"yyyy.MM.dd"`