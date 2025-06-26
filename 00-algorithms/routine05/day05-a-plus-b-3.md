# Day05 | A+B - 3

> [📎 백준 - A+B - 3](https://www.acmicpc.net/problem/10950)
> 

---

### 💡 개념 요약

- 여러 줄 입력에서 매 줄마다 정수 2개를 받아 합을 출력하는 문제
- `Scanner.nextLine()`으로 한 줄 통째로 받고 `split(" ")`으로 나누기
- 문자열로 받은 숫자는 `Integer.parseInt()`로 정수 변환
- 입력을 먼저 배열에 저장하고, 나중에 처리하는 구조도 가능

---

### 🧾 예시 코드

**내 풀이 (입력 배열 저장 후 처리)**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cnt = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        String[] numberSet = new String[cnt];

        for (int i = 0; i < numberSet.length; i++) {
            numberSet[i] = scanner.nextLine();
        }

        for (int i = 0; i < numberSet.length; i++) {
            String[] parts = numberSet[i].split(" ");
            System.out.println(Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]));
        }
    }
}

```

**온이 풀이 (즉시 처리 방식)**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a + b);
        }
    }
}

```

---

### 📌 비교 포인트

| 항목 | 봄이 풀이 | 온이 풀이 |
| --- | --- | --- |
| 입력 처리 | 한 줄씩 저장 후 처리 | 즉시 계산 |
| 유연성 | 전처리, 후처리 가능 | 빠르고 간결 |
| 사용 함수 | `nextLine()` + `split()` + `parseInt()` | `nextInt()` |
| 적합 상황 | 입력 유효성 검사, 복합 처리 | 단순 반복 계산 문제 |

---

### 🌱 핵심 개념

- 문자열을 `split(" ")`으로 나누고, `Integer.parseInt()`로 정수 변환
- 테스트 케이스가 여러 줄일 땐 배열에 저장하거나 즉시 처리 방식 선택 가능
- `Scanner.nextLine()` 사용 시 개행 제거를 위해 `nextInt()` 뒤에 `nextLine()` 필수