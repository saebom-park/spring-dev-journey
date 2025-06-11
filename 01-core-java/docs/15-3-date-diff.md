# [15-3단계] 날짜 차이 계산 (Period, ChronoUnit)

> “생일까지 며칠 남았지?”
> 
> 
> 👉 날짜 차이는 `Period` or `ChronoUnit`으로 계산해보자!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `Period.between(a, b)` | **년/월/일 기준** 차이 계산 (LocalDate 전용) |
| `ChronoUnit.DAYS.between(a, b)` | **일수 기준** 차이 계산 (정수형 결과) |
| `LocalDate` | 둘 다 날짜 객체로 입력 |
| a, b 순서 | `a → b` 방향 기준 (a가 과거면 양수, 미래면 음수) |

---

### 🧾 예시 코드

```java
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDiffExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2025, 12, 25);

        // Period로 차이 구하기
        Period diff = Period.between(today, birthday);
        System.out.println("생일까지: " + diff.getMonths() + "개월 " + diff.getDays() + "일");

        // ChronoUnit으로 일수 계산
        long days = ChronoUnit.DAYS.between(today, birthday);
        System.out.println("생일까지 총 " + days + "일 남음");
    }
}
```

---

### 📌 포인트 요약

- `Period`: 년/월/일 구분하여 차이 출력 (`getYears()`, `getMonths()`, `getDays()`)
- `ChronoUnit`: 날짜 간 간격을 **일수/월수/연도** 등으로 단순 계산 (`between()`)
- `a, b` 순서 중요 → `Period.between(오늘, 생일)` 과 `between(생일, 오늘)`은 부호 반대
- 실무에선 `ChronoUnit.DAYS.between()` 많이 씀 (정수형 결과가 다루기 편함)

---

### 🧪 실습 미션

> 🎯 목표: 두 날짜 사이의 차이를 계산해보자!
> 
1. `DateDiffPractice.java` 파일 생성
2. 아래 기능 구현:

```
✅ 오늘과 생일(2025-12-25) 사이 차이 계산
✅ Period로 월/일 차이 출력
✅ ChronoUnit으로 총 며칠 남았는지 출력
```