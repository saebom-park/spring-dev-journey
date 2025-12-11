# 04-api-development
RESTful API를 단계적으로 개선하며 설계·검증·상태코드·예외 처리·문서화까지 실무형 API 개발 흐름을 학습하는 단계입니다.  
예시는 Habit(Baseline), 실습은 Diary 도메인을 활용해 개념을 직접 구현합니다.

---

## 🗂️ 구성 안내
- `api-0-crud-habit/` : 기본 CRUD 예시(테스트 미션). 개념 문서는 없고 README 시나리오만 존재.
- `api-diary/` : Diary API 실습 폴더. 단계별 실습 README와 관련 docs 포함.
- `api-diary/docs/` : API-1 ~ API-5 수업자료(md 파일들).

---

## 🎯 학습 목표
- REST API 설계 원리와 HTTP 상태 코드 체계를 이해한다.
- ResponseEntity를 통해 상황별 응답을 명확하게 정의한다.
- DTO(Request/Response)를 활용하여 안전한 API 데이터 모델링을 익힌다.
- Bean Validation 기반의 입력값 검증 흐름(@Valid, @NotBlank 등)을 적용한다.
- 전역 예외 처리(Global Exception Handler)를 구성하고 일관된 에러 포맷을 제공한다.
- Swagger(OpenAPI), Postman으로 API 테스트 및 문서화를 수행한다.
- Spring Data JPA를 이용해 페이징·정렬 API를 구현한다.

---

## 🧠 전체 학습 구성 (총 6단계)

| 단계    | 주제                  | 설명                                      |
|-------|---------------------|-----------------------------------------|
| API-0 | Habit CRUD Baseline | 현재 실력 점검을 위한 테스트 미션 + 기본 CRUD 구조 구현     |
| API-1 | CRUD 고도화 & 상태코드     | ResponseEntity, 201/204 등 실무 상태코드 적용    |
| API-2 | 입력값 검증 (Validation) | Bean Validation (@Valid) 기반 요청 검증       |
| API-3 | 전역 예외 처리            | Error Response 포맷, 예외별 상태코드(404/400) 설계 |
| API-4 | API 문서화             | Swagger/OpenAPI 문서화 + Postman 테스트       |
| API-5 | 조회 API 확장           | 페이징·정렬 기반 실무형 조회 API 설계                 |

---

## 📂 폴더 구조

| 폴더명                                           | 설명                                       |
|-----------------------------------------------|------------------------------------------|
| `api-0-crud-habit/`                           | Habit CRUD 예시. docs 없음. README 시나리오만 존재. |
| `api-diary/`                                  | Diary API 실습. 단계별 미션 README 존재.          |
| `api-diary/docs/api-1-crud-responseentity.md` | API-1 수업자료                               |
| `api-diary/docs/api-2-validation.md`          | API-2 수업자료                               |
| `api-diary/docs/api-3-global-exception.md`    | API-3 수업자료                               |
| `api-diary/docs/api-4-swagger-postman.md`     | API-4 수업자료                               |
| `api-diary/docs/api-5-paging-sorting.md`      | API-5 수업자료                               |

---

## 📄 문서 구성 규칙

각 단계는 다음 형식에 맞춰 문서를 구성합니다.

| 파일명 형식                       | 설명                                  |
|------------------------------|-------------------------------------|
| `api-<단계>-<주제>.md`           | 해당 단계의 수업자료 (개념 요약 + 예시 코드 + 실습 흐름) |
| `api-<단계>-<주제>-questions.md` | 단계별 질문 정리 (필요 시 생성)                 |
| `api-<단계>-<주제>-mistakes.md`  | 실습 중 발생한 오류·실수 기록 (필요 시 생성)         |
 
> 예시·실습 코드 흐름은 기본 수업자료 또는 README 내부에서 직접 설명합니다.

---

## 🧭 학습 흐름

1. **API-0: Habit CRUD**
    - JPA + Controller + Service + Repository 구조만으로 CRUD를 직접 구현
    - 현재 실력을 점검하는 베이스라인 실습
    - 개념 자료는 없으며 README(시나리오)가 가이드 역할 수행

2. **API-1 ~ API-5: Diary API 고도화 실습**
    - Habit 예시 코드로 개념 확인
    - Diary 도메인을 기반으로 직접 구현하며 실무 감각 내재화
    - 상태코드 → 검증 → 예외 처리 → 문서화 → 페이징 순으로 API 품질을 단계적으로 개선

3. 각 단계의 docs/*.md 파일로 수업 개념을 정리하고,  
   README 기반으로 Diary API를 직접 작성하면서 실습한다.

---

## 📌 작성 기준

- JSON 기반 REST API 구성
- Controller → Service → Repository 계층 분리
- DTO 사용으로 Entity 직접 노출 금지
- ResponseEntity로 상태코드 제어
- 전역 예외 처리로 일관된 오류 응답 제공
- Swagger / Postman 기반 문서화·테스트
- 실무형 페이징·정렬 API 적용