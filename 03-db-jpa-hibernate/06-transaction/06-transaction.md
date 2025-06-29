# [DB-6단계] 트랜잭션 처리 (transaction)

> "여러 SQL 작업을 하나의 흐름으로!"
JDBC의 트랜잭션 제어 방식을 학습하고,
commit/rollback을 이용해 데이터 일관성을 지키는 코드를 직접 작성해보자.
> 

---

## 💡 핵심 개념 요약

| 용어 | 설명 |
| --- | --- |
| 트랜잭션(Transaction) | 여러 작업을 하나의 작업처럼 처리하는 단위. 모두 성공하거나, 모두 실패해야 함 |
| commit | 트랜잭션을 성공적으로 마무리하여 DB에 반영 |
| rollback | 트랜잭션 도중 오류 발생 시 이전 상태로 복구 |
| auto-commit | 기본 설정. SQL 실행 시마다 자동으로 commit 됨. 트랜잭션 제어를 위해선 false로 설정해야 함 |

---

## 🧾 예시 코드

> 예시 파일: BookOrderTransaction.java (CASE 1: try-catch-finally)
> 

```java
import java.sql.*;

public class BookOrderTransaction {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE stocks (id INT AUTO_INCREMENT PRIMARY KEY, bookId INT, cnt INT)");
            stmt.execute("CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, memberId INT, bookId INT, status VARCHAR(100))");

            stmt.executeUpdate("INSERT INTO stocks (bookId, cnt) VALUES (101, 5)");

            conn.setAutoCommit(false); // 트랜잭션 시작

            // 1. 재고 수량 감소
            String sql1 = "UPDATE stocks SET cnt = cnt - 1 WHERE bookId = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, 101);
            pstmt1.executeUpdate();

            // 2. 주문 테이블에 내역 추가
            String sql2 = "INSERT INTO orders(memberId, bookId, status) VALUES (?, ?, ?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, 1);
            pstmt2.setInt(2, 101);
            pstmt2.setString(3, OrderStatus.PENDING.name());
            pstmt2.executeUpdate();

            conn.commit();
            System.out.println("주문 처리 완료!");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("주문 실패! 롤백 수행됨.");
            } catch (SQLException rollbackEx) {
                System.out.println("롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
```

> 예시 파일: BookOrderTransactionWithResources.java  (CASE 2: try-with-resources)
> 

```java
import java.sql.*;

public class BookOrderTransaction {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        // try-with-resources 시작
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()
        ) {
            // 테이블 생성
            stmt.execute("CREATE TABLE stocks (id INT PRIMARY KEY, bookId int, cnt INT)");
            stmt.execute("CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, memberId INT, bookId INT");

            // 테스트 데이터 삽입
            stmt.execute("INSERT INTO stocks (bookId, cnt) VALUES (101, 5)");

            conn.setAutoCommit(false); // 트랜잭션 시작

            try (
                PreparedStatement pstmt1 = conn.prepareStatement("UPDATE stocks SET cnt = cnt - 1 WHERE booId = ?");
                PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO orders(memberId, bookId, status) VALUES (?, ?, ?)")
            ) {
                // 1. 재고 감소
                pstmt1.setInt(1, 101);
                pstmt1.executeUpdate();

                // 2. 주문 내역 삽입
                pstmt2.setInt(1, 1);
                pstmt2.setInt(2, 101);
                pstmt2.setString(3, OrderStatus.Pending.name())
                pstmt2.executeUpdate();

                conn.commit();
                System.out.println("주문 처리 완료!");
            } catch (Exception e) {
                conn.rollback();
                System.out.println("주문 실패! 롤백 수행됨.");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 📌 포인트 요약

- JDBC에서 트랜잭션을 사용하려면 `setAutoCommit(false)` 호출 필수
- 모든 작업이 성공하면 `commit()`, 하나라도 실패하면 `rollback()` 처리
- try-catch-finally 블록을 통해 자원 정리와 예외 처리 병행해야 안전함
- 트랜잭션이 필요한 이유: **데이터 일관성 유지 (예: 이체 또는 주문은 여러 작업이 동시에 일관성 있게 수행돼야 함)**

---

## 🧪 실습 미션

🎯 목표: 계좌 이체 로직을 구현하며 트랜잭션 처리 흐름을 실습해보자

1. `AccountTransfer.java` 클래스 생성
2. DB 연결 후 setAutoCommit(false) 설정
3. 아래 두 작업을 하나의 트랜잭션으로 수행
    - A 계좌에서 1000원 출금 (balance 차감)
    - B 계좌에 1000원 입금 (balance 증가)
4. 중간에 예외 발생 시 전체 작업 rollback
5. 성공 시 "이체 성공!", 실패 시 "이체 실패! 롤백됨." 출력
6. 모든 JDBC 자원은 try-with-resources 또는 finally 블록으로 정리할 것

> 참고: 계좌 테이블에는 id(int), name(varchar), balance(int) 컬럼이 있다고 가정
>