# [API-3단계] 전역 예외 처리 (global-exception-handler)

> 이번 단계에서는 컨트롤러마다 흩어져 있던 예외 처리를
> 
> 
> `@RestControllerAdvice`로 모아 **전역 예외 처리 체계**를 설계합니다.
> 
> 잘못된 입력/조회 불가/서버 오류를 명확히 구분하고,
> 
> **일관된 에러 응답 포맷(JSON)**을 제공하는 방법을 학습합니다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| @RestControllerAdvice | 애플리케이션 전역에서 발생하는 예외를 한곳에서 처리 |
| @ExceptionHandler | 특정 예외 유형을 잡아 처리하는 메서드 지정 |
| ErrorResponse | 에러 응답 표준화 객체 (timestamp, status, message 등) |
| IllegalArgumentException | 잘못된 요청 데이터/없는 ID 조회 시 404로 변환 |
| MethodArgumentNotValidException | DTO 검증 실패 시 발생, 400으로 변환 |
| 실무 포인트 | 예외가 흩어져 있으면 유지보수 지옥 → 전역 핸들러로 통일 |

---

## 🧾 예시 코드 (Habit)

### 공통 에러 응답 객체

```java
package com.springlab19.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;

    public ErrorResponse(int status, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public List<String> getErrors() { return errors; }
}

```

---

### 전역 예외 처리기

```java
package com.springlab19.exception;

import com.springlab19.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // DTO 검증 실패 → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors));
    }

    // 잘못된 ID 조회 → 404
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage())));
    }

    // 그 외 모든 예외 → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of("서버 오류가 발생했습니다.")));
    }
}

```

---

### 잘못된 요청 시 응답 예시

```json
{
  "timestamp": "2025-08-20T11:45:12",
  "status": 400,
  "errors": [
    "제목은 필수입니다.",
    "내용은 비워둘 수 없습니다."
  ]
}

```

---

## 📌 포인트 요약

- **컨트롤러 개별 try-catch → ❌** → 전역 `@RestControllerAdvice`로 통일
- 예외 유형별 상태코드 구분:
    - DTO 검증 실패 → 400 Bad Request
    - 잘못된 ID 조회 → 404 Not Found
    - 기타 → 500 Internal Server Error
- 에러 응답은 항상 동일 JSON 구조(`ErrorResponse`)
- 프론트엔드와 협업 시 **예측 가능한 에러 처리**가 가능해짐
- API-2까지는 “자동 400”만 가능 → API-3부터 **실무형 에러 관리 체계** 완성

---

## 🧪 실습 미션 (Diary)

🎯 목표: **Diary API에 전역 예외 처리기를 적용하여 에러 응답을 표준화한다.**

1. `ErrorResponse` 클래스 작성 (`timestamp`, `status`, `errors`)
2. `GlobalExceptionHandler` 생성 → 아래 규칙 반영
    - DTO 검증 실패(`MethodArgumentNotValidException`) → 400
    - 존재하지 않는 Diary ID → 404
    - 기타 예상 못한 오류 → 500
3. Postman으로 테스트
    - 잘못된 입력(title/content 비어있음) → 400 응답 JSON
    - 존재하지 않는 ID 조회/삭제 → 404 응답 JSON
    - 인위적 오류 발생 → 500 응답 JSON