# [13-2단계] 실습: 예외를 throw하고, 입력받아 처리하기

---

### 💡 핵심 개념 요약

| 키워드 | 뜻 |
| --- | --- |
| `Scanner` | 사용자 입력을 받을 수 있는 클래스 |
| `throw` | **직접 예외를 "던져서" 발생시킨다**는 뜻 |
| `throws` | **이 메서드는 예외를 던질 수 있어요~** 라고 선언하는 역할 |

---

### 🧾 예시 코드: 입력받고, 직접 예외 `throw` 하기

```java
import java.util.Scanner;

public class ThrowExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("나눗셈 분자를 입력하세요: ");
        int numerator = scanner.nextInt();

        System.out.print("나눗셈 분모를 입력하세요: ");
        int denominator = scanner.nextInt();

        try {
            int result = divide(numerator, denominator);
            System.out.println("나눗셈 결과: " + result);
        } catch (ArithmeticException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 분모가 0이면 예외를 '직접' 던진다!
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("0으로는 나눌 수 없습니다!");
        }
        return a / b;
    }
}
```

---

### 📌 포인트 요약

- `Scanner`는 `System.in`에서 값을 입력받는 도구야!
- `throw new ArithmeticException(...)`
    
    → 개발자가 직접 **예외를 강제로 던지는** 문장이야!
    
    → “나 이 상황에서 더 이상 못해요!!” 하고 프로그램이 **스스로 포기하는 느낌**
    
- `catch`에서 그걸 받아서 `"괜찮아~"` 하고 처리해주는 거야 😊

---

## 🧾 코드 문장 이해표

| 코드 문장 | 무슨 뜻이야? | 한 줄 요약 느낌 |
| --- | --- | --- |
| `Scanner scanner = new Scanner(System.in);` | 사용자(나)한테 입력을 받을 준비를 하는 코드야. `scanner`라는 이름으로 입력 도구를 만든 거야. | **“입력 도구 만들어!”** |
| `System.out.print("나눗셈 분자를 입력하세요: ");` | 사용자한테 "숫자 입력하라"는 메시지를 화면에 보여줘 (줄바꿈 없음). | **“이거 입력하라고 말해줘!”** |
| `int numerator = scanner.nextInt();` | 방금 만든 `scanner`를 통해서 숫자 하나 입력받고 `numerator`라는 변수에 저장해. | **“입력값 저장해!”** |
| `try { ... } catch (ArithmeticException e) { ... }` | 이 코드 안에서 문제 생길 수도 있으니까, 조심해서 실행해보고, 에러 나면 `catch` 안으로 점프해서 대신 처리해줘. | **“문제 생길 수도 있어 → 대비해!”** |
| `int result = divide(numerator, denominator);` | `divide()`라는 함수한테 숫자 두 개 던져서 계산시키고 결과를 `result`에 저장해. | **“계산해줘 → 결과 저장해!”** |
| `throw new ArithmeticException("0으로 나눌 수 없습니다!");` | "야 이건 진짜 위험해!"라고 판단하고, 내가 직접 에러를 만들어서 위에 던지는 코드야. | **“이건 못하겠다! 에러 던져!!”** |
| `System.out.println("예외 발생: " + e.getMessage());` | catch 블록에서 받은 에러 메시지를 화면에 보여줘. | **“에러 메세지 출력해!”** |
| `return a / b;` | 문제 없으면 a를 b로 나눠서 결과를 되돌려줘. | **“정상** |