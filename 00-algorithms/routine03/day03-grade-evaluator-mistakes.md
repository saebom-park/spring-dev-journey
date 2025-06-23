# Day03 | 실수노트

> 💻 실습 코드: GradeEvaluator.java
> 

---

### 😅 실수 1 — 점수 구간 누락 (80~89 범위)

```java
if (score >= 90 && score <= 100) {
    grade = "A";
} else if (score >= 70) {
    grade = "B";  // ❌ 80~89도 B여야 하는데 누락됨
} else if (score >= 60) {
    grade = "C";
} else {
    grade = "F";
}
```

✅ 정답:

```java
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else if (score >= 60) {
    grade = "D";
} else {
    grade = "F";
}
```

📌 **설명**:

- 80~89 구간을 `B`로 처리해야 하는데 조건이 누락되어 `C`로 출력됨
- 백준은 내부 테스트케이스(예: 85점)로 채점하기 때문에 조건 누락 시 무조건 오답 처리

---

### 😅 실수 2 — 출력 형식 불일치

```java
System.out.println("당신의 학점은 " + grade + "입니다.");
```

✅ 정답:

```java
System.out.println(grade);
```

📌 **설명**:

- 백준은 출력 형식이 문제 예시와 **100% 동일**해야 정답 처리됨
- `"당신의 학점은"`과 같은 문구가 들어가면 무조건 오답

---

### 📌 요약 포인트

- 조건문에서 문제에서 요구한 점수 구간을 모두 포함해야 한다
- 백준은 출력 형식이 1글자라도 다르면 정답 처리되지 않음
- 클래스명은 `Main`, 출력은 **학점 문자만** 단독 출력