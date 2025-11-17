# [REVIEW-2-3단계] 실수노트

> 💻 실습 코드: OrderDaoRefactor.java
> 

---

### 😅 실수 1 — SQL 예약어 오타: `VALUE` → `VALUES`

```
String sql = "INSERT INTO orders (productName, quantity, price) VALUE (?, ?, ?)"; // ❌ 오타
```

✅ 정답:

```
String sql = "INSERT INTO orders (productName, quantity, price) VALUES (?, ?, ?)"; // ✅ 정확한 예약어 사용
```

📌 설명:

- SQL 예약어는 하나만 틀려도 전체 문법 오류 발생
- VALUE → VALUES 꼭 확인할 것

---

### 😅 실수 2 — 트랜잭션 시작 전에 유효성 검사 누락

```
conn.setAutoCommit(false); // ❌ orders 비어있는데도 트랜잭션 시작
```

✅ 정답:

```
if (orders == null || orders.isEmpty()) return 0; // ✅ 먼저 검사
conn.setAutoCommit(false);
```

📌 설명:

- 의미 없는 트랜잭션은 DB에 불필요한 부하 초래
- 데이터 유효성은 **가장 먼저 검사**해야 함

---

### 😅 실수 3 — rollback 이후 `throw` 누락

```
conn.rollback();
e.printStackTrace(); // ❌ 예외를 호출부에 전달하지 않음
```

✅ 정답:

```
conn.rollback();
throw e; // ✅ 호출부에 예외 전달
```

📌 설명:

- catch 블록에서 예외를 무조건 삼키지 말 것
- `throw e` 또는 `throw rollbackEx`로 상위에 알릴 것

---

### 😅 실수 4 — 불필요한 객체 생성

```
Order order = new Order(); // ❌ rs.next() 확인 전 객체 생성
```

✅ 정답:

```
if (rs.next()) {
    Order order = new Order();
```

📌 설명:

- 쓸모없는 객체를 만들지 않도록 **조건 후 생성** 습관화

---

### 😅 실수 5 — `findById()`에서 Optional 미사용

```
return null; // ❌ 호출부에서 null 체크 필요
```

✅ 정답:

```
return Optional.empty(); // ✅ 명시적 표현
```

📌 설명:

- Optional은 null을 대체하는 Java의 안전한 래퍼
- `ifPresentOrElse()` 등으로 깔끔하게 분기 가능

---

### 😅 실수 6 — switch로 컬럼명 직접 조합

```
switch (type) { ... } // ❌ 문자열 조합 기반 보안 위험
```

✅ 정답:

```
Map<Integer, String> ORDER_COLUMNS = ... // ✅ 화이트리스트 방식
```

📌 설명:

- 외부 입력으로부터 컬럼명을 직접 구성하면 **SQL Injection 위험** 존재
- 안전한 컬럼 매핑은 Map 또는 enum 등 **화이트리스트 방식** 사용

---

### 😅 실수 7 — 이미 int 타입에 불필요한 `NumberFormatException` 처리

```
pstmt.setInt(1, id); // int인데 try-catch 감쌈 ❌
```

📌 설명:

- 이미 int로 선언된 파라미터는 변환이 필요 없음
- `try { setInt() } catch(NumberFormatException)` 구문은 의미 없음

---

### 📌 요약 포인트

- SQL 예약어는 정확히! `VALUES`, 괄호, 세미콜론 등 주의
- 트랜잭션은 유효성 검사 후에 시작
- rollback 후 `throw` 누락하지 않기
- Optional 적극 사용해 null 리스크 줄이기
- 컬럼명 조합은 Map 등으로 안전하게 구성
- 의미 없는 객체 생성, 예외 처리 줄이기