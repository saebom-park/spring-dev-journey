# [15-2단계] 질문노트: 날짜 포맷팅 (FormatPractice.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: `FormatPractice.java` 실습 기준)
> 

---

### 💡 1. 날짜 포맷 파싱 관련 질문

| 질문 | 답변 요약 |
| --- | --- |
| `"2030-01-01"`은 그냥 `parse()` 되는데, `"2030/01/01"`은 왜 안돼? | `LocalDate.parse()`는 기본적으로 `"yyyy-MM-dd"` 형식만 자동 인식. `"yyyy/MM/dd"`처럼 다르면 `DateTimeFormatter` 필요 |
| `"yyyy/MM/dd"` 형식 포맷은 어떻게 설정해? | `DateTimeFormatter.ofPattern("yyyy/MM/dd")` 처럼, 문자열에 들어간 **구분자(`/`, `.`, `년`)까지 포함해서 직접 지정해야 함** |
| 날짜 포맷 문자에는 어떤 것들이 있어? | `yyyy` = 연도, `MM` = 월, `dd` = 일. `HH:mm:ss`, `yyyy.MM.dd`, `yyyy년 MM월 dd일` 등으로 다양하게 조합 가능 |

---

### 🌱 정리 키워드

- `DateTimeFormatter`는 포맷 지정용 클래스
- `parse()`는 문자열이 **기본 형식(yyyy-MM-dd)**일 때만 formatter 없이 가능
- 구분자(`/`, `년`, `.`)가 포함된 문자열은 반드시 **포맷터 지정 필요**