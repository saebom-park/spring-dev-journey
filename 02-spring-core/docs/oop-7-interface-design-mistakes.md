# [OOP-7단계] 실수노트

> 💻 실습 코드: SmartWatch.java
> 

---

### 😅 실수 1 — 입력 문자열을 숫자로 바꿀 때 예외 발생 가능

```java
int appNum = Integer.parseInt(input); // ❌ 숫자가 아닌 경우 예외 발생
```

✅ 정답:

```java
try {
    appNum = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("오류 발생: " + e.getMessage());
}
```

📌 **설명**:

사용자 입력은 언제든지 예외를 유발할 수 있으므로, **숫자 입력 구간은 항상 try-catch 처리**가 필요함.

봄이는 실습 중 이를 직접 인식하고 안전하게 반영함.

---

### 📌 요약 포인트

- `Integer.parseInt()`는 문자열이 정수가 아닐 경우 예외 발생
- 숫자 입력은 항상 `try-catch`로 감싸기
- `Scanner.nextInt()`보다 `nextLine()` + `parseInt()`가 유연함