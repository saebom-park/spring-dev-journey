# [DB-2단계] SQL 기초문 (sql-basic)

> ✨ "DB에 저장된 데이터를 직접 다루려면 어떻게 해야 할까?"
> 
> 
> 👉 SQL 문법을 사용하면 데이터를 삽입하고, 조회하고, 수정하고, 삭제할 수 있어!
> 

---

## 💡 핵심 개념 요약

| 문법 | 설명 | 예시 |
| --- | --- | --- |
| `SELECT` | 테이블에서 데이터를 조회 | `SELECT * FROM students;` |
| `INSERT` | 테이블에 데이터를 추가 | `INSERT INTO students VALUES (1, '봄이', '통계학');` |
| `UPDATE` | 기존 데이터를 수정 | `UPDATE students SET major = '수학' WHERE id = 1;` |
| `DELETE` | 데이터를 삭제 | `DELETE FROM students WHERE id = 1;` |
| `WHERE` | 조건을 걸어 행을 필터링 | `SELECT * FROM students WHERE major = '통계학';` |

---

## 🧾 예시 코드

> 📄 예시 테이블: students
> 

```
-- 데이터 삽입
INSERT INTO students VALUES (2, '온이', '수학');

-- 데이터 조회
SELECT * FROM students;
SELECT name FROM students WHERE major = '통계학';

-- 데이터 수정
UPDATE students SET major = '빅데이터' WHERE name = '봄이';

-- 데이터 삭제
DELETE FROM students WHERE id = 2;
```

---

## 📌 포인트 요약

- `SELECT`는 모든 SQL의 시작: 은 전체 컬럼 의미
- `WHERE`로 원하는 조건만 추출 가능
- `INSERT`, `UPDATE`, `DELETE`는 **데이터를 변경**하므로 조심해서 사용해야 함
- `UPDATE`, `DELETE` 시 `WHERE` 조건을 빼면 **전체가 변경/삭제**되므로 항상 주의!

---

## 🧪 실습 미션

> 🎯 students 테이블을 대상으로 SQL 기본 문법을 직접 써보자!
> 
1. students 테이블에 아래 데이터를 추가해보세요:
    - `(3, '아리', '경영학')`
    - `(4, '태오', '컴퓨터공학')`
2. 통계학 전공자만 조회하세요
3. 이름이 '봄이'인 학생의 전공을 '데이터사이언스'로 바꾸세요
4. id가 4인 학생을 삭제하세요

> 📁 파일명 예시: basic_queries.sql
>