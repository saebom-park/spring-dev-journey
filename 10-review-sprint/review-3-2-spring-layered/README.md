# REVIEW-3-2: 인터페이스 분리 리팩토링 (spring-layered)

> ✨ 기존 회원 등록 API에 **인터페이스 기반 계층 분리**를 적용하여  
>  
> `Service`와 `Repository` 역할을 **interface + 구현체**로 나누고,  
> DI 구조를 명확하게 설계합니다.

---

## 💡 실습 시나리오

📦 **계층별 역할 분리와 인터페이스 설계**

- 기존 Controller → Service → Repository 구조에서  
  Service/Repository 계층을 인터페이스로 추상화
- DI는 인터페이스 기반으로 구성, 구현체는 @Service/@Repository로 연결
- 스프링이 구현체를 자동 주입해주는 구조 실습
- 전체 API 흐름은 유지하되, 내부 구조를 더 유연하게 개선

---

## 📋 구현 요구사항

### 1. Member 클래스

- 필드: `id`, `name`, `email`, `age`
- getter/setter 포함한 POJO 형태

### 2. MemberRequestDto / MemberResponseDto

- `MemberRequestDto`: 클라이언트 요청 데이터 전달용
- `MemberResponseDto`: 등록 결과를 반환하는 응답용 DTO

### 3. MemberRepository 인터페이스

- `save(Member member)` 메서드 정의
- 메모리 저장소에 대한 역할만 정의 (구현은 X)

### 4. MemoryMemberRepository 구현체

- `MemberRepository`를 구현
- 내부 Map<Long, Member>로 저장
- 저장 시 ID 자동 증가

### 5. MemberService 인터페이스

- `register(MemberRequestDto)` 메서드 정의
- 컨트롤러의 요청을 받아 처리하는 추상 서비스 계층

### 6. MemberServiceImpl 구현체

- `MemberService`를 구현
- DTO → Entity 변환 및 저장 후 응답 DTO 반환

### 7. MemberController

- POST `/members` 요청 처리
- 인터페이스(MemberService) 타입으로 DI 받기
- JSON 요청 바인딩 → 등록 → JSON 응답 반환

---

## 🎯 구현 목표 체크리스트

| 항목 | 포함 여부 |
| --- | --- |
| Service/Repository 인터페이스와 구현체를 나눔 | ✅ |
| Controller가 인터페이스 타입으로 의존 | ✅ |
| 구현체에만 @Service / @Repository 사용 | ✅ |
| 전체 구조 정상 동작 및 테스트 완료 | ✅ |

---

## 📂 폴더 구조 예시

```
review-3-2-spring-layered/
├── README.md
└── src/main/java
    └── com.review32/
        ├── Member.java
        ├── MemberRequestDto.java
        ├── MemberResponseDto.java
        ├── controller/
        │   └── MemberController.java
        ├── repository/
        │   ├── MemberRepository.java
        │   └── MemoryMemberRepository.java
        └── service/
            ├── MemberService.java
            └── MemberServiceImpl.java
```
