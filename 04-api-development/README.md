# 🧷 04. API 개발 실전

> 이 단계에서는 RESTful API를 직접 설계하고 구현하는 과정을 학습합니다.  
> 요청-응답 구조, DTO 설계, 유효성 검증, 상태 코드 등 실무 백엔드 API 개발의 기본기를 다집니다.

---

## ✅ 학습 목표

- REST API의 원리와 설계 규칙 이해
- Spring MVC 구조 기반의 API 구현 실습
- 요청 DTO / 응답 DTO 설계 능력 습득
- 입력값 검증과 예외 처리 흐름 구성

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| Controller | `@RestController`, `@GetMapping`, `@PostMapping` |
| 요청 매핑 | `@RequestParam`, `@PathVariable`, `@RequestBody` |
| DTO 설계 | 요청/응답 데이터를 객체로 전달 |
| 유효성 검증 | `@Valid`, `BindingResult`, `@NotNull`, `@Size` 등 |
| 예외 처리 | `@ExceptionHandler`, `@ControllerAdvice`, 전역 처리 |
| 표준 응답 구조 | 성공/실패 응답 통일된 포맷으로 관리 |
| 상태 코드 설계 | `200`, `400`, `404`, `500` 등의 의미와 사용 기준 |
| Postman & Swagger | API 테스트 및 문서화 도구

---

## 📂 문서 구성

| 파일명 예시 | 설명 |
|-------------|------|
| `01-controller-basics.md` | 기본 요청/응답 흐름 실습 |
| `02-dto-validation.md` | DTO와 입력 검증 실습 |
| `03-exception-handling.md` | 예외 처리 및 응답 포맷 통일 |
| `04-api-testing.md` | Postman, Swagger 활용법 |
| `...-mistakes.md` | 실무에서 자주 겪는 API 오류 정리 |
| `...-extra.md` | API 버전 관리, 응답 Wrapping 전략 등 |

---

## 🧭 학습 흐름

1. 간단한 API부터 시작해 컨트롤러의 흐름에 익숙해지기
2. DTO를 활용해 계층 간 명확한 데이터 전달 연습
3. 유효성 검증, 에러 응답 구조를 직접 설계
4. 실제 테스트 도구를 통해 API 사용자의 입장에서 점검

---

## 📌 작성 기준

- JSON 기반 REST API 설계
- 계층 분리 구조 (Controller → Service → Repository)
- 공통 응답 포맷 구조화 (`ApiResponse`, `ErrorResponse` 등)

---

> “API는 백엔드와 프론트의 소통 언어야.”  
>  
> 요청과 응답의 명확한 구조, 예외 상황 대응까지  
> **진짜 실무 백엔드 개발자**가 되기 위한 핵심이 여기 다 있어!