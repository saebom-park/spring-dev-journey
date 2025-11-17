# 📦 REVIEW-3-1 전체 개념 A~Z 정리: 회원 등록 API 흐름

> Spring Boot 실습 중 처음으로 Controller → Service → Repository 구조를 손코딩하며  
> 계층 분리, DTO 흐름, Bean 주입, 리플렉션, Map 저장소 등 실무 구조의 핵심을 학습했습니다.

---

## 💡 1. 전체 구성 요약

| 계층 | 클래스 | 책임 |
|------|--------|------|
| Controller | MemberController | 클라이언트 요청 수신 & 응답 반환 |
| Service | MemberService | 비즈니스 로직 실행, 흐름 제어 |
| Repository | MemberRepository | 데이터를 저장/조회하는 역할 (Map 사용) |
| Entity | Member | 회원 데이터를 담는 순수 자바 객체 (POJO) |
| DTO | MemberRequestDto / MemberResponseDto | 요청/응답 데이터 전달용 객체 |

---

## ✨ 2. 주요 개념 A~Z

### A. @RestController
- HTTP 요청/응답을 JSON으로 자동 처리
- @ResponseBody가 자동으로 적용됨

### B. @RequestBody
- 클라이언트의 JSON 요청을 자바 객체(DTO)로 변환해줌 (리플렉션 기반)

### C. @PostMapping("/members")
- HTTP POST 요청을 처리하는 메서드에 붙임
- URL 매핑을 의미

### D. DTO 분리 (Request / Response)
- 요청(Request): MemberRequestDto → name, email, age
- 응답(Response): MemberResponseDto → id, name
- 목적에 따라 필드를 분리하여 보안/확장성 확보

### E. Entity (Member)
- 데이터를 담는 틀
- DB 저장용이 아님 → 지금은 POJO로 동작
- getter/setter 포함

### F. 생성자 주입
```java
private final MemberService memberService;
public MemberController(MemberService memberService) { ... }
```
- Spring이 생성자에 필요한 의존 객체를 주입해줌
- final + 생성자 = 불변성 + 명시성

### G. @Service / @Repository
- Spring이 해당 클래스를 Bean으로 등록하게 해주는 어노테이션
- 자동으로 DI 가능해짐

### H. 리플렉션 (Reflection)
- 실행 중 객체의 구조(필드, 메서드 등)를 조사하고 생성하는 기능
- 기본 생성자가 필요한 이유

### I. Optional.ofNullable()
```java
Optional.ofNullable(store.get(id));
```
- null이 올 수 있는 데이터를 안전하게 감싸는 Optional 팩토리 메서드
- ifPresentOrElse() 등으로 처리 가능

### J. new ArrayList<>(store.values())
- Map의 값들만 모아서 새로운 리스트로 생성
- store.values()는 Collection이므로 리스트로 변환

### K. 계층 분리 이유
- 역할 책임 분리 (Controller는 요청만, 비즈니스는 Service에서)
- 유지보수성과 확장성 ↑
- 테스트 가능성 ↑

---

## 🧪 3. 전체 실행 흐름 요약

```
1. POST /members 요청 (JSON)
2. Controller가 @RequestBody로 DTO 받음
3. Service에게 DTO 전달
4. Service가 Member 객체 생성 후 Repository에 저장 요청
5. Repository가 ID 부여 → Map에 저장
6. 저장된 객체로 응답 DTO 생성
7. Controller가 응답 반환 (JSON)
```

---

## 🎯 4. 테스트 예시 (Postman)

### 요청
```
POST http://localhost:8080/members
Content-Type: application/json
Body:
{
  "name": "봄이",
  "email": "spring@dev.com",
  "age": 30
}
```

### 응답
```json
{
  "id": 1,
  "name": "봄이"
}
```

---

## 🌱 5. 마무리 요약

- 지금까지의 자바 실습과 달리 “기능 + 설계”를 동시에 경험한 구조
- 처음 Service/Repository 분리는 낯설지만, 이게 스프링의 핵심 철학
- 우리가 만든 구조는 이후 DB 연동(JPA), 유효성 검증, 예외처리, 인증 등 모든 실습의 기본 뼈대가 될 것

