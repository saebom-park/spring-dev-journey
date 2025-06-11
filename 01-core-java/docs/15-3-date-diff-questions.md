# [15-3단계] 질문노트: 날짜 차이 계산 (DateDiffPractice.java)

💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약

(코드: `DateDiffPractice.java` 실습 기준)

---

### 💡 1. ChronoUnit 관련 질문

| 질문 | 답변 요약 |
| --- | --- |
| `ChronoUnit` 이거 어떻게 읽어? | “크로노유닛”이라고 읽어. `Chrono`는 시간, `Unit`은 단위. 즉, “시간 단위 열거형(enum)” |
| `ChronoUnit.DAYS.between(...)`는 왜 long 타입이야? | 날짜 차이는 수백만 일도 될 수 있어서 `int` 대신 `long`을 반환해. 실무에서도 long이 안전함 |
| `ChronoUnit.DAYS`는 `Period`처럼 `getDays()` 같은 거 못 써? | 못 써! `ChronoUnit.between()`은 `long` 타입 정수로만 결과가 나옴. 월/일/년 따로 보려면 `Period` 사용해야 해 |

---

### 🌱 정리 키워드

- `ChronoUnit` = 시간 단위 enum (`DAYS`, `MONTHS`, `YEARS` 등)
- `between(a, b)`는 `long` 타입 반환
- `Period`는 날짜 간 차이를 `년/월/일`로 쪼개는 객체