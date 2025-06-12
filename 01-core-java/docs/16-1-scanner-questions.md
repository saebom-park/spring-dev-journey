# [16-1단계] 질문노트: Scanner 입력 (ScannerPractice.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: `ScannerPractice.java` 실습 기준)
> 

---

### 💡 1. `nextInt()` 다음에 오는 `nextLine()`이 왜 바로 넘어가나요?

| 질문 | 답변 요약 |
| --- | --- |
| `nextInt()` 다음에 `nextLine()` 썼는데 왜 입력 없이 넘어가? | `nextInt()`는 숫자만 읽고, **엔터는 버퍼에 남겨둠** |
| `nextLine()`은? | 남아 있는 **엔터(\n)** 를 그대로 읽어서 **빈 문자열이 들어감** |
| 해결법은? | `nextInt()` 후에 `scanner.nextLine();` 한 줄 더 넣어서 **엔터 소비**하기 |

---

### 🌱 정리 키워드

- `nextInt()`는 숫자만, `nextLine()`은 한 줄 전체
- `nextInt()` → `nextLine()` 쓰면 **엔터 처리 꼭 해야 함**
- 중간에 `scanner.nextLine();` 넣으면 버그 해결!