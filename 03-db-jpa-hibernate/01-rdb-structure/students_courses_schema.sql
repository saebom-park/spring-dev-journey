-- Drop tables if exist (optional, for reset)
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;

-- Schema SQL
CREATE TABLE students (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  major VARCHAR(100) NOT NULL
);

CREATE TABLE courses (
  id INT PRIMARY KEY,
  student_id INT,
  title VARCHAR(300) NOT NULL,
  FOREIGN KEY (student_id) REFERENCES students(id)
);

INSERT INTO students VALUES (1, '봄이', '통계학');
INSERT INTO courses VALUES (101, 1, '수리 통계학');

-- Query SQL
SELECT * FROM students;
SELECT * FROM courses;