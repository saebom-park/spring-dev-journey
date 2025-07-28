# REVIEW-3-1: 회원 등록 API 구현 (spring-api-basic)

> ✨ Spring Boot의 `@RestController`와 `@RequestBody`를 활용해  
>  
> **회원 등록 API**를 구현하고, 계층 분리 + DTO 매핑을 손코딩으로 연습합니다.

---

## 💡 실습 시나리오

📦 **회원 등록 API 만들기**

- `/members` POST 요청을 받아 새로운 회원(Member)을 등록하는 API 구성
- Controller → Service → Repository 계층을 나누고, DI로 연결
- HTTP 요청/응답 데이터는 DTO 객체를 통해 전달
- 클라이언트는 JSON 요청을 보내고, 서버는 JSON 응답 반환

---

## 📋 구현 요구사항

### 1. Member 클래스

- 필드: `id`, `name`, `email`, `age`
- getter/setter 포함한 POJO 형태

### 2. MemberRequestDto / MemberResponseDto

- `MemberRequestDto`: 클라이언트 요청 데이터 전달용
- `MemberResponseDto`: 등록 결과를 반환하는 응답용 DTO

### 3. MemberController

- POST `/members` 요청 처리
- `@RequestBody`로 JSON 요청 바인딩
- 등록 후 `MemberResponseDto`를 JSON으로 반환

### 4. MemberService

- `register()` 메서드 정의
- DTO → Entity 변환 후 저장 로직 수행

### 5. MemberRepository

- 단순한 메모리 저장소 사용 (Map 기반)
- 저장 시 ID는 내부에서 자동 증가 처리

---

## 🎯 구현 목표 체크리스트

| 항목 | 포함 여부 |
| --- | --- |
| Controller → Service → Repository 구조로 분리 | ✅ |
| DTO를 통한 요청/응답 처리 | ✅ |
| @RestController 사용 | ✅ |
| @RequestBody → 객체 매핑 동작 확인 | ✅ |
| 저장 결과를 JSON으로 반환 | ✅ |

---

## 📂 폴더 구조 예시

```
review-3-1-spring-api-basic/
├── README.md
└── src/main/java
    └── com.review31/
        ├── Member.java
        ├── MemberRequestDto.java
        ├── MemberResponseDto.java
        ├── MemberController.java
        ├── MemberService.java
        └── MemberRepository.java
```
