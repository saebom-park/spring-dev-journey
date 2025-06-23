# Day02 | 평균 구하기

> [🔗 프로그래머스 - 평균 구하기](https://school.programmers.co.kr/learn/courses/30/lessons/12944)
> 

---

### 💡 개념 요약

- 배열 평균 = 합계 ÷ 개수
- 정수끼리 나누면 `int / int`로 실수 손실 발생 → 반드시 `double` 사용
- Java 8 이상에서는 `Arrays.stream(arr).average()`도 가능

---

### 🧾 예시 코드

**내 풀이 (기본 for문 방식)**

```java
public double solution(int[] arr) {
    double sum = 0;
    for (int i = 0; i < arr.length; i++) {
        sum += arr[i];
    }
    return sum / arr.length;
}
```

**온이 풀이 (Stream API 활용)**

```java
import java.util.Arrays;

public double solution(int[] arr) {
    return Arrays.stream(arr).average().orElse(0);
}
```

---

### 📌 비교 포인트

| 항목 | 봄이 풀이 | 온이 풀이 |
| --- | --- | --- |
| 반복 방식 | `for`문 | Stream API |
| 실수 처리 | 직접 `double` 선언 | 자동 처리 |
| 가독성 | 직관적 | 간결하고 선언형 |
| 활용 버전 | 전 버전 가능 | Java 8+ 이상 |

---

### 🌱 핵심 개념

- 평균 계산 시 `double` 자료형 필수
- `Arrays.stream(arr).average()`는 OptionalDouble 반환
- `.orElse(0)`로 값이 없을 경우 기본값 설정 가능