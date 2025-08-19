# [DB-3단계] JDBC 연결 (jdbc-basic)

> ✨ "자바 애플리케이션이 DB랑 연결되려면 뭘 써야 할까?"
> 
> 
> 👉 JDBC는 자바가 데이터베이스와 직접 연결하기 위해 사용하는 기본 API야!
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| JDBC | Java Database Connectivity. 자바에서 DB와 통신하는 API |
| DriverManager | DB 드라이버를 통해 연결을 관리하는 클래스 (`getConnection`) |
| Connection | DB와의 연결을 의미하는 객체 |
| Statement | SQL을 실행하는 객체 (정적 쿼리 사용) |
| ResultSet | SQL 결과를 담는 객체 (`SELECT` 전용) |
| try-with-resources | 자원 자동 반납을 위한 구문. JDBC에 매우 중요! |

---

## 🧾 예시 코드

> 📄 예시 파일: JdbcMain.java
> 

```java
import java.sql.*;

public class JdbcMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100))");
            stmt.execute("INSERT INTO students VALUES (1, '봄이', '통계학')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("major"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 📌 포인트 요약

- `DriverManager.getConnection()`으로 DB 연결을 생성함
- SQL 실행에는 `Statement`, 조회 결과는 `ResultSet`으로 받음
- `try-with-resources`를 사용하면 `Connection`, `Statement` 자동 닫힘 처리
- JDBC는 SQL을 코드에 직접 작성해야 하므로 유지보수가 힘듦 → 이후 JPA로 개선됨

---

## 🧪 실습 미션

> 🎯 JDBC로 students 테이블을 생성하고 데이터 조회까지 해보자!
> 
1. H2 메모리 데이터베이스를 사용해서 자바에서 `students` 테이블을 생성하세요
2. 학생 데이터를 직접 INSERT 하세요 (예: 1, '봄이', '통계학')
3. `SELECT * FROM students`로 모든 데이터를 출력하세요
4. 자원은 반드시 `try-with-resources`로 반납하세요

> 📁 파일명 예시: JdbcMain.java
>