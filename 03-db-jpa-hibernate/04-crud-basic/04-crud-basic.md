# [DB-4단계] CRUD 처리 (crud-basic)

> ✨ “JDBC로 직접 DB를 조작해보자!”
> 
> 
> Create → Read → Update → Delete 흐름을 전부 코드로 구현하며,
> 
> 자바에서 SQL을 제어하는 전반적인 흐름을 익혀보자.
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| CRUD | 데이터 처리의 기본: 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) |
| execute / executeUpdate | `INSERT`, `UPDATE`, `DELETE`는 `executeUpdate()` 사용 권장 |
| executeQuery | `SELECT` 계열은 `executeQuery()` 사용 (결과가 ResultSet) |
| 영향 줄 개수 반환 | `executeUpdate()`는 반영된 row 수(int) 반환 |
| 트랜잭션 | JDBC는 기본적으로 자동 커밋됨 → 실무에선 수동 커밋 권장 (`setAutoCommit(false)`) |

---

### 🧾 예시 코드

> 📄 예시 파일: BookCrudMain.java
> 

```java
try (
    Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    Statement stmt = conn.createStatement()
) {
		// CREATE
    stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100))");

    int insertCount = stmt.executeUpdate("INSERT INTO books VALUES (1, '자바의 정석', '남궁성')");
    System.out.println("삽입된 행 수: " + insertCount);

    // READ
    ResultSet rs = stmt.executeQuery("SELECT * FROM books");
    while (rs.next()) {
        System.out.println(rs.getInt("id") + ", " + rs.getString("title") + ", " + rs.getString("author"));
    }

    // UPDATE
    int updateCount = stmt.executeUpdate("UPDATE books SET author = '홍길동' WHERE id = 1");
    System.out.println("수정된 행 수: " + updateCount);

    // DELETE
    int deleteCount = stmt.executeUpdate("DELETE FROM books WHERE id = 1");
    System.out.println("삭제된 행 수: " + deleteCount);
} catch (SQLException e) {
    e.printStackTrace();
}
```

---

### 📌 포인트 요약

- `executeUpdate()`는 DML 문에 적합하며, **적용된 행 수를 반환**함
- `executeQuery()`는 결과 집합을 다룰 수 있는 **ResultSet 반환**
- 트랜잭션 처리는 나중에 수동 커밋 방식에서 다룰 예정
- CRUD 흐름은 항상: **삽입 → 조회 → 수정 → 삭제 → 다시 조회**

---

### 🧪 실습 미션

> 🎯 목표: orders 테이블을 생성하고, CRUD 전체 흐름 구현
> 
1. `orders` 테이블: `id`, `member_id`, `book_id`, `status` 컬럼으로 생성
2. 데이터 2건 삽입: 서로 다른 주문자(member_id)와 도서(book_id)
3. 특정 주문의 상태를 `'CANCELLED'`로 변경
4. 하나의 주문을 삭제
5. 최종적으로 전체 조회하여 변경 사항 반영 여부 확인
6. `System.out.println()`으로 각 단계 결과 출력 필수

> 📁 파일명 예시: OrderCrudMain.java
>