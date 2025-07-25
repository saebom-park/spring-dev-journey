# REVIEW-2-2: 예외 케이스 핸들링 (jdbc-error-case)

> ✨ JDBC 기반의 데이터 처리 중 발생할 수 있는 예외 상황을 다루고,
> 트랜잭션 흐름과 try-catch-finally 구조를 복습하는 실습입니다.

---

## 🎯 목표

* 트랜잭션 개념 및 commit/rollback 흐름 이해
* try-catch-finally 블록을 통한 예외 안전성 확보
* JDBC 예외 발생 시 자원 정리 및 오류 메시지 출력 연습

---

## 💡 실습 시나리오

📚 **도서 등록 중 예외 케이스 발생 시 롤백 처리**

* 여러 건의 `Book` 데이터를 한 번에 등록하는 중간에 오류가 발생하면,
* 전체 트랜잭션을 롤백하여 **데이터 일관성**을 유지해야 합니다.

---

## 📋 구현 요구사항

### 1. `Book` 클래스 재사용

* 필드: `id`, `title`, `author`, `price`

### 2. DB 준비 (books 테이블 그대로 사용)

```sql
CREATE TABLE books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  author VARCHAR(100),
  price INT
);
```

### 3. JDBC 트랜잭션 처리 방식

* `setAutoCommit(false)`로 자동 커밋 해제
* 예외 발생 시 `rollback()` 수행
* 정상 종료 시 `commit()` 호출
* `finally` 블록에서 `conn.close()` 포함

### 4. `BookDao` 클래스에 메서드 추가

* `insertMultipleBooks(List<Book> books)`

  * 중간에 `null` 값 등 예외 발생하도록 테스트 케이스 구성

### 5. `Main.java`

* 여러 도서를 한 번에 등록 시도
* 오류 발생 시 롤백 → 사용자에게 메시지 출력

---

## 🎯 구현 목표 체크리스트

| 항목                        | 포함 여부 |
| ------------------------- | ----- |
| `setAutoCommit(false)` 사용 | ✅     |
| 예외 발생 시 `rollback()` 수행   | ✅     |
| 정상 종료 시 `commit()` 수행     | ✅     |
| try-catch-finally 블록 구성   | ✅     |
| 예외 메시지 출력 처리              | ✅     |
| 리소스 안전하게 정리               | ✅     |

---

## 📂 폴더 구조 예시

```
review-2-2-jdbc-error-case/
├── README.md
└── src/
    └── com.review22/
        ├── Book.java
        ├── BookDao.java
        └── Main.java
```
