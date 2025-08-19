# 🧷 06. 실전 API 개발

> 이 섹션에서는 **하나의 도메인(API)을 점진적으로 개선**하며  
> 실무 수준의 RESTful API 개발 흐름을 학습합니다.  
> 
> - **예시코드**: Habit (api-0 베이스라인을 단계별로 보강)  
> - **실습미션**: Diary (새 도메인을 직접 구현하며 개념 내재화)

---

## ✅ 학습 목표

- REST API 설계 원리와 HTTP 상태 코드 체계 이해
- ResponseEntity로 상황별 응답 제어
- DTO를 통한 요청/응답 데이터 구조화
- Bean Validation 기반의 입력값 검증
- 전역 예외 처리와 통일된 에러 응답 포맷 설계
- Swagger & Postman으로 API 문서화 및 테스트
- Spring Data JPA 기반 페이징/정렬 처리

---

## 📚 학습 주제

| 단계 | 주제 | 설명 |
|------|------|------|
| API-0 | Habit CRUD (Baseline) | 지금까지 배운 범위로 구현한 기본 CRUD |
| API-1 | CRUD API 고도화 (ResponseEntity & 상태코드) | 모든 응답이 200 OK였던 한계 → 상황별 상태코드(201/204 등) 적용 |
| API-2 | 입력값 검증 (Validation DTO) | 잘못된 요청을 차단하는 Bean Validation(@Valid, @NotBlank, @Min) |
| API-3 | 전역 예외 처리 (Global Exception Handler) | 예외별 상태코드(404/400)와 에러 응답 포맷 제공 |
| API-4 | API 문서화 & 테스트 (Swagger + Postman) | OpenAPI/Swagger로 문서화, Postman으로 테스트 시나리오 관리 |
| API-5 | 조회 API 확장 (페이징 & 정렬) | 단순 findAll()을 실무형 페이징/정렬 API로 개선 |

---

## 📂 문서 구성

| 파일명 | 설명 |
|-------------|------|
| `api-0-crud-habit/` | Habit CRUD (베이스라인, 수준 점검) |
| `api-diary/` | Diary API (API-1~5 단계별로 점진적 개선) |
| `api-1-crud-responseentity.md` | API-1 수업자료 |
| `api-2-validation.md` | API-2 수업자료 |
| `api-3-global-exception.md` | API-3 수업자료 |
| `api-4-swagger-postman.md` | API-4 수업자료 |
| `api-5-paging-sorting.md` | API-5 수업자료 |
| `README.md` | 섹션 전체 가이드 |

---

## 🧭 학습 흐름

1. `api-0-crud-habit` — Habit CRUD로 현재 수준 점검
2. API-1부터는 Diary 도메인을 기반으로 단계별 업그레이드
3. 각 단계에서 **Habit 예시코드**로 개념을 확인
4. 같은 개념을 **Diary 실습미션**으로 직접 구현
5. 점진적으로 API를 실무 수준으로 보강

---

## 📌 작성 기준

- JSON 기반 REST API 설계
- Controller → Service → Repository 계층 분리
- ResponseEntity 활용한 상태코드 제어
- DTO를 통한 Entity 직접 노출 금지
- 전역 예외 처리와 통일된 응답 구조 적용
- Swagger & Postman을 통한 테스트·문서화
- 실무 수준의 페이징/정렬 기능 제공

---

> “API는 백엔드와 프론트의 소통 언어다.”  
>  
> 상태코드·검증·예외처리·문서화까지.  
> **Diary API를 점점 발전시켜가며 실무 감각을 쌓는다!**
