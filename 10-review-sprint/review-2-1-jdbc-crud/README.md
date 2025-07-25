# REVIEW-2-1: 도서 CRUD (jdbc-crud)

> ✨ JDBC로 데이터베이스와 Java 객체를 수동 연결하며  
> INSERT/SELECT/UPDATE/DELETE 흐름과 DAO 패턴을 익히는 실습입니다.

---

## 🎯 목표

- DB와 Java 객체 간의 수동 매핑 체험
- JDBC 연결 구조와 SQL 실행 흐름 이해
- DAO 패턴을 통해 책임 분리된 구조 구현

---

## 💡 실습 시나리오

📚 **도서 관리 시스템 (BookManager)**

- `Book` 객체는 DB의 `books` 테이블과 매핑됨
- Java에서 도서 객체를 생성/조회/수정/삭제하는 기능을 JDBC로 구현합니다.

---

## 📋 구현 요구사항

### 1. `Book` 클래스
- 필드: `id`, `title`, `author`, `price`
- 생성자, getter/setter, `toString()` 포함

### 2. DB 준비
```sql
CREATE TABLE books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  author VARCHAR(100),
  price INT
);
```

### 3. JDBC 연결 방식
- `BookDao` 내부에 직접 JDBC 연결 정보 작성

```java
String url = "jdbc:mysql://localhost:3306/yourdb";
String user = "root";
String password = "yourpassword";
Connection conn = DriverManager.getConnection(url, user, password);
```

### 4. `BookDao` 클래스 (DAO 패턴)
- `insertBook(Book book)`
- `selectAllBooks()`
- `selectBookById(int id)`
- `updateBook(Book book)`
- `deleteBookById(int id)`

### 5. `Main.java`
- 책 등록 → 전체 조회 → 특정 조회 → 수정 → 삭제 순서대로 실행

---

## 🎯 구현 목표 체크리스트

| 항목 | 포함 여부 |
|------|------------|
| JDBC 연결 성공 | ✅ |
| SQL 실행 준비 (PreparedStatement) | ✅ |
| CRUD 전 기능 구현 | ✅ |
| DAO 패턴 분리 적용 | ✅ |
| 리소스 안전하게 닫기 | ✅ |
| 예외 처리 및 메시지 출력 | ✅ |

---

## 📂 폴더 구조 예시

```
review-2-1-jdbc-crud/
├── README.md
└── src/
    └── com.review21/
        ├── Book.java
        ├── BookDao.java
        └── Main.java
```
