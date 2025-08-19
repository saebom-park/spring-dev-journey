# [API-1~5] Diary API 실습 프로젝트

> 💬 이 프로젝트는 **실전 API 개발(섹션 6)** 수업에서 사용하는  
> Diary 도메인 기반 실습 프로젝트입니다.  
> API-1부터 API-5까지 순차적으로 보강하며 **실무형 REST API**를 완성합니다.

---

## 💡 시나리오

사용자가 매일 작성하는 **일기(Diary)**를 기록·관리할 수 있는 API를 설계하려고 합니다.  

예시 일기:
- 제목: "운동 다녀온 날", 내용: "헬스장에서 하체 운동"  
- 제목: "책 읽은 날", 내용: "객체지향의 사실과 오해 1장 정리"  

관리자는 다음 기능을 통해 일기를 관리합니다:

- 새로운 일기를 등록할 수 있음  
- 등록된 일기를 조회할 수 있음  
- 일기를 수정하거나 삭제할 수 있음  
- 목록 조회 시 페이징/정렬 가능  

---

## 📋 요구사항

- Spring Boot + JPA + MySQL 기반 프로젝트 구성
- `Diary` 엔티티 설계
  - id(Long)
  - title(String)
  - content(String)
  - createdDate(LocalDateTime)
- Repository, Service, Controller 계층 분리
- 단계별 기능 보강:
  - API-1 → CRUD + ResponseEntity + 상태코드
  - API-2 → Bean Validation 검증
  - API-3 → 전역 예외 처리 및 에러 응답 포맷
  - API-4 → Swagger & Postman 문서화/테스트
  - API-5 → 페이징 & 정렬
- **패키지 규칙**: Gradle `group = 'com.springlab19'` ↔ 최상위 패키지 `com.springlab19` (1:1 매핑), 실습 코드는 `com.springlab19.practice` 하위에 배치

---

## 🎯 체크리스트

- ✅ Spring Boot + JPA + MySQL 환경 설정 (build.gradle, application.yml)
- ✅ `Diary` 엔티티 설계
- ✅ `DiaryRepository` (JpaRepository 상속) 구현
- ✅ `DiaryService` CRUD + ResponseEntity 적용
- ✅ `DiaryController` REST API 매핑
- ✅ Validation 검증 어노테이션 적용
- ✅ 전역 예외 처리기(GlobalExceptionHandler) 작성
- ✅ Swagger UI + Postman 테스트 시나리오 구성
- ✅ 페이징/정렬 API 구현 및 테스트

---

## 📂 폴더 구조 (Gradle 표준, **test 제외**)

```
api-diary/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── springlab19/
│       │           ├── ApiDiaryApplication.java
│       │           └── practice/
│       │               └── diary/
│       │                   ├── domain/
│       │                   │   └── Diary.java
│       │                   ├── repository/
│       │                   │   └── DiaryRepository.java
│       │                   ├── service/
│       │                   │   ├── DiaryService.java
│       │                   │   └── DiaryServiceImpl.java
│       │                   ├── controller/
│       │                   │   └── DiaryController.java
│       │                   └── dto/
│       │                       ├── DiaryRequestDto.java
│       │                       └── DiaryResponseDto.java
│       └── resources/
│           └── application.yml
└── docs/
    ├── api-1-crud-responseentity.md
    ├── api-2-validation.md
    ├── api-3-global-exception-handler.md
    ├── api-4-swagger-postman.md
    └── api-5-paging-sorting.md
```

---

## 🚀 제출 방법
1. `api-diary/` 폴더에 위 구조로 프로젝트 구성  
2. 매 단계(API-1~5)마다 Postman 테스트 캡처 또는 컬렉션 파일 첨부  
3. `docs/` 폴더의 수업자료를 참고하며 구현한 코드 공유  

---

## 🔜 다음 단계
- API-1부터 시작해 매일 한 단계씩 실습  
- Habit 예시코드와 비교하며 Diary 구현을 점진적으로 보강  
- API-5 완료 시 **실무형 REST API 기본기** 완성
