# [DB-5단계] 실수노트

> 💻 실습 코드: BookDao.java
> 

---

### 😅 실수 1 — `prepareStatment()` 오타로 컴파일 에러 발생

```java
// 오타
conn.prepareStatment(sql);
```

✅ **정답:**

```java
conn.prepareStatement(sql);
```

📌 **설명**:

- `prepareStatement()`는 정확한 메서드명! 오타로 인해 메서드를 인식하지 못함
- IDE에서 자동완성 기능을 적극 활용하면 이런 오타는 방지할 수 있음
- JDBC의 메서드는 명확한 철자 규칙을 가지므로 암기보다는 **IDE 도움받기 전략**이 중요

---

### 😅 실수 2 — `findAll()`에서 리스트에 Book 추가하지 않음

```java
// 잘못된 코드
while (rs.next()) {
    Book book = new Book();
    book.setId(rs.getInt("id"));
    book.setTitle(rs.getString("title"));
    book.setAuthor(rs.getString("author"));
    // ❌ list.add(book); 빠짐
}
```

✅ **정답:**

```java
while (rs.next()) {
    Book book = new Book();
    book.setId(rs.getInt("id"));
    book.setTitle(rs.getString("title"));
    book.setAuthor(rs.getString("author"));
    list.add(book); // ✅ 리스트에 추가
}
```

📌 **설명**:

- Book 객체를 만들어도 `list.add(book);`가 없으면 결과 리스트는 비어 있음
- `return` 직전에 리스트가 제대로 쌓였는지 꼭 체크!

---

### 📌 요약 포인트

- 철자 오타는 `NoSuchMethod` 에러의 흔한 원인
- 자동완성 (`Ctrl + Space`)을 적극 활용하자!
- 메서드 이름은 정확히, 철자 하나도 틀리면 작동 안 됨
- 반복문에서 생성한 객체는 결과 리스트에 반드시 추가!
- 실습 후 `System.out.println()`으로 리스트 출력 → 누락 여부 바로 확인 가능
- 출력이 없다면 `add()` 실수 의심해보기!