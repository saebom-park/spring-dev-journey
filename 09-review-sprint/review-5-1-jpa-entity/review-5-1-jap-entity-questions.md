# [REVIEW-5-1] 질문노트: JPA 엔티티 설계

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.review51 실습 기준)
> 

---

### 💡 1. 클래스에서 @RequestMapping으로 경로를 지정했는데, 메서드에 또 PostMapping이나 GetMapping을 써야 해?

| 질문 | 답변 요약 |
| --- | --- |
| 클래스에 이미 @RequestMapping("/members")라고 적었으면, 메서드에도 또 @PostMapping이나 @GetMapping에 경로를 써야 해? | 꼭 그럴 필요는 없어. 클래스에서 기본 경로(`/members`)를 설정했기 때문에, 메서드에는 그냥 `@PostMapping`, `@GetMapping`만 써도 각각 `/members` 경로에서 POST, GET 요청을 처리할 수 있어. 경로를 중복해서 쓰는 건 명확한 구분이 필요할 때만 해도 돼. |

---

### 🌱 정리 키워드

- `@RequestMapping`으로 클래스 레벨에서 경로를 지정하면, 메서드에서는 생략 가능
- 메서드에 `@PostMapping`, `@GetMapping`만 쓰면 기본 경로에서 HTTP 메서드만 구분해서 처리함
- 실무에서는 REST 스타일을 따르기 위해 메서드 경로 생략을 자주 활용함