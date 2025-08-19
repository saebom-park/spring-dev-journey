# [DBA-4-1단계] 질문노트: 단방향 연관관계 매핑

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: springlab16 실습 기준)
> 

---

### 💡 1. `logging.level.org.hibernate.SQL: debug`는 무슨 설정이야?

| 질문 | 답변 요약 |
| --- | --- |
| DBA-3에는 없었는데 DBA-4부터 생긴 이유는 뭘까? | 이 설정은 Hibernate가 실제 실행하는 SQL문을 콘솔 로그로 보여주는 설정이야. DBA-4는 연관관계 매핑이 포함되면서 자동 생성되는 JOIN 쿼리나 외래키 관련 쿼리를 확인할 필요가 있기 때문에 추가된 거야. 단순 CRUD만 하던 DBA-3에서는 생략해도 됐지만, 관계 매핑이 시작되는 시점부터는 디버깅용으로 필수야. |

---

### 💡 2. `@JoinColumn(name = "member_id")`인데 왜 Member 클래스엔 그냥 `id`라고 써?

| 질문 | 답변 요약 |
| --- | --- |
| 외래키 컬럼 이름이랑 참조 필드 이름이 다른 이유가 뭐야? | `@JoinColumn(name = "member_id")`는 DB에 실제 생성될 외래키 컬럼명을 지정하는 것이고, `Member` 클래스의 `id`는 그 엔티티의 기본키 필드 이름이야. 두 이름은 같을 필요 없고, 오히려 실무에서는 `member_id`, `user_id`처럼 구체적인 이름으로 외래키 컬럼명을 명시하는 게 유지보수에 더 좋아. |

---

### 💡 3. `@JoinColumn(name = ...)`은 무조건 PK를 참조해?

| 질문 | 답변 요약 |
| --- | --- |
| name 속성에 지정한 컬럼은 항상 상대 엔티티의 PK를 참조하는 거야? | 기본적으로는 맞아. 아무 설정도 없으면 JPA는 `@JoinColumn`이 참조하는 대상 엔티티의 PK(@Id)를 자동으로 연결해. 하지만 `referencedColumnName` 속성을 사용하면 PK가 아닌 다른 컬럼도 참조할 수 있어. 예를 들어 `referencedColumnName = "username"`처럼 쓰면 `username` 컬럼을 외래키로 지정할 수 있어. |

---

### 💡 4. `fetch = FetchType.LAZY`는 무슨 의미야?

| 질문 | 답변 요약 |
| --- | --- |
| LAZY가 직관적으로 뭔지 잘 모르겠어… | LAZY는 “진짜 필요한 순간까지는 DB에서 안 가져올게”라는 뜻이야. 예를 들어 Order만 먼저 조회하고, 그 안의 member를 꺼낼 때 그제서야 member 정보를 SELECT 하는 식으로 동작해. 즉시 로딩(EAGER)은 무조건 다 끌고 오지만, LAZY는 최소한만 가져오는 전략이야. |

---

### 💡 5. `@ManyToOne`은 직관적으로 어떻게 받아들여야 해?

| 질문 | 답변 요약 |
| --- | --- |
| 개념은 외웠는데 감이 잘 안 와 | 내가 지금 만들고 있는 클래스(Order)가 Many니까, “나는 여러 주문 중 하나야 → 하나의 Member를 바라보는 관계”라고 받아들이면 돼. 그래서 ManyToOne이라고 쓰는 거야. |

---

### 💡 6. Order가 Many니까 ManyToOne이라고 외워도 돼?

| 질문 | 답변 요약 |
| --- | --- |
| 그냥 내가 속한 클래스가 Many니까 ManyToOne이라고 외워도 되는 거야? | 완벽하게 맞아! 이 어노테이션은 “내가 속한 클래스가 Many일 때 → ManyToOne”이라고 외우는 게 가장 직관적이고 실무에서도 그렇게 이해하면 헷갈릴 일이 없어. |

---

### 🌱 정리 키워드

- logging.level 설정
- Hibernate SQL 출력
- @JoinColumn vs 엔티티 필드 이름
- 외래키 컬럼 네이밍 관례
- referencedColumnName 사용
- fetch = LAZY 의미
- EAGER vs LAZY
- ManyToOne 직관적 해석 방법