# 🧷 03. DB 연동 & JPA / Hibernate

> 이 단계에서는 자바 애플리케이션과 데이터베이스를 연결하는 방법을 학습합니다.  
> JDBC 기초부터 시작해 DAO, 트랜잭션까지 실제 DB 처리 흐름을 익히고,  
> 이후에는 JPA로 전환하면서 ORM 방식도 경험하게 됩니다.

---

## ✅ 학습 목표

- 관계형 데이터베이스 구조와 SQL 기본 문법 학습
- JDBC를 활용한 DB 연결 및 CRUD 처리
- DAO 패턴과 트랜잭션 처리 방식 이해
- 이후 JPA 기반 ORM의 필요성과 구조 학습

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| RDB 구조 | 테이블, 관계, 기본키/외래키 등 관계형 DB 기초 |
| SQL 기초 | `SELECT`, `INSERT`, `UPDATE`, `DELETE` 문 사용 |
| JDBC 연결 | `Connection`, `Statement`, `ResultSet` 등 사용법 |
| CRUD 처리 | JDBC 기반 INSERT/SELECT/UPDATE/DELETE 구현 |
| DAO 패턴 | DB 접근 책임을 전담하는 객체 구조 |
| 트랜잭션 처리 | JDBC의 수동 커밋, 롤백 처리 이해 |
| JPA 소개 | ORM 방식 전환 배경 및 JPA 핵심 개념 체험 (후반부 예정) |

---

## 📂 문서 구성

| 단계 | 파일명 | 주제 |
|------|--------|------|
| DB-1 | `01-rdb-structure.md` | 관계형 데이터베이스 구조 이해 |
| DB-2 | `02-sql-basic.md` | SQL 기초문 학습 |
| DB-3 | `03-jdbc-basic.md` | JDBC 연결 실습 |
| DB-4 | `04-crud-basic.md` | CRUD 처리 실습 |
| DB-5 | `05-dao-pattern.md` | DAO 패턴 설계 및 적용 |
| DB-6 | `06-transaction.md` | 트랜잭션 처리 실습 |
| ...  | `...-mistakes.md` | JDBC & JPA 실수노트 정리 |
| ...  | `...-extra.md` | 트랜잭션, 영속성 등 추가 개념 |

---

## 📁 실습 디렉토리 구성 기준

- 각 단계별로 전용 폴더를 만들어 실습 파일을 분리 관리합니다.
- 실습 파일에는 `.sql`, `.java`, `application.properties` 등이 포함될 수 있습니다.
- `.md` 문서는 문서 포맷 규칙에 따라 정리하고, 실습 코드는 별도 보관합니다.

### 예시 구조

```
03-db-jpa-hibernate/
├── 01-rdb-structure/
│   ├── 01-rdb-structure.md           ← 수업 정리 문서
│   └── students_courses_schema.sql  ← 실습 SQL
├── 02-sql-basic/
│   ├── 02-sql-basic.md
│   └── basic_queries.sql
├── 03-jdbc-basic/
│   ├── 03-jdbc-basic.md
│   └── JdbcMain.java
```

- 문서 파일명: `{단계번호}-{주제}.md`  
- 실습 파일명: 학습 내용이 명확하게 드러나는 이름으로 자유롭게 작성

---

## 🧭 학습 흐름

1. RDB 구조와 SQL 기초를 먼저 학습
2. JDBC를 통해 자바 코드와 DB를 직접 연결해봄
3. CRUD 처리 흐름을 JDBC로 구현
4. DAO 패턴으로 역할 분리된 구조 체험
5. 트랜잭션 처리 흐름 실습
6. 이후 JPA 기반의 ORM 구조로 확장 (스프링 연동 예정)

---

## 📌 작성 기준

- Java 17 + Spring Boot 3.x + H2 or MySQL 환경
- JDBC 실습 중심 → DAO, 트랜잭션 처리 → JPA 도입으로 전환
- 실수 노트는 실무에서 자주 발생하는 DB 연동 실수 위주 정리

---

> “JDBC로 DB를 배우고,  
> DAO로 구조화하고,  
> JPA로 유지보수를 해결한다.”  
>  
> 스프링 백엔드의 성장 단계는  
> **DB와의 협력 구조를 어떻게 설계하느냐에 달려있어.**
