# [API-2단계] 실수노트

> 💻 실습 코드: DiaryController.java
> 

---

### 😅 실수 1 — `@Valid` import 경로 잘못 지정

```java
import org.springframework.web.bind.annotation.Valid;
```

✅ 정답:

```java
import jakarta.validation.Valid;
```

📌 **설명**:

`@Valid`는 Spring이 아니라 Bean Validation 표준 애노테이션.

Spring Boot 3.x부터는 `jakarta.validation.Valid`를 import 해야 정상 동작.

---

### 😅 실수 2 — 검증 실패 응답 반환 타입 불일치

```java
public ResponseEntity<DiaryResponseDto> createDiary(...) {
    if (bindingResult.hasErrors()) {
        List<String> errors = ...
        return ResponseEntity.badRequest().body(errors); // ❌ 타입 불일치
    }
}
```

✅ 정답:

```java
public ResponseEntity<?> createDiary(...) {
    if (bindingResult.hasErrors()) {
        List<String> errors = ...
        return ResponseEntity.badRequest().body(errors); // ✅
    }
}
```

📌 **설명**:

검증 실패 시 `List<String>`을 반환하므로, 반환 타입을 범용 `ResponseEntity<?>`로 지정해야 컴파일 에러가 발생하지 않음.

---

### 😅 실수 3 — `DefaultMessageSourceResolvable` import 누락

```java
// import 없음 → 빨간줄 발생
List<String> errors = bindingResult.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();
```

✅ 정답:

```java
import org.springframework.context.support.DefaultMessageSourceResolvable;
```

📌 **설명**:

Spring이 검증 에러 메시지를 감싸서 제공하는 객체.

`getDefaultMessage()` 메서드를 메서드 참조로 사용하려면 올바른 import가 필요함.

---

### 📌 요약 포인트

- `@Valid`는 `jakarta.validation.Valid`를 import해야 한다.
- 검증 실패 시 반환 타입은 `ResponseEntity<?>`로 선언해주는 것이 안전하다.
- `DefaultMessageSourceResolvable`는 반드시 `org.springframework.context.support` 패키지에서 import해야 한다.