# 🧷 06. 실전 API 개발

> 이 단계에서는 RESTful API를 직접 설계하고 구현하는 과정을 학습합니다.  
> API-0에서 확인한 부족한 부분(상태 코드, ResponseEntity)을 보강하고,  
> DTO 설계·입력값 검증·예외 처리·API 테스트까지 **실무 API 기본기**를 완성합니다.

---

## ✅ 학습 목표

- CRUD API의 설계와 구현 능력 습득
- REST API 원리와 HTTP 상태 코드 활용
- DTO를 통한 안전한 요청/응답 구조 설계
- 입력값 검증(@Valid)과 전역 예외 처리 흐름 구성
- Postman & Swagger로 API를 테스트하고 문서화

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| CRUD API | POST/GET/PUT/DELETE 엔드포인트 구현 |
| Controller | `@RestController`, `@GetMapping`, `@PostMapping` |
| 요청 매핑 | `@PathVariable`, `@RequestBody`, `@RequestParam` |
| DTO 설계 | 요청/응답 데이터 객체 분리 |
| 상태 코드 | `201`, `200`, `204`, `400`, `404` 등 적절한 사용 |
| 입력값 검증 | `@Valid`, `@NotNull`, `@Size`, `BindingResult` |
| 예외 처리 | `@ExceptionHandler`, `@ControllerAdvice` |
| 표준 응답 구조 | ApiResponse, ErrorResponse 등 공통 포맷 |
| API 테스트 | Postman, Swagger 활용법 |

---

## 📂 문서 구성

| 파일명 | 설명 |
|-------------|------|
| `api-0-crud-habit/` | 사전 점검(테스트) — 지금까지 배운 범위로 구현한 Habit CRUD |
| `api-1-crud.md` | API-1: CRUD API 실습 (새로운 도메인 + ResponseEntity/상태코드 포함) |
| `api-2-validation.md` | API-2: 입력값 검증 실습 |
| `api-3-exception.md` | API-3: 전역 예외 처리 실습 |
| `api-4-testing.md` | API-4: Postman & Swagger 테스트 |
| `api-5-paging.md` | API-5: 페이징 처리 실습 |
| `README.md` | API 단계 전체 가이드 |

---

## 🧭 학습 흐름

1. `api-0-crud-habit.md` — 지금까지 배운 개념 총집합 (Habit CRUD)
2. 본격 CRUD API 설계 + ResponseEntity로 상태 코드 제어
3. DTO를 통해 요청/응답 데이터를 분리
4. 입력값 검증과 전역 예외 처리 도입
5. Postman & Swagger로 실제 API 호출·테스트 진행

---

## 📌 작성 기준

- JSON 기반 REST API 설계
- Controller → Service → Repository 계층 분리
- ResponseEntity 활용한 상태 코드 제어
- DTO를 통해 Entity 직접 노출 금지
- 전역 예외 처리와 통일된 응답 구조 적용

---

> “API는 백엔드와 프론트의 소통 언어다.”  
>  
> 명확한 요청·응답 구조, 상태 코드, 예외 대응까지.  
> **실무 백엔드 개발자**로 가는 길의 핵심이 바로 여기 있다!
