# [REVIEW-3-3] 전체 회원 조회 + 단건 조회 (member-find-api)

> 🧪 기존 회원 등록 흐름을 기반으로 전체/단건 조회 기능을 추가하며 Controller → Service → Repository 계층 설계와 DTO 응답 구조를 학습합니다.
>
> 이번 실습에서는 stream을 활용한 응답 리스트 변환도 함께 경험합니다.

---

## 💡 시나리오

사용자 정보를 저장한 뒤,

- 전체 회원 목록을 조회하고
- 특정 회원의 상세 정보를 조회할 수 있는 기능을 추가하고자 한다.

기존 등록 API를 유지한 채, 조회용 API만 확장한다.

---

## 📋 요구사항

### 기능 요구

- 회원을 등록할 수 있다 (이름, 이메일, 나이)
- 전체 회원 목록을 조회할 수 있다
- 특정 회원의 ID로 상세 정보를 조회할 수 있다

### API 스펙

- POST   `/members/register`  → 회원 등록
- GET    `/members/find`      → 전체 조회
- GET    `/members/{id}`      → 단건 조회

---

## 🎯 체크리스트

- ✅ DTO를 통한 요청/응답 구조를 명확히 분리했는가?
- ✅ Controller → Service → Repository 계층 흐름을 유지했는가?
- ✅ 단건 조회는 @PathVariable, 전체 조회는 List 반환을 잘 처리했는가?
- ✅ Member → MemberResponseDto 변환을 정확히 구현했는가?
- ✅ stream 문법의 도입 흐름을 이해하고 리팩토링에 적용할 수 있는가?

---

## 📂 폴더 구조

```
review-3-3/
├── controller/
│   └── MemberController.java
├── service/
│   ├── MemberService.java
│   └── MemberServiceImpl.java
├── repository/
│   ├── MemberRepository.java
│   └── MemoryMemberRepository.java
├── dto/
│   ├── MemberRequestDto.java
│   └── MemberResponseDto.java
├── domain/
│   └── Member.java
├── MemberApiApplication.java
```

---

> 이번 실습을 통해 REST API의 기본 조회 흐름과 DTO/Entity 분리 감각을 더욱 단단히 만들었습니다. 다음 실습에선 구조 리팩토링 또는 예외 처리 확장을 연습할 예정입니다. 🌱

