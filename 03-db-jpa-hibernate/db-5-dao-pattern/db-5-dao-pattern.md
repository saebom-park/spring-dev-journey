# [DB-5단계] DAO 패턴 설계 (dao-pattern)

> “지저분한 JDBC 코드를 깔끔하게 분리하자!”
> 
> 
> DB 접근 책임을 전담하는 DAO 객체를 직접 만들어보며,
> 
> 유지보수 가능한 구조로 JDBC 코드를 리팩토링해보자.
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| DAO (Data Access Object) | DB 접근 로직을 전담하는 객체 |
| 관심사 분리 | 비즈니스 로직 ↔ DB 처리 코드 분리 |
| 재사용성 | 다양한 클래스에서 공통 DAO를 호출 |
| 메서드 단위 추상화 | insert(), findAll() 등 명확한 단위로 메서드 제공 |
| 의존성 최소화 | DB 연결은 외부에서 주입받거나 내부에서 관리 |

---

### 🧾 예시 코드

> 📄 예시 파일: **Book**.java **(VO 역할)**
> 

```java
public class Book {
    private int id;
    private String title;
    private String author;

    // 생성자, getter/setter 생략
}
```

> 📄 예시 파일: **BookDao.java**
> 

```java
import java.sql.*;

public class BookDao {
    private Connection conn;

    public BookDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Book book) throws SQLException {
        String sql = "INSERT INTO books VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.executeUpdate();
        }
    }

    public List<Book> findAll() throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                list.add(book);
            }
        }
        return list;
    }
}
```

---

### 📌 포인트 요약

- DAO는 **DB 연동 책임만 갖는 전담 클래스**
- JDBC 로직을 메서드 단위로 추상화하여 깔끔하게 관리 가능
- 서비스/비즈니스 코드와 분리되기 때문에 **테스트나 유지보수가 쉬움**
- DAO 내부는 보통 **PreparedStatement**를 사용해 가독성과 보안 모두 확보

---

### 🧪 실습 미션

🎯 목표: `OrderDao` 클래스를 설계하고, 주문 데이터를 삽입/조회하는 DAO 구조 구현

1. `Order.java` 클래스 생성 (id, memberId, bookId, status 필드)
2. `OrderDao.java` 클래스 생성
    - `insert(Order)` 메서드 구현
    - `findAll()` 메서드 구현
3. 메인 클래스 `OrderDaoMain.java` 생성
    - DB 연결 및 `orders` 테이블 생성
    - `insert()` 호출로 주문 2건 저장
    - `findAll()` 호출로 모든 주문 출력
4. 모든 JDBC 자원은 try-with-resources로 관리

> 참고: status 값은 'PAYED', 'CANCELLED' 등 문자열로 처리해도 좋아!
>