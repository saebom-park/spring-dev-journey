# [REVIEW-4-1] 질문노트: 상품 검색 API

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약  
> (코드: com.review41 실습 기준)

---

### 💡 1. `<mapper>` 태그의 type 속성에는 어떤 값을 써야 해?

| 질문 | 답변 요약 |
|------|-----------|
| mapper 태그에 들어가는 type 속성은 뭔가야? | `<mapper>` 태그에는 type 속성이 없고, 실제로는 `<resultMap>` 태그에 `type` 속성이 쓰여. 여기에 들어가는 값은 매핑 대상 클래스의 전체 경로야. 예: `com.review41.dto.ProductResponseDto` |

---

### 💡 2. resultMap 태그 안에는 그 type 값에 들어가는 클래스의 모든 필드가 명시되어야 해?

| 질문 | 답변 요약 |
|------|-----------|
| resultMap에 매핑 대상 클래스의 모든 필드를 다 써야 해? | 꼭 다 쓸 필요는 없어! SQL 쿼리 결과에 포함된 컬럼만 매핑하면 되고, 나머지는 생략해도 돼. 다만 필요한 필드를 빼먹으면 값이 null로 채워지니까 그건 주의해야 해. |

---

### 💡 3. Spring Boot 3.x에서는 mybatis 설정을 어디에 둬야 해?

| 질문 | 답변 요약 |
|------|-----------|
| spring 밑에 mybatis 넣으면 안 돼? | Spring Boot 3.x부터는 `mybatis:`가 spring 하위가 아닌 **최상위 설정**이어야 작동해! 자동 구성도 그걸 기준으로 매퍼 XML을 찾거든. |

---

### 🌱 정리 키워드

- `<mapper>`의 `namespace`는 Mapper 인터페이스 전체 경로
- `<resultMap>`의 `type`은 결과 매핑 대상 클래스 (FQCN)
- `<select>`의 `parameterType`은 메서드 파라미터 타입
- `type` 속성은 `<mapper>`가 아니라 `<resultMap>`에 사용됨
- resultMap은 SQL 결과에 포함된 컬럼과 매핑할 필드만 명시하면 됨
- **Spring Boot 3.x에서는 mybatis 설정을 spring 밑이 아니라 최상위에 둬야 함**

