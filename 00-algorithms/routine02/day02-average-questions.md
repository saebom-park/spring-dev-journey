# Day02 | 질문노트

### ❓ Q1. Stream이란 무엇인가요?

- Java 8에서 도입된 **데이터 흐름 처리 도구**
- 배열이나 리스트를 선언형 스타일로 처리할 수 있음
- `sum()`, `average()`, `filter()` 등 함수형 메서드로 간결하게 연산 가능

```java
int sum = Arrays.stream(arr).sum();
double avg = Arrays.stream(arr).average().orElse(0);
```

---

### ❓ Q2. OptionalDouble은 뭔가요?

- `average()` 결과는 값이 없을 수도 있어서 `OptionalDouble`로 반환됨
- `null` 대신 비어있음을 표현하는 **안전한 래퍼 객체**
- `.orElse(0)` → 값이 없을 때 기본값 지정 가능

```java
OptionalDouble avg = Arrays.stream(arr).average();
double result = avg.orElse(0); // 값이 없으면 0 반환
```

---

### ✅ 요약 표

| 개념 | 설명 |
| --- | --- |
| Stream | 배열/컬렉션 데이터를 함수형 스타일로 처리 |
| average() | 평균 계산, `OptionalDouble` 반환 |
| OptionalDouble | 값이 없을 수도 있는 안전한 래퍼 |
| orElse(0) | 값이 없을 경우 기본값 설정 |