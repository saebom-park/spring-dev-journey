# [DB-5단계] insert(Book book) 메서드 완전 해부

> BookDao 클래스의 insert() 메서드를 한 줄씩 분석하여 완전히 이해하기 위한 문서입니다.

---

## 📄 전체 코드

```java
public void insert(Book book) throws SQLException {
    String sql = "INSERT INTO books VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, book.getId());
        pstmt.setString(2, book.getTitle());
        pstmt.setString(3, book.getAuthor());
        pstmt.executeUpdate();
    }
}
```

---

## 🧠 한 줄씩 해석

### 1. `public void insert(Book book) throws SQLException {`
- Book 객체를 받아 DB에 저장하는 역할
- 예외는 밖으로 던짐 (`throws SQLException`)

### 2. `String sql = "INSERT INTO books VALUES (?, ?, ?)";`
- SQL 템플릿을 미리 준비
- `?`는 나중에 값으로 채움

### 3. `try (PreparedStatement pstmt = conn.prepareStatement(sql)) {`
- 쿼리 실행 준비 도구 생성
- try-with-resources로 자동 자원 정리

### 4~6. `pstmt.setX(...)`
- 각각 `book` 객체에서 값을 꺼내 `?` 자리에 채움

### 7. `pstmt.executeUpdate();`
- SQL 실행
- 실제로 DB에 반영됨

---

## 💬 핵심 요약

- `Book`은 DB 한 줄(Row)을 표현
- `?` 자리는 안전하고 가독성 좋은 방식
- 자원 누수를 막기 위해 try-with-resources 사용
