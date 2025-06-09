# [15-1단계] 추가 개념 정리

### ✅ 주제: `switch`문에서 요일 enum 처리하기

---

### 💡 핵심 개념 요약

| 구분 | 기본 switch문 | 최신 switch 표현식 (Java 14+) |
| --- | --- | --- |
| 문법 | `case ...: break;` | `case ... -> 결과값` |
| 반환 | ❌ 직접 반환 불가 (변수에 대입하려면 따로 작성) | ✅ `switch (...) {}` 전체가 값으로 반환됨 |
| 가독성 | 비교적 장황함 | 매우 간결하고 명확함 |
| enum 대응 | 가능 (`case MONDAY:` 등) | 동일하게 가능 (`case MONDAY ->`) |
| `break` 필요 여부 | 필요 | 불필요 |

---

### 🧾 예시 코드 1 — **기본 switch문**

```java
DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
String koreanDay = "";

switch (dayOfWeek) {
    case MONDAY:
        koreanDay = "월요일";
        break;
    case TUESDAY:
        koreanDay = "화요일";
        break;
    // 생략 ...
}
System.out.println("오늘은 " + koreanDay + "입니다.");
```

---

### 🧾 예시 코드 2 — **최신 switch 표현식 (Java 14+)**

```java
DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

String koreanDay = switch (dayOfWeek) {
    case MONDAY    -> "월요일";
    case TUESDAY   -> "화요일";
    case WEDNESDAY -> "수요일";
    case THURSDAY  -> "목요일";
    case FRIDAY    -> "금요일";
    case SATURDAY  -> "토요일";
    case SUNDAY    -> "일요일";
};

System.out.println("오늘은 " + koreanDay + "입니다.");
```

---

### 📌 포인트 요약

- `switch`는 enum 타입(`DayOfWeek`)과도 잘 어울림
- 자바 최신 버전에서는 `switch →` 문법으로 **더 간결하고 안전하게 작성 가능**
- 기존 방식은 `break` 누락 시 실수 발생 가능 → 최신 방식이 더 권장됨
- 아직 Java 14 이상이 아니거나 실무 버전 낮으면 **기본 switch문 우선 사용**