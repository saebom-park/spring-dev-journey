# [API-1단계] 질문노트: CRUD API 고도화

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab20 실습 기준)
> 

---

### 💡 1. ORM이 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| ORM이 뭐야? | **Object-Relational Mapping**: 객체(Entity)와 관계형 DB 테이블을 자동 매핑해주는 기술. |
| JPA와 Hibernate는 어떤 관계야? | JPA는 **표준 ORM 명세**, Hibernate는 **구현체**. 스프링은 JPA 인터페이스를 통해 Hibernate를 사용. |
| org.hibernate.type vs org.hibernate.orm.jdbc.bind 차이는? | Hibernate 6부터는 `org.hibernate.orm.jdbc.bind`가 권장. SQL 파라미터 바인딩 과정을 로깅하는 카테고리. |

---

### 💡 2. Dirty Checking이 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| Dirty Checking이 뭐야? | **변경 감지**: JPA가 영속 상태 엔티티의 스냅샷과 현재 값을 비교해, 달라지면 자동으로 UPDATE SQL 실행. |
| 왜 save() 없이도 update가 돼? | 이미 조회된 엔티티는 영속 상태라 JPA가 추적 중. set으로 필드만 바꿔도 트랜잭션 커밋 시점에 UPDATE가 반영됨. |
| save()는 언제 필요해? | 새로운 엔티티 등록(persist)할 때 필요. 수정은 Dirty Checking으로 자동 반영. |

---

### 🌱 정리 키워드

- ORM = 객체 ↔ DB 테이블 매핑
- JPA = 표준, Hibernate = 구현체
- Hibernate 6+에서는 `orm.jdbc.bind` 로깅 사용
- Dirty Checking = JPA가 변경 감지 후 자동 update SQL 실행
- save()는 등록용, 수정은 자동 반영