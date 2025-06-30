# [DB-6단계] 질문노트: 트랜잭션 처리 (BookOrderTransaction.java)

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: BookOrderTransaction.java 실습 기준)
> 

---

### 💡 1. 왜 예시 코드에서는 `url`이나 `user`, `password`가 없었어?

| 질문 | 답변 요약 |
| --- | --- |
| 지금까지는 `url`을 `jdbc:h2:mem:testdb`로 썼는데, 예시코드는 왜 `mysql`로 되어 있어? | 예시코드는 실무 기준의 흐름을 보여주기 위해 MySQL 스타일로 작성한 것뿐이고, 실습에서는 H2를 그대로 사용해도 돼. |

---

### 💡 2. MySQL을 사용하려면 뭔가 설정을 해야 하지 않아?

| 질문 | 답변 요약 |
| --- | --- |
| 예시 코드처럼 MySQL 쓰려면 그냥 실행하면 돼? | 안 돼! MySQL은 설치되어 있어야 하고, DB/테이블도 생성되어 있어야 해. 드라이버도 추가 필요. |

---

### 💡 3. 그럼 테이블은 언제, 어디서 생성해?

| 질문 | 답변 요약 |
| --- | --- |
| 예시 코드 실행 전에 테이블이 없는데, 그럼 따로 SQL 쳐야 해? | 아니, 자바 코드 안에서 `CREATE TABLE`, `INSERT` 구문을 실행해서 인메모리 DB에 일시적으로 만들 수 있어. `conn.createStatement()`로 처리 가능! |

---

### 💡 4. 왜 `try-with-resources` 안 쓰고 `try-catch-finally`를 썼어?

| 질문 | 답변 요약 |
| --- | --- |
| 지금까지 자원 정리는 `try-with-resources`로 배웠는데, 예시코드는 왜 아니야? | 트랜잭션 실습의 구조를 명확히 보여주기 위해 일부러 자원 관리를 명시적으로 표현한 거야. 하지만 실무에선 `try-with-resources`가 더 안전하고 추천돼! |

---

### 💡 5. AUTO_INCREMENT인데 id에 값을 넣어도 왜 오류가 안 나는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| id 컬럼이 AUTO_INCREMENT인데 직접 값을 지정해도 에러가 안 나! 왜지? | MySQL이나 H2에서는 명시적으로 값을 지정한 경우에는 그 값을 우선 사용하고, 자동 증가 기능은 생략된 경우에만 동작해. 중복 값만 아니라면 허용돼. |

---

### 💡 6. 그럼 PreparedStatement 객체에 리턴된 id 값이 자동으로 저장돼?

| 질문 | 답변 요약 |
| --- | --- |
| Statement.RETURN_GENERATED_KEYS 옵션을 쓰면 pstmt 안에 id 값이 저장돼? | 아니, pstmt 자체에 저장되는 건 아니고, `getGeneratedKeys()` 메서드를 통해 별도로 꺼내야 해. ResultSet으로 반환돼. |

> 예시:
> 
> 
> ```java
> PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
> pstmt.executeUpdate();
> ResultSet rs = pstmt.getGeneratedKeys();
> if (rs.next()) {
>     int generatedId = rs.getInt(1);
>     System.out.println("생성된 ID: " + generatedId);
> }
> ```
> 

---

### 🌱 정리 키워드

- `jdbc:h2:mem:testdb` vs `jdbc:mysql://...`
- H2 인메모리 → 테이블은 코드 안에서 생성 가능
- 실무 기준은 `try-with-resources`로 자원 관리
- 예시 코드는 흐름 중심, 실습은 환경 맞춰 조정
- `AUTO_INCREMENT` 컬럼은 명시적 값 지정도 허용됨 (단, 중복은 불가)
- 자동 생성된 id는 `getGeneratedKeys()`로 꺼내야 함
- pstmt 객체에 자동 저장되는 건 아님