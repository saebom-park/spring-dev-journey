# [13-3단계] throws 키워드

> “예외를 나 혼자 처리 못 하겠을 땐?”
> 
> 
> 👉 바로 `throws`로 **호출한 쪽에게 책임을 넘기는 선언**!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `throw` | 예외를 실제로 던짐 (예: `throw new IOException(...)`) |
| `throws` | 이 메서드는 예외를 던질 수 있다고 미리 알리는 선언 |
| `IOException` | 입출력 관련 예외. 파일, 콘솔 입력 등에서 자주 발생 |
| 예외 위임 |  `throws`로 호출한 쪽(`main`)에 예외 처리를 맡김 |
| 처리 방식 | 위임받은 쪽에서 `try-catch`로 예외를 받아서 처리 |

---

### 🧾 예시 코드

```java
import java.io.IOException;

public class ThrowsExample {
    public static void main(String[] args) {
        try {
            checkAge(15); // 예외를 위임받은 쪽에서 처리
            System.out.println("입장 허가!");
        } catch (IOException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 이 메서드는 예외를 던질 수 있음
    public static void checkAge(int age) throws IOException {
        if (age < 18) {
            throw new IOException("18세 미만은 입장할 수 없습니다.");
        }
    }
}
```

---

### 📌 포인트 요약

- `throws`는 **예외를 내가 직접 처리하지 않고, 호출자에게 넘기는 선언**
- `throw`는 예외를 **실제로 발생시키는 동작**
- `IOException`처럼 자바가 제공하는 예외 타입 사용 가능
- **예외를 위임받은 쪽은 반드시 try-catch로 처리해야 함**
- 실무에서는 **파일 입출력, 네트워크 통신 등에서 throws 많이 사용함**

---

### 🧪 실습 미션

> 🎯 목표: throws 키워드로 예외를 위임해보자!
> 
1. `checkLogin(String id)` 메서드를 만들고,
    
    → `id`가 `"admin"`이 아니면 `throw new IllegalArgumentException(...)`
    
    → `throws IllegalArgumentException` 선언 포함
    
2. `main()`에서 `checkLogin()` 호출 후,
    
    → `try-catch`로 예외 처리
    
3. 정상 로그인 시 `"접속 성공!"` 출력
    
    예외 발생 시 `"접속 실패: 관리자만 접속 가능"` 출력