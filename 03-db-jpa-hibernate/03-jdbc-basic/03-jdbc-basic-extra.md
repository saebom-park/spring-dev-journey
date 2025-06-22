# [DB-3단계] 추가 개념 정리

> 💭 JDBC 연결 흐름이 헷갈릴 때, 한눈에 복기할 수 있는 구조 정리
> 

---

## ✅ JDBC 기본 흐름 — "5단계 구조"

| 단계 | 키워드 | 설명 | 예시 코드 |
| --- | --- | --- | --- |
| 1 | 🔌 연결 | DB에 연결한다 | `Connection conn = DriverManager.getConnection(...)` |
| 2 | 🧾 명령 준비 | SQL 실행 준비 | `Statement stmt = conn.createStatement()` |
| 3 | 🎯 SQL 실행 | SQL을 날린다 | `stmt.execute(...)`, `stmt.executeQuery(...)` |
| 4 | 📦 결과 받기 | SELECT 결과 받음 | `ResultSet rs = stmt.executeQuery(...)` |
| 5 | 🔍 반복 처리 | 결과를 순회하며 출력 | `while (rs.next()) { ... }` |

➡ 이 모든 과정을 `try-with-resources` 안에 넣으면 자원 정리도 자동화된다.

---

## 💡 기억 문장

> 🔌 연결하고  →  🧾 명령 준비해서  →  🎯 SQL 날리고  →  📦 결과 받고  →  🔍 출력한다!
> 

---

## ✅ try-with-resources 구조 템플릿

```
try (
    Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    Statement stmt = conn.createStatement()
) {
    stmt.execute("CREATE TABLE students (id INT, name VARCHAR(50))");
    stmt.execute("INSERT INTO students VALUES (1, '봄이')");

    ResultSet rs = stmt.executeQuery("SELECT * FROM students");
    while (rs.next()) {
        System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

---

> 📌 다음 JDBC 코드를 작성할 때, 이 구조와 흐름을 머릿속 말풍선처럼 떠올려보자!
>