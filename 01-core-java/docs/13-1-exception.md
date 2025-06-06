# [13단계] 예외 처리 (Exception Handling)

> "프로그램이 죽지 않고 에러를 우아하게 처리하려면?"
> 
> 
> 바로 **예외 처리의 세계**로 들어가야 해!
> 

---

### 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `try { ... } catch` | 에러가 날 수 있는 코드를 시도(try)하고, 에러가 나면 잡아내서(catch) 대처함 |
| `Exception` | 예외 상황을 표현하는 자바의 객체형 클래스 |
| `throw` | 강제로 예외를 발생시키고 싶을 때 사용 |
| `throws` | 해당 메서드가 예외를 던질 수 있다는 것을 선언 |
| `finally` | 에러 발생 여부와 상관없이 **항상 실행되는 블록** |

---

### 🧾 예시 코드

```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // ❌ 0으로 나눔 → ArithmeticException 발생
            System.out.println("결과: " + result);
        } catch (ArithmeticException e) {
            System.out.println("예외 발생! 나누기 0은 안 돼요.");
        } finally {
            System.out.println("무조건 실행되는 finally 블록");
        }

        System.out.println("프로그램 정상 종료");
    }
}
```

---

### 📌 포인트 요약

- `try` 안에서 에러가 나면 `catch`가 처리해줌
- `finally`는 무조건 실행됨 (DB 닫기, 파일 정리 등에 사용)
- `throw`로 직접 예외 던질 수 있음
- 사용자 정의 예외는 `extends Exception`으로 만들 수 있음
- **ArithmeticException**: 덧셈, 뺄셈, 곱셈, 나눗셈 등 산술 계산에서 오류 발생 시 던져지는 예외

---

### 🧪 실습 미션

1. 숫자 두 개를 입력받아 나눗셈을 수행하는 프로그램 작성
    
    → 0으로 나누면 `"0으로는 나눌 수 없습니다"` 출력
    
2. `finally` 블록에서 `"항상 실행되는 코드입니다"` 출력 추가
3. 직접 예외를 `throw` 하는 메서드 만들어보기

---