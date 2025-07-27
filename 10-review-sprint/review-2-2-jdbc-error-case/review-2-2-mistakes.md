# [REVIEW-2-2단계] 실수노트

> 💻 실습 코드: BookDaoBasic.java
> 

---

### 😅 실수 1 — `executeQuery()`를 `DELETE`, `INSERT`, `UPDATE`에 사용

```java
pstmt.executeQuery(); // ❌ DML에서 사용됨
```

✅ 정답:

```java
pstmt.executeUpdate(); // ✅ DML 전용 메서드
```

📌 설명:

- `executeQuery()`는 SELECT 전용으로 사용해야 하며,
- DML 명령어(`INSERT`, `UPDATE`, `DELETE`)는 반드시 `executeUpdate()`로 실행해야 함

---

### 😅 실수 2 — `switch-case`에 `break` 문 누락

```java
switch (type) {
    case 1: typeText = "title";
    case 2: typeText = "author";
    case 3: typeText = "price";
}
```

✅ 정답:

```java
switch (type) {
    case 1: typeText = "title"; break;
    case 2: typeText = "author"; break;
    case 3: typeText = "price"; break;
    default: throw new SQLException("유효하지 않은 type 입니다.");
}
```

📌 설명:

- `break`가 없으면 모든 case가 연속 실행되는 **fall-through 오류** 발생
- 꼭 `break;`로 흐름을 차단해줘야 함

---

### 😅 실수 3 — SQL 문장에 괄호 닫힘 누락

```java
String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?"; // ❌
```

✅ 정답:

```java
String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)"; // ✅
```

📌 설명:

- 괄호 `)` 또는 따옴표가 닫히지 않으면 `SQLSyntaxErrorException` 발생
- SQL 문자열은 항상 시각적으로 확인할 것

---

### 😅 실수 4 — `PreparedStatement` 닫는 부분에서 `conn.close()` 호출

```java
if (pstmt != null) conn.close(); // ❌ pstmt 조건인데 conn 닫음
```

✅ 정답:

```java
if (pstmt != null) pstmt.close();
if (conn != null) conn.close();
```

📌 설명:

- 오타로 자원 정리 흐름이 잘못 적용됨
- `pstmt`와 `conn`은 각각 분리해서 닫아야 함

---

### 😅 실수 5 — `ResultSet`을 초기화하지 않고 `.next()` 호출 (NPE 발생)

```java
pstmt = conn.prepareStatement(sql);
// rs = pstmt.executeQuery(); 누락됨
while (rs.next()) { // ❌ rs가 null
```

✅ 정답:

```java
rs = pstmt.executeQuery();
while (rs.next()) { ... }
```

📌 설명:

- `ResultSet`은 `executeQuery()`로 반드시 초기화해야 함
- 초기화 없이 접근하면 `NullPointerException` 발생

---

### 📌 요약 포인트

- `executeUpdate()`는 DML, `executeQuery()`는 SELECT 전용!
- `switch-case`에는 `break`를 반드시 넣자
- SQL 문장은 괄호, 따옴표 빠짐 없이 닫기
- 자원 정리 시 `pstmt`, `conn` 분리해서 정확히 닫기
- `ResultSet`은 항상 `rs = pstmt.executeQuery();`로 초기화 후 사용