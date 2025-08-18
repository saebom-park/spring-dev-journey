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
| Postman & Swagger | API 테스트 및 문서화 도구 |

---

## 📂 문서 구성

| 폴더명 | 설명 |
|-------------|------|
| `api-0-crud-habit/` | Habit CRUD (기본기, 네가 직접 구현한 베이스라인) |
| `api-1-crud/` | CRUD API 보강 (`@Transactional`, `ResponseEntity`) |
| `api-2-validation/` | 입력값 검증 (Bean Validation, `@Valid`) |
| `api-3-exception/` | 전역 예외 처리 (`@ControllerAdvice`) |
| `api-4-swagger/` | Swagger & Postman 테스트 |
| `api-5-paging/` | 페이징 처리 (Pageable, 정렬) |
| `...-mistakes.md` | 실수노트 |
| `...-extra.md` | 추가 개념 정리 |

---

## 🧭 학습 흐름

1. API-0 Habit CRUD를 직접 구현해 기본기를 확인
2. API-1~5 예시코드(Habit 보강판)와 실습코드(새 도메인)를 병행 학습
3. DTO, 입력값 검증, 에러 응답 구조를 단계별로 익힘
4. Swagger & Postman으로 테스트하며 API 사용성을 점검
5. Pageable을 활용해 실무형 API로 확장

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
