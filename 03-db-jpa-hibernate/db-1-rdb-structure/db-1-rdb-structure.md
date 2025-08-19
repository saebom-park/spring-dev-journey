# [DB-1단계] RDB 구조 이해 (rdb-structure)

> ✨ "데이터를 효율적으로 저장하고 관리하려면 어떻게 해야 할까?"
> 
> 
> 👉 테이블, 관계, 키 제약조건으로 구성된 RDB 구조를 이해하면 돼!
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 테이블 (Table) | 데이터를 행(Row), 열(Column) 구조로 저장하는 기본 단위 |
| 행 (Row) | 테이블의 한 줄, 하나의 데이터 레코드에 해당 |
| 열 (Column) | 데이터 항목의 종류, 속성을 정의 (예: 이름, 나이 등) |
| 기본키 (Primary Key) | 각 행을 유일하게 식별하는 키 (중복 ❌, NULL ❌) |
| 외래키 (Foreign Key) | 다른 테이블의 기본키를 참조하는 키 (테이블 간 관계 형성) |
| 제약조건 (Constraint) | 데이터 무결성을 보장하는 규칙 (PK, FK, NOT NULL 등) |

---

## 🧾 예시 코드

> 📄 예시: 회원/주문 테이블 설계 (SQL)
> 

```
CREATE TABLE members (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT
);

CREATE TABLE orders (
    id INT PRIMARY KEY,
    member_id INT,
    product VARCHAR(100) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id)
);
```

---

## 📌 포인트 요약

- 관계형 데이터베이스는 "테이블 + 관계" 구조로 구성됨
- *기본키(PK)**는 중복 없이 각 데이터를 고유하게 식별함
- *외래키(FK)**는 다른 테이블의 PK를 참조하여 관계를 설정함
- 실무에서는 1:N 관계가 자주 등장 → 한 회원이 여러 주문을 가질 수 있음
- 테이블 설계만으로도 도메인 구조와 비즈니스 관계를 유추할 수 있음

---

## 🧪 실습 미션

> 🎯 테이블 설계 감각 잡기!
> 
1. 아래 조건을 만족하는 두 개의 테이블을 설계해보자 (SQL 또는 표 형식으로)

### students 테이블

| 컬럼명 | 타입 | 제약조건 |
| --- | --- | --- |
| id | INT | PK |
| name | VARCHAR | NOT NULL |
| major | VARCHAR |  |

### courses 테이블

| 컬럼명 | 타입 | 제약조건 |
| --- | --- | --- |
| id | INT | PK |
| student_id | INT | FK (students.id) |
| title | VARCHAR | NOT NULL |
1. 두 테이블의 관계 정의:
→ students 1명은 courses 여러 개 수강 → **1:N 관계**
2. SQL이 익숙하지 않다면 표만 그려도 OK!