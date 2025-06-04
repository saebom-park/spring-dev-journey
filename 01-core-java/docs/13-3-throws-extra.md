# [13-3단계] 추가 개념 정리

### ✅ 주제: Checked Exception vs Unchecked Exception

---

### 💡 핵심 개념 요약

| 구분 | Checked Exception ✅ | Unchecked Exception ❌ |
| --- | --- | --- |
| 검사 시점 | 컴파일 시점 | 런타임 시점 |
| `throws` 선언 | **반드시 필요** | 선택사항 (생략 가능) |
| 상속 구조 | `Exception` (단, `RuntimeException` 제외) | `RuntimeException` 또는 그 하위 클래스 |
| 예시 | `IOException`, `SQLException`, `IllegalAccessException` | `NullPointerException`, `ArithmeticException`, `IllegalArgumentException` |
| 실무 특징 | 파일, DB, 네트워크 등 **외부 자원 다룰 때 자주 사용** | 로직 오류, 잘못된 값 등 **코드 내부 문제 처리 시 자주 사용** |

---

### 🧾 예시 코드 1 — Checked Exception

```java
import java.io.FileReader;
import java.io.IOException;

public class CheckedExample {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("test.txt"); // 파일이 없으면 컴파일 에러 유도
        reader.read();
    }
}
```

✅ `IOException`은 **반드시 `throws`로 위임하거나 try-catch로 처리해야 컴파일 가능**

---

### 🧾 예시 코드 2 — Unchecked Exception

```java
public class UncheckedExample {
    public static void main(String[] args) {
        int result = 10 / 0; // ArithmeticException 발생
        System.out.println("결과: " + result);
    }
}
```

✅ `throws` 없이도 컴파일 OK!

→ **실행 도중** 나누기 0이 발생할 경우 예외 발생

---

### 📌 포인트 요약

- Checked Exception은 반드시 **예외 처리 or 위임** 필요 (컴파일러가 강제함)
- Unchecked Exception은 선택적으로 처리 가능 (개발자의 판단에 맡김)
- 실무에서는 **Checked는 외부 자원**, **Unchecked는 내부 로직**에 주로 사용