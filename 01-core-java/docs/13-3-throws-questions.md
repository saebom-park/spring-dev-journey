# [13-3단계] 질문노트: throws 키워드 (LoginValidator.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: `LoginValidator.java` 실습 기준)
> 

---

### 💡 1. `IllegalArgumentException`, `throws`, 예외 흐름

| 질문 | 답변 요약 |
| --- | --- |
| `IllegalArgumentException`은 SafeCalculator에서 throws 없이 썼는데, LoginValidator에선 왜 throws 써? | `IllegalArgumentException`은 **Unchecked Exception**이기 때문에 throws 없이 사용 가능함. LoginValidator에선 연습 목적으로 `throws`를 명시한 것뿐 |
| 어떤 방식이 더 좋은 실무 방식이야? | 실무에선 `throws`를 생략하고 `throw`만 쓰는 **Unchecked 방식**을 더 많이 씀. Checked Exception은 파일/DB처럼 외부 환경에만 주로 사용 |
| throws 사용하는 메서드는 무조건 `void`여야 해? | ❌ 아님! `throws`는 반환형과 무관하게 어떤 메서드에도 붙일 수 있음. `int`, `String` 등 리턴하면서도 `throws` 가능 |

---

### 💡 2. Checked Exception vs Unchecked Exception

| 질문 | 답변 요약 |
| --- | --- |
| 우리가 작성한 코드들, 대부분 Unchecked로 해도 되지 않아? | 맞아! 실습에선 throws 연습을 위해 Checked Exception을 쓴 거지만, 실무에서는 대부분 Unchecked(Exception)으로 처리함 |
| 그럼 `IllegalArgumentException`으로도 똑같은 기능 되잖아? | 맞아! 조건만 잘 걸어주면 Checked Exception 없이도 예외 처리 가능함. Checked는 오히려 코드 복잡도를 높이기 때문에 가급적 피하는 경우가 많음 |
| 실무에선 내부 로직에서는 throws 선언 안 하겠네? | ✅ 정답! **파일, DB, 네트워크** 같은 외부 리소스가 아닌 이상, 로직에서는 `throw`만 쓰고 `throws`는 생략하는 게 보통 |

---

### 💡 3. 예외 처리 실무 감각 요약

| 항목 | 실무에서는 이렇게 써요 |
| --- | --- |
| 외부 환경의 문제 (파일, DB, API) | **Checked Exception** → 반드시 `throws` or `try-catch` |
| 내부 로직의 문제 (입력값 검사, 조건) | **Unchecked Exception** → `throw`만 사용, `throws` 생략 |
| 실습 코드들 | Checked Exception은 연습용. 실무에서는 `IllegalArgumentException`, `RuntimeException` 등으로 처리하는 게 일반적 |

---

### 🌱 정리 키워드

- `throw`는 예외를 발생시키는 키워드 (예: `throw new IllegalArgumentException(...)`)
- `throws`는 메서드가 예외를 발생시킬 수 있다고 미리 선언하는 키워드
- **Unchecked Exception**은 `throws` 없어도 되고, 실무에서 더 자주 사용됨
- 실무에서는 **외부 환경**과 관련된 예외만 Checked로, 그 외는 대부분 Unchecked로 처리함