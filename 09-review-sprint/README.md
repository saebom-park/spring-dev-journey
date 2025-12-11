# 🌿 spring-dev-journey: 총복습 스프린트 (09-review-sprint)

> 이 디렉토리는 지금까지 학습한 Java, JDBC, Spring, MyBatis, JPA 내용을  
> **핵심 개념 중심으로 다시 구현해보는 복습 실습 모음입니다.**

---

## ✅ 목적

- 주요 개념과 흐름을 다시 정리하고 손으로 재구현하기
- JDBC → MyBatis → JPA까지의 CRUD 기반 로직을 재확인
- Spring MVC 구조와 API 작성 흐름을 반복하여 익숙해지기

---

## 🧠 진행 방식

1. 각 폴더별 실습 문제(README)를 확인한다.
2. 코드를 직접 작성하며 이전 개념을 다시 떠올린다.
3. 필요한 경우 기존 수업자료를 참고한다.
4. 구현 후 코드 리뷰 및 실수·질문을 문서에 기록한다.

---

## 📚 복습 세트 목록

| 세트명 | 폴더명 | 주제 | 주요 개념 |
|--------|--------|------|-----------|
| REVIEW-1-1 | review-1-1-bookstore | 도서 시스템 | 클래스, 생성자, static, 예외 |
| REVIEW-1-2 | review-1-2-album-manager | 앨범 관리 | 인터페이스, 다형성 |
| REVIEW-1-3 | review-1-3-membership-grade | 멤버십 등급 | 상속, 추상 클래스 |
| REVIEW-2-1 | review-2-1-jdbc-crud | 도서 CRUD | JDBC, DAO |
| REVIEW-2-2 | review-2-2-jdbc-error-case | 예외 처리 | 트랜잭션, 에러 핸들링 |
| REVIEW-2-3 | review-2-3-dao-integration | DAO 통합 | CRUD + 트랜잭션 |
| REVIEW-3-1 | review-3-1-spring-api-basic | 회원 등록 API | DI, @RestController, DTO |
| REVIEW-3-2 | review-3-2-spring-layered | 3계층 구조 API | Controller → Service → Repository |
| REVIEW-3-3 | review-3-3-json-mapping | JSON 매핑 | @RequestBody, @ResponseBody |
| REVIEW-4-1 | review-4-1-mybatis-search | 상품 검색 | Mapper XML, resultMap |
| REVIEW-4-2 | review-4-2-dynamic-sql | 동적 SQL | 조건 분기, where |
| REVIEW-4-3 | review-4-3-resultMap-nested | 중첩 매핑 | 관계형 매핑 |
| REVIEW-5-1 | review-5-1-jpa-entity | 엔티티 설계 | @Entity, @Id |
| REVIEW-5-2 | review-5-2-jpa-relation | 연관관계 | @ManyToOne, LAZY |
| REVIEW-5-3 | review-5-3-jpa-tx-context | 트랜잭션 동작 | flush, dirty checking |

---

## ✨ 목표

- 핵심 개념을 빠르게 다시 정리
- 손으로 구현하며 흐름 재확인
- 실수 포인트를 정리하고 다음 실습에 반영