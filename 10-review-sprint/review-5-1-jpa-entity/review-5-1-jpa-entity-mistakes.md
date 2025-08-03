# [REVIEW-5-1] 실수노트

> 💻 실습 코드: Member.java, Product.java
> 

---

### 😅 실수 1 — 테이블 이름을 명시하지 않아서 JPA가 테이블을 못 찾는 문제

```java
@Entity
public class Member {
    ...
}

```

✅ 정답:

```java
@Entity
@Table(name = "members")
public class Member {
    ...
}

```

📌 **설명**:

- JPA는 클래스 이름을 그대로 테이블 이름으로 인식해 (`Member` → `member`)
- 그런데 실제 DB 테이블 이름이 `members`처럼 복수형이면 매핑이 실패할 수 있어
- 그래서 `@Table(name = "members")`처럼 정확히 지정해주는 게 안전해

---

### 😅 실수 2 — RESTful하지 않은 경로를 컨트롤러에 사용한 문제

```java
@PostMapping("/register")
@GetMapping("/select")

```

✅ 정답:

```java
@PostMapping
@GetMapping

```

📌 **설명**:

- REST에서는 URL로 '무엇을 할지'가 아니라 '무엇에 대해' 작업하는지를 표현해야 해
- 즉, 경로는 `/members`, `/products`처럼 자원 이름만 쓰고
- 동작은 HTTP 메서드 (`GET`, `POST`)로 구분하는 게 REST 스타일이야

---

### 📌 요약 포인트

- JPA에서 클래스와 테이블 이름이 다를 경우 `@Table(name = ...)`으로 명시적으로 매핑할 것
- REST 컨트롤러 경로는 자원 중심(`/members`)으로 두고, 동작은 HTTP 메서드로 구분할 것 (`POST`, `GET`)