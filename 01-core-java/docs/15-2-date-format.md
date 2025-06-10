# [15-2단계] 날짜 포맷팅 (DateTimeFormatter)

> “오늘 날짜를 2025년 06월 10일 형식으로 출력하려면?”
> 
> 
> 👉 바로 `DateTimeFormatter`로 포맷을 지정해야지!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `DateTimeFormatter` | 날짜/시간을 문자열로 변환할 때 사용하는 포맷터 |
| `ofPattern("yyyy-MM-dd")` | 원하는 형식 지정 |
| `format()` | 날짜 → 문자열 변환 |
| `parse()` | 문자열 → 날짜로 역변환 |

---

### 🧾 예시 코드

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        // 날짜 → 문자열 포맷
        String formatted = today.format(formatter);
        System.out.println("오늘 날짜 (포맷): " + formatted);

        // 문자열 → 날짜 파싱
        String dateStr = "2025-10-31";
        LocalDate parsed = LocalDate.parse(dateStr); // ISO 기본 형식이라 formatter 없이도 가능
        System.out.println("파싱된 날짜: " + parsed);
    }
}
```

---

### 📌 포인트 요약

- `format()` → **LocalDate → String**
- `parse()` → **String → LocalDate**
- 포맷 문자열 패턴:
    - `"yyyy"` → 연도
    - `"MM"` → 월
    - `"dd"` → 일
- `"yyyy/MM/dd"` 같은 커스텀 형식도 자유롭게 가능

---

### 🧪 실습 미션

> 🎯 목표: 오늘 날짜를 "2025년 06월 10일" 형식으로 출력해보자!
> 
1. `FormatPractice.java` 파일 생성
2. 다음 기능 구현:

```
✅ 오늘 날짜를 "yyyy년 MM월 dd일" 형식으로 포맷하여 출력
✅ 문자열 "2030-01-01"을 날짜로 파싱하여 출력
```