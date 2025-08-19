# 🧷 03. DB 연동 & JPA / Hibernate

> 📁 폴더 위치: `03-db-jpa-hibernate/`
>
> 자바 애플리케이션과 데이터베이스를 연결하는 다양한 방식(JDBC, DAO, JPA)을 학습합니다.
> SQL 기초부터 JDBC 실습, DAO 구조 분리, 트랜잭션 처리까지의 흐름을 경험한 뒤,
> ORM 기반인 JPA의 동작 방식도 실습하게 됩니다.

---

## ✅ 학습 목표

- 관계형 데이터베이스 구조와 SQL 기본 문법 학습  
- JDBC를 활용한 DB 연결 및 CRUD 처리 흐름 체득  
- DAO 패턴과 트랜잭션 처리 방식 적용 실습  
- 이후 JPA 기반 ORM 구조와 영속성 컨텍스트 이해  

---

## 🧠 전체 학습 구성 (총 11단계)

| 챕터                       | 코드   | 주제                           | 폴더명 |
|--------------------------|------|------------------------------|------------------------------|
| DB 연동 기초 (JDBC & SQL)    | DB-1 | 관계형 데이터베이스 구조 이해             | `db-1-rdb-structure` |
| 〃                        | DB-2 | SQL 문법 기초 실습                 | `db-2-sql-basic` |
| 〃                        | DB-3 | JDBC 기반 DB 연결 실습             | `db-3-jdbc-basic` |
| 〃                        | DB-4 | CRUD 흐름과 JDBC 처리 구조 익히기      | `db-4-crud-basic` |
| 〃                        | DB-5 | DAO 패턴 설계와 역할 분리             | `db-5-dao-pattern` |
| 〃                        | DB-6 | 트랜잭션 처리 흐름과 롤백 제어            | `db-6-transaction` |
| DB 연동 심화 (MyBatis / JPA) | DBA-1 | MyBatis 기본 흐름 및 매핑 구조         | `dba-1-mybatis-basic` |
| 〃                        | DBA-2 | 동적 SQL 및 resultMap 적용 실습      | `dba-2-dynamic-sql` |
| 〃                        | DBA-3 | JPA 엔티티 매핑 및 Repository 설계     | `dba-3-jpa-entity-repository` |
| 〃                        | DBA-4 | 연관관계 매핑 실습 (단방향/양방향)         | `dba-4-1-relation-mapping-unidirectional`<br>`dba-4-2-relation-mapping-bidirectional` |
| 〃                        | DBA-5 | 트랜잭션 전파와 영속성 컨텍스트 이해        | `dba-5-transaction-cascade` |

---

## 📂 문서 구성 예시

| 파일명                                              | 설명                                   |
|--------------------------------------------------|--------------------------------------|
| `db-1-rdb-structure.md`                           | RDB 구조와 테이블 설계 흐름 정리                 |
| `db-2-sql-basic.md`                               | SQL 문법 예시 및 실습 미션 정리                 |
| `db-3-jdbc-basic.md`                              | JDBC 연결/CRUD 실습 정리                      |
| `db-4-crud-basic.md`                              | JDBC 기반 CRUD 처리 구조 정리                  |
| `db-5-dao-pattern.md`                             | DAO 패턴 설계와 역할 분리 정리                  |
| `db-6-transaction.md`                             | 트랜잭션 처리 흐름과 롤백 제어 정리               |
| `dba-1-mybatis-basic.md`                          | MyBatis 기본 흐름 및 매퍼 구조 정리              |
| `dba-2-dynamic-sql.md`                            | 동적 SQL, resultMap 적용 정리                 |
| `dba-3-jpa-entity-repository.md`                  | JPA 엔티티 매핑 & Repository 설계 정리         |
| `dba-4-1-relation-mapping-unidirectional.md`      | 연관관계 매핑 실습 — 단방향                      |
| `dba-4-2-relation-mapping-bidirectional.md`       | 연관관계 매핑 실습 — 양방향                      |
| `dba-5-transaction-cascade.md`                    | 트랜잭션 전파 & 영속성 컨텍스트 정리               |

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
