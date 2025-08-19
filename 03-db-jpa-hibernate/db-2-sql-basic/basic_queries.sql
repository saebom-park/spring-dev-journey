-- 1. 데이터 삽입
INSERT INTO students VALUES (1, '봄이', '통계학');
INSERT INTO students VALUES (2, '온이', '수학');
INSERT INTO students VALUES (3, '아리', '경영학');
INSERT INTO students VALUES (4, '태오', '컴퓨터공학');

-- 2. 통계학 전공자만 조회
SELECT * FROM students WHERE major = '통계학';

-- 3. 이름이 '봄이'인 학생의 전공을 '데이터 사이언스'로 변경
UPDATE students SET major = '데이터사이언스' WHERE name = '봄이';

SELECT * FROM students WHERE name = '봄이';

-- 4. id가 4인 학생을 삭제
DELETE FROM students WHERE id = 4;

SELECT * FROM students;