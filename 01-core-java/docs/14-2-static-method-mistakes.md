# [14-2단계] 실수노트

> 💻 실습 코드: StringUtil.java
> 

---

### 😅 실수 1 — `StringBuilder` 클래스 이름 오타

```java
new stringBuilder(s) // ❌ 컴파일 에러 발생
```

✅ 정답:

```java
new StringBuilder(s) // ✅ 대문자 S로 시작해야 함
```

📌 **설명**:

- 자바는 대소문자를 구분하므로 `stringBuilder`는 클래스 이름이 아니라고 판단돼
- 클래스는 대문자로 시작하는 이름을 써야 하며,
    
    `StringBuilder`는 Java가 기본 제공하는 클래스 중 하나예요
    

---

### 📌 요약 포인트

- 클래스 이름은 반드시 **대문자로 시작**해야 함
- `stringBuilder` → ❌, `StringBuilder` → ✅
- 기본 제공 클래스라도 정확한 철자와 대소문자를 지켜야 인식됨