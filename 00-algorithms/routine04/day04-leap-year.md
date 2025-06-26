# Day04 | 윤년

> [📎 백준 - 윤년](https://www.acmicpc.net/problem/2753)
> 

---

### 💡 개념 요약

- 조건문을 활용해 윤년 여부를 판단 (`if`, `&&`, `||` 조합)
- 윤년 조건: `4의 배수 && 100의 배수 아님` 또는 `400의 배수`
- 출력은 `1` 또는 `0`만 단독으로 출력 (메시지 ❌)
- 클래스명은 `Main`으로 고정 (백준 전용 규칙)

---

### 🧾 예시 코드

**내 풀이 (조건문 방식)**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        scanner.nextLine();

        int result = 0;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            result = 1;
        }
        System.out.println(result);
    }
}

```

**온이 풀이 (삼항 연산자)**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        System.out.println(
            (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 1 : 0
        );
    }
}

```

---

### 📌 비교 포인트

| 항목 | 봄이 풀이 | 온이 풀이 |
| --- | --- | --- |
| 조건문 방식 | `if-else` 구조 | 삼항 연산자 |
| 가독성 | 조건이 명확히 보임 | 짧고 간결함 |
| 변수 사용 | `int result` 활용 | 변수 없이 바로 출력 |
| 적합성 | 로직 학습에 좋음 | 한 줄 제출용으로 효율적 |

---

### 🌱 핵심 개념

- 윤년 조건: `4의 배수 && 100의 배수 아님 || 400의 배수`
- `System.out.println()`으로 **숫자만 출력**해야 정답 처리됨
- 삼항 연산자도 로직 단축에 유용하나 가독성 우선 시 if-else가 적합