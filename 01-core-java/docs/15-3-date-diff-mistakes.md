# [15-3단계] 실수노트

> 💻 실습 코드: DateDiffPractice.java
> 

---

### 😅 실수 1 — 문자열 누적한 결과를 마지막에 덮어씀

```java
String diffTxt = "생일까지";
if (diffYear > 0) {
    diffTxt += diffYear + "년";
}
...
diffTxt = " 남았습니다."; // ❌ 이전 내용 덮어버림
```

✅ 정답:

```java
String diffTxt = "생일까지";
if (diffYear > 0) diffTxt += " " + diffYear + "년";
if (diffMonth > 0) diffTxt += " " + diffMonth + "개월";
if (diffDay > 0) diffTxt += " " + diffDay + "일";
diffTxt += " 남았습니다.";
```

📌 **설명**:

- 문자열을 `+=`로 누적하는 중이라면 마지막에 `" 남았습니다."`도 `+=`로 붙여야 함
- `=`로 덮어쓰면 그동안 누적한 문자열이 모두 사라짐!

---

### 😅 실수 2 — 여러 조건을 `else if`로 처리함

```java
if (diffYear > 0) {
    diffTxt += " " + diffYear + "년";
} else if (diffMonth > 0) {
    diffTxt += " " + diffMonth + "개월";
} else if (diffDay > 0) {
    diffTxt += " " + diffDay + "일";
}
```

✅ 정답:

```java
if (diffYear > 0) diffTxt += " " + diffYear + "년";
if (diffMonth > 0) diffTxt += " " + diffMonth + "개월";
if (diffDay > 0) diffTxt += " " + diffDay + "일";
```

📌 **설명**:

- `else if`는 하나라도 조건이 참이면 **나머지는 전부 건너뜀**
- 날짜 차이처럼 **여러 항목을 동시에 누적 출력해야 할 때는 `if`만 단독으로 반복**하는 게 맞음

---

### 📌 요약 포인트

- 문자열 누적 중에는 `+=`만 사용하고, 절대 `=`로 덮어쓰지 말 것!
- 여러 조건이 동시에 성립할 수 있을 때는 `if`만 단독으로 반복할 것