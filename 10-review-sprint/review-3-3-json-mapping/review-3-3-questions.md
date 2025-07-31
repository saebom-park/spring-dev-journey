# [REVIEW-3-3] 질문노트: 전체 조회 흐름 + stream 변환

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
>
> (코드: review-3-3 실습 기준)

---

### 💡 1. `List<MemberResponseDto>`를 반환해도 괜찮은가?

| 질문 | 답변 요약 |
| --- | --- |
| 전체 회원 목록을 응답할 때 List<MemberResponseDto> 형태로 반환해도 괜찮아? | ✅ 응답 객체인 DTO로 감싸서 리스트로 반환하는 건 실무에서 가장 일반적인 구조야. 보안성과 응답 최적화 측면에서도 좋은 선택이야! |

---

### 💡 2. DTO를 왜 Entity 대신 써야 해?

| 질문 | 답변 요약 |
| --- | --- |
| 그냥 Member를 바로 반환하면 안 돼? | ❌ Entity에는 민감한 정보가 포함될 수 있고, 외부에 설계를 노출하게 돼. DTO는 필요한 필드만 담기 때문에 보안과 유지보수에 좋아! |

---

### 💡 3. stream으로 DTO 리스트 만들기 예시 설명해줘!

```java
return memberRepository.findAll().stream()
    .map(member -> new MemberResponseDto(
        member.getId(), member.getName(), member.getEmail(), member.getAge()
    ))
    .collect(Collectors.toList());
```

| 단계 | 설명 |
|------|------|
| `stream()` | 리스트를 하나씩 처리하는 흐름으로 변환함 |
| `map(...)` | Member → MemberResponseDto 로 변환하는 단계 |
| `collect(Collectors.toList())` | 다시 리스트로 모아 반환함 |

> 💬 즉, for문 없이 리스트를 "다른 타입의 리스트"로 바꾸는 가장 강력한 문법!

---

### 💡 4. @RequestMapping 써도 되는 거야? 실습에선 안 썼는데...

| 질문 | 답변 요약 |
| --- | --- |
| 실습에서는 @RequestMapping 안 쓰고 바로 @GetMapping 썼는데, 다른 예시에서는 왜 써? | @RequestMapping은 "공통 prefix"를 클래스에 적용할 때 유용해. 실습에서는 간단한 구조라 생략한 거고, 실무에선 @RequestMapping("/members") + @GetMapping("/search") 식으로 많이 써! |

---

### 💡 5. MemberService에서 requestDto는 왜 안 쓰는거야?

| 질문 | 답변 요약 |
| --- | --- |
| findMember() 메서드에서 id만 필요했는데 왜 requestDto로 받았던 거야? | ❌ 이 경우엔 단일 값(Long id)이므로 requestDto 없이 `@PathVariable Long id`로 받는 게 훨씬 명확해! 필요할 때만 DTO로 감싸는 게 좋아. |

---

### 💡 6. getMembers() 메서드 이름은 이렇게 써도 돼?

| 질문 | 답변 요약 |
| --- | --- |
| 전체 조회할 때 findAllMembers()로 할까 고민됐어 | 실무에선 getMembers() 또는 findMembers()처럼 간결하고 의미 명확한 이름을 선호해! findAllMembers()는 너무 장황하고 흔치 않아. |

---

### 🌱 정리 키워드

- List<MemberResponseDto> → 실무 표준 응답 구조
- Entity 직접 반환 ❌ → 민감 정보 보호, 유지보수성 확보
- stream().map().collect() → DTO 변환 공식
- @RequestMapping → 클래스 공통 prefix로 자주 사용됨
- @PathVariable vs DTO → 값 하나면 기본형 사용이 명확함
- 메서드 네이밍 → getMembers(), getMember() 추천

