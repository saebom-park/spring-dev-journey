# [REVIEW-3-3] 실수노트

> 💻 실습 코드: MemberServiceImpl.java, MemberController.java

---

### 😅 실수 1 — `findMember()` 요청에서 RequestDto를 사용함

```java
@GetMapping("/members/{id}")
public MemberResponseDto findMember(@RequestBody MemberRequestDto requestDto) { ... }
```

✅ 정답:

```java
@GetMapping("/members/{id}")
public MemberResponseDto findMember(@PathVariable Long id) { ... }
```

📌 **설명**:

- GET 요청에서는 body를 사용하지 않고 경로(@PathVariable)나 쿼리(@RequestParam)로 데이터를 전달함
- 단일 id 조회에는 Dto 포장이 불필요함

---

### 😅 실수 2 — MemberService에 register만 있는 줄 알고 그대로 쓴 실수

```java
public interface MemberService {
    MemberResponseDto register(MemberRequestDto requestDto);
}
```

📌 **설명**:

- 실습 목적이 전체 조회 / 단건 조회인데 기존 register() 메서드를 복사해 넣는 실수가 있었음
- 요구사항과 설계 의도를 먼저 파악하고 필요한 메서드 시그니처를 새로 정의해야 함

---

### 😅 실수 3 — stream 문법이 익숙하지 않아 for문을 사용함

```java
List<Member> members = repository.findAll();
List<MemberResponseDto> dtos = new ArrayList<>();
for (Member m : members) {
    dtos.add(new MemberResponseDto(...));
}
```

📌 **설명**:

- 틀린 건 아니지만, 실무에선 `stream().map().collect()` 방식이 더 간결하고 자주 쓰임
- 추후 익숙해지면 stream 방식으로 리팩토링 가능

---

### 📌 요약 포인트

- GET 요청에서는 RequestBody 사용 ❌ → @PathVariable / @RequestParam 사용 ✅
- register() 메서드만 반복하지 말고 요구 흐름에 따라 새로운 시그니처 설계
- stream은 아직 생소하더라도 익숙해지면 더 깔끔한 구현 가능!

