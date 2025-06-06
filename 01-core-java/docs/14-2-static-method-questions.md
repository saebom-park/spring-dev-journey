# [14-2단계] 질문노트: static 메서드 (StringUtil.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: `StringUtil.java` 실습 기준)
> 

---

### 💡 1. 문자열 뒤집기와 대문자 변환

| 질문 | 답변 요약 |
| --- | --- |
| 문자열을 뒤집는 메서드는 뭐가 있어? | `StringBuilder`를 사용해서 `.reverse()` 메서드로 뒤집을 수 있어요. 마지막에 `.toString()`으로 다시 String으로 변환해야 해요. |
| `StringBuilder`는 왜 쓰는 거야? | `String`은 한 번 만들어지면 수정할 수 없는(불변) 자료형이라, 문자열을 수정하려면 `StringBuilder` 같은 가변 객체가 필요해요. |
| `new StringBuilder(s).reverse().toString()` 이건 어떻게 동작하는 거야? | 1️⃣ `new StringBuilder(s)` → 문자열을 수정 가능한 객체로 바꿔줌2️⃣ `.reverse()` → 문자열을 뒤집어줌3️⃣ `.toString()` → 다시 String 타입으로 변환 |
| 문자열을 대문자로 바꾸는 방법은? | 문자열에서 `.toUpperCase()` 메서드를 호출하면 대문자로 변환돼요. |
| 소문자로 바꾸는 건 어떻게 해? | `.toLowerCase()` 메서드를 사용하면 소문자로 변환돼요! |

---

### 🌱 정리 키워드

- `StringBuilder`는 문자열을 수정할 수 있는 객체
- `new StringBuilder(s).reverse().toString()` → 문자열 뒤집기
- `s.toUpperCase()` → 대문자 변환
- `s.toLowerCase()` → 소문자 변환
- `String`은 불변이라 직접 수정할 수 없음 → 수정 작업은 `StringBuilder`로