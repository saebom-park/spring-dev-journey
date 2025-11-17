# [REVIEW-4-3] 질문노트: 중첩 resultMap 처리

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.review43 실습 기준)
> 

---

### 💡 1. association 태그는 언제 사용해?

| 질문 | 답변 요약 |
| --- | --- |
| 중첩 객체 매핑할 땐 어떻게 해야 해? | resultMap 안에서 객체 안의 객체를 매핑할 때 `<association>`을 사용해. 예: Product 안에 Category가 들어갈 때. |

---

### 💡 2. association 쓸 때 꼭 javaType 써야 해?

| 질문 | 답변 요약 |
| --- | --- |
| javaType은 필수야? | 대부분의 경우 명시해주는 게 안전해. 특히 FQCN(전체 경로)으로 정확히 지정해줘야 MyBatis가 타입을 인식할 수 있어. |

---

### 💡 3. association 안에 result 여러 개 써도 돼?

| 질문 | 답변 요약 |
| --- | --- |
| result 여러 개 넣어도 되지? | 물론! 일반 resultMap처럼 `<result>` 태그 여러 개 넣어서 객체 필드들을 하나하나 매핑하면 돼. |

---

### 💡 4. select id와 Mapper 메서드명이 다르면 어떻게 돼?

| 질문 | 답변 요약 |
| --- | --- |
| id 이름이 다르면 에러나? | 맞아. 메서드명과 select 태그의 id는 대소문자까지 정확히 일치해야 MyBatis가 바인딩할 수 있어. 하나라도 다르면 오류 발생해. |

---

### 💡 5. 중첩 객체의 필드명은 JSON 기준이야, DB 컬럼 기준이야?

| 질문 | 답변 요약 |
| --- | --- |
| 필드명은 어디 기준으로 해? | DTO 클래스 기준이야. 즉 JSON으로 응답될 때 어떤 구조로 내려보낼지 기준으로 이름을 정하면 돼. |

---

### 💡 6. association 안에 또 다른 association도 쓸 수 있어?

| 질문 | 답변 요약 |
| --- | --- |
| 계층적으로 계속 넣을 수 있어? | 가능은 해! 예: Product → Category → Supplier처럼 중첩 매핑 가능. 다만 alias 충돌이나 null 주의는 꼭 해야 해. |

---

### 🌱 정리 키워드

- `<association>`은 resultMap 내에서 중첩 객체를 매핑할 때 사용
- `javaType`은 FQCN으로 명확하게 지정할 것
- `<association>` 안에는 `<result>` 여러 개 포함 가능
- Mapper의 메서드명과 `<select>`의 id는 대소문자까지 정확히 일치해야 함
- DTO 기준으로 필드명을 잡으면 JSON 응답 구조가 깔끔해짐
- 중첩된 association도 가능하나, 컬럼 alias 충돌은 조심해야 함