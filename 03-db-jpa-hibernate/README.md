# 🧷 03. DB 연동 & JPA / Hibernate

> 이 단계에서는 자바 애플리케이션과 데이터베이스를 연결하는 방법을 학습합니다.  
> JDBC 기초부터 시작해 JPA와 하이버네이트의 핵심 개념과 실습을 통해 ORM에 익숙해집니다.

---

## ✅ 학습 목표

- JDBC를 사용한 기본 DB 연동 방식 이해
- SQL과 자바 코드의 연결 구조 익히기
- JPA를 활용한 객체-관계 매핑 실습
- 엔티티 설계와 영속성 컨텍스트의 개념 습득

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| JDBC | `Connection`, `Statement`, `ResultSet` 사용법 |
| SQL 기초 | `SELECT`, `INSERT`, `UPDATE`, `DELETE` 문 사용 |
| JPA란? | ORM(Object Relational Mapping)의 개념 |
| Entity 매핑 | `@Entity`, `@Id`, `@Column` 등의 사용 |
| Repository | CRUD 쿼리 작성 없이 데이터 조작 |
| 연관관계 매핑 | `@OneToMany`, `@ManyToOne`, Fetch 전략 |
| 트랜잭션 처리 | `@Transactional`을 통한 일관성 관리 |
| 영속성 컨텍스트 | 1차 캐시, Dirty Checking, Flush 타이밍

---

## 📂 문서 구성

| 파일명 예시 | 설명 |
|-------------|------|
| `01-jdbc-basic.md` | 순수 JDBC 사용 및 문제점 파악 |
| `02-jpa-intro.md` | JPA 개념 및 기본 Entity 매핑 |
| `03-repository-layer.md` | 스프링에서 JPA 연동 구조 |
| `04-relationship-mapping.md` | 엔티티 간 연관관계 설정 |
| `...-mistakes.md` | 실무에서 자주 발생하는 JPA 실수 정리 |
| `...-extra.md` | 트랜잭션, 영속성 컨텍스트 보충 개념 |

---

## 🧭 학습 흐름

1. JDBC 코드를 직접 작성해보고 불편함을 체감
2. JPA 도입 이후의 코드 간결성과 장점을 실습으로 확인
3. Entity-DB 매핑, 기본 Repository 사용법을 익히며 실무 감각 확보
4. 연관관계, 트랜잭션, 지연 로딩 등 실무 핵심 이슈를 보충 개념으로 학습

---

## 📌 작성 기준

- Java 17 + Spring Boot 3.x + H2 or MySQL 환경
- 스프링 Data JPA 기본 기능 위주로 구성
- 실수 노트는 실무에서 자주 발생하는 문제 위주 (e.g. N+1, 연관관계 실수)

---

> “JPA를 모르면 반복되는 SQL과 싸우게 돼.”  
>  
> 객체와 DB를 자연스럽게 연결하는 JPA는  
> **스프링 백엔드의 생산성과 유지보수를 책임지는 핵심 무기**야.
