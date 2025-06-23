# Day03 | 시험 성적

> [🔗 백준 - 시험 성적](https://www.acmicpc.net/problem/9498)
> 

---

### 💡 개념 요약

- 조건문으로 점수 구간을 분기해서 학점(A~F)을 출력
- `Scanner.nextInt()`로 입력 받고 `System.out.println()`으로 등급 출력
- 백준은 **정확한 출력 형식** 요구 → `"A"` 등 단독 출력만 허용
- `Main` 클래스 필수 (`GradeEvaluator` ❌)

---

### 🧾 예시 코드

**내 풀이 (if-else 구조)**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();

        String grade;
        if (score >= 90 && score <= 100) {
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
        System.out.println(grade);
    }
}
```

**온이 풀이 (메서드 분리 + 리팩토링)**

```java
import java.util.Scanner;

public class Main {
    public static String getGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        System.out.println(getGrade(score));
    }
}
```

---

### 📌 비교 포인트

| 항목 | 봄이 풀이 | 온이 풀이 |
| --- | --- | --- |
| 조건 처리 | `if-else` 구조 | `return`으로 간결화 |
| 출력 방식 | `System.out.println(grade)` | `System.out.println(getGrade(score))` |
| 범위 명시 | `score <= 100` 포함 | 100 이상 입력을 허용 |
| 유지보수 | 직관적 | 테스트 분리 용이 |

---

### 🌱 핵심 개념

- 점수 조건은 문제에서 요구한 **모든 구간** 포함해야 함
- 출력은 `"A"` 등 **학점만 단독 출력** (문구 불가)
- 클래스명은 반드시 `Main` (백준 전용 규칙)
- `if-else`는 좁은 조건부터 위에, 넓은 조건은 아래에 위치시키기