# [15-1단계] 날짜 & 시간 API (LocalDate, LocalDateTime)

> “날짜 계산, 출력, 오늘 날짜 구할 땐?”
> 
> 
> 👉 바로 `java.time` 패키지의 **LocalDate / LocalDateTime**!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `LocalDate` | 날짜(년, 월, 일)를 표현하는 클래스 |
| `LocalTime` | 시간(시, 분, 초)을 표현하는 클래스 |
| `LocalDateTime` | 날짜 + 시간 통합 표현 |
| `now()` | 현재 날짜/시간 구하기 |
| `of(년, 월, 일)` | 특정 날짜 생성 |
| `plusDays()`, `minusDays()` | 날짜 더하기 / 빼기 |
| `parse()` | 문자열을 날짜로 파싱 |

---

### 🧾 예시 코드

```java
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateExample {
    public static void main(String[] args) {
        // 현재 날짜와 시간
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("오늘 날짜: " + today);
        System.out.println("현재 시간: " + now);

        // 날짜 연산
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("내일: " + tomorrow);
        System.out.println("어제: " + yesterday);

        // 특정 날짜 생성
        LocalDate birthDay = LocalDate.of(1996, 12, 25);
        System.out.println("생일: " + birthDay);

        // 문자열 → 날짜 파싱
        LocalDate parsed = LocalDate.parse("2025-07-01");
        System.out.println("파싱된 날짜: " + parsed);
    }
}
```

---

### 📌 포인트 요약

- `LocalDate`는 날짜 전용 클래스, `LocalDateTime`은 날짜 + 시간
- `now()`로 현재 날짜/시간 가져오기
- `plusDays()`, `minusWeeks()` 등으로 직관적 연산 가능
- `of(YYYY, MM, DD)`로 날짜 지정
- `parse("2025-12-25")`로 문자열을 날짜로 만들 수 있음
- 과거의 `java.util.Date`보다 **가독성, 안전성, 기능성** 모두 개선된 API

---

### 🧪 실습 미션

> 🎯 목표: 날짜 다루는 기본기 연습!
> 
1. `DatePractice.java` 파일 생성
2. 아래 기능 구현:

```
✅ 오늘 날짜 출력
✅ 오늘 기준 3일 전과 5일 후 출력
✅ 특정 날짜(예: 2000년 1월 1일) 생성
✅ "2025-10-31" 문자열을 날짜로 변환 후 출력
```