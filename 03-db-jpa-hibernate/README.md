# 🧷 03. DB 연동 & JPA / Hibernate

> 📁 폴더 위치: `03-db-jpa-hibernate/`
>
> 자바 애플리케이션과 데이터베이스를 연결하는 다양한 방식(JDBC, DAO, JPA)을 학습합니다. SQL 기초부터 JDBC 실습, DAO 구조 분리, 트랜잭션 처리까지의 흐름을 경험한 뒤, ORM 기반인 JPA의 동작 방식도 실습하게 됩니다.

---

## ✅ 학습 목표

- 관계형 데이터베이스 구조와 SQL 기본 문법 학습
- JDBC를 활용한 DB 연결 및 CRUD 처리 흐름 체득
- DAO 패턴과 트랜잭션 처리 방식 적용 실습
- 이후 JPA 기반 ORM 구조와 영속성 컨텍스트 이해

---

## 🧠 전체 학습 구성 (총 11단계)

| 챕터                       | 코드    | 주제                         |
|--------------------------|-------|----------------------------|
| DB 연동 기초 (JDBC & SQL)    | DB-1  | 관계형 데이터베이스 구조 이해           |
| 〃                        | DB-2  | SQL 문법 기초 실습               |
| 〃                        | DB-3  | JDBC 기반 DB 연결 실습           |
| 〃                        | DB-4  | CRUD 흐름과 JDBC 처리 구조 익히기    |
| 〃                        | DB-5  | DAO 패턴 설계와 역할 분리           |
| 〃                        | DB-6  | 트랜잭션 처리 흐름과 롤백 제어          |
| DB 연동 심화 (MyBatis / JPA) | DBA-1 | MyBatis 기본 흐름 및 매핑 구조      |
| 〃                        | DBA-2 | 동적 SQL 및 resultMap 적용 실습   |
| 〃                        | DBA-3 | JPA 엔티티 매핑 및 Repository 설계 |
| 〃                        | DBA-4 | 연관관계 매핑 실습 (단방향/양방향)       |
| 〃                        | DBA-5 | 트랜잭션 전파와 영속성 컨텍스트 이해       |

---

## 📂 문서 구성 예시

| 파일명                               | 설명                            |
|-----------------------------------|-------------------------------|
| `01-rdb-structure.md`             | RDB 구조와 테이블 설계 흐름 정리          |
| `02-sql-basic.md`                 | SQL 문법 예시 및 실습 미션 정리          |
| `03-jdbc-basic.md`                | JDBC 연결 실습 정리 문서              |
| `JdbcSelectAll.java`              | 전체 목록 조회 실습 코드                |
| `05-dao-pattern-findall-extra.md` | DAO 패턴 세부 분석 정리               |
| `06-transaction-questions.md`     | 트랜잭션 실습 중 질문 노트               |
| `dba-3-entity-mapping.md`         | JPA 엔티티 매핑 및 Repository 구조 정리 |
| `dba-5-questions.md`              | JPA 트랜잭션 흐름 관련 질문 노트          |

---

## 📁 실습 디렉토리 구성 기준

- 각 단계별로 전용 폴더 생성: `01-rdb-structure/` ~ `06-transaction/`, `dba-1/` ~ `dba-5/`
- 실습 코드는 `.java`, `.sql`, `.xml` 중심으로 관리
- 문서 정리는 `spring-doc-format-rules.md` 포맷을 따름

---

## 🧭 학습 흐름 요약

### 1. DB 연동 기초 (JDBC & SQL)
- 관계형 DB 구조와 SQL 기초를 학습하고
- JDBC로 DB를 직접 연결해 데이터 처리 실습
- CRUD → DAO → 트랜잭션 흐름까지 정복

### 2. DB 연동 심화 (MyBatis / JPA)
- MyBatis로 SQL 중심 매핑 구조 경험
- 이후 JPA의 객체 중심 ORM 구조로 전환
- 영속성 컨텍스트와 트랜잭션 전파 개념까지 확장

---

> “JDBC로 시작해 DAO로 구조화하고,
> 트랜잭션 흐름을 익히며,
> MyBatis & JPA로 유지보수성과 확장성을 갖춘다.”