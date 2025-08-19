# [DB-5단계] findAll() 메서드 완전 해부

> BookDao 클래스의 findAll() 메서드를 한 줄씩 분석하여 완전히 이해하기 위한 문서입니다.

---

## 📄 전체 코드

```java
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
```

---

## 🧠 한 줄씩 해석

### 1. `List<Book> list = new ArrayList<>();`
- 결과를 담을 바구니 준비

### 2. `String sql = "SELECT * FROM books";`
- 전체 책 목록 조회 쿼리

### 3. `try (PreparedStatement ..., ResultSet ...)`
- SQL 실행 준비 + 결과 받기
- 두 자원 모두 자동 정리

### 4~8. `while (rs.next()) {...}`
- 한 줄씩 데이터를 꺼내 Book 객체로 매핑
- 리스트에 추가

### 9. `return list;`
- 전체 책 리스트 반환

---

## 💬 핵심 요약

- DB에서 여러 줄 → 자바의 여러 객체로 변환
- ResultSet은 반복문으로 순회
- Book 객체로 꺼내어 리스트에 담음
