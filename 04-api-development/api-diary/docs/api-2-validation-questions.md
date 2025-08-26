# [API-2단계] 질문노트: 입력값 검증

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab20.controller 실습 기준)
> 

---

### 💡 1. 컨트롤러에서 `import org.springframework.web.bind.annotation.Valid;` 했는데 왜 안 먹어?

| 질문 | 답변 요약 |
| --- | --- |
| 컨트롤러에서 `@Valid` import 했는데 동작 안 해 | `@Valid`는 Spring이 아니라 Bean Validation 표준이야 → `jakarta.validation.Valid`를 import 해야 함 (Spring Boot 3.x부터는 무조건 jakarta 패키지 사용) |

---

### 💡 2. Postman에서 400은 떨어지는데 왜 검증 메시지는 안 나오지?

| 질문 | 답변 요약 |
| --- | --- |
| 검증 실패 시 `message`가 안 내려옴 | Spring Boot 기본 에러 응답은 `"Bad Request"`만 보여줌. → 해결 방법: ① 컨트롤러에서 `BindingResult`로 직접 에러 메시지 추출, ② API-3 단계에서 전역 예외 처리(`@ControllerAdvice`)로 커스터마이즈 |

---

### 💡 3. `BindingResult`는 뭐 import 해야 해?

| 질문 | 답변 요약 |
| --- | --- |
| `BindingResult` import 경로가 헷갈림 | `org.springframework.validation.BindingResult`를 import 해야 함. DTO 검증 어노테이션(`@NotBlank`, `@Min`)은 `jakarta.validation.constraints.*`, `@Valid`는 `jakarta.validation.Valid` 사용 |

---

### 💡 4. `error -> error.getDefaultMessage()` 대신 `DefaultMessageSourceResolvable::getDefaultMessage`로 쓰는 건 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| `DefaultMessageSourceResolvable::getDefaultMessage`가 뭐지? | 람다(`error -> error.getDefaultMessage()`)를 더 축약한 메서드 참조 문법. `error` 객체는 내부적으로 `DefaultMessageSourceResolvable` 타입이라서 `getDefaultMessage()` 메서드를 직접 참조 가능. 기능은 동일하고 문법만 짧아진 것. |