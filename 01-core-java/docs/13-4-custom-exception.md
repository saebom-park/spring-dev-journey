# [13-4단계] 사용자 정의 예외 & throws 흐름 정리

> ❓ “내가 직접 만든 예외를 던지려면?”
> 
> 
> ✅ **“사용자 정의 예외 클래스 + throws로 위임!”**
> 

---

## 💡 핵심 개념 요약

| 구분 | 설명 |
| --- | --- |
| 사용자 정의 예외 | `Exception` 또는 `RuntimeException`을 상속하여 개발자가 만드는 예외 클래스 |
| 생성자 | 기본 생성자와 메시지를 받는 생성자를 주로 정의함 |
| throw | 예외 객체를 명시적으로 던질 때 사용 |
| throws | 예외를 호출한 쪽에 위임할 때 메서드 선언부에 사용 |
| 예외 흐름 | `throw`로 던짐 → `throws`로 위임 → `try-catch` 또는 재위임 처리 |

---

## 🧾 예시 코드

### 1. 사용자 정의 예외 클래스 (`CustomLoginException.java`)

```java
public class CustomLoginException extends Exception {
    public CustomLoginException() {
        super("로그인에 실패했습니다.");
    }

    public CustomLoginException(String message) {
        super(message);
    }
}
```

### 2. 로그인 검증기 (`LoginService.java`)

```java
public class LoginService {
    public void login(String username, String password) throws CustomLoginException {
        if (!"spring".equals(username) || !"1234".equals(password)) {
            throw new CustomLoginException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        System.out.println("로그인 성공!");
    }
}
```

### 3. 실행부 (`LoginMain.java`)

```java
public class LoginMain {
    public static void main(String[] args) {
        LoginService service = new LoginService();
        try {
            service.login("spring", "wrongpw");
        } catch (CustomLoginException e) {
            System.out.println("❗ 예외 발생: " + e.getMessage());
        }
    }
}
```

---

## 📌 포인트 요약

- 사용자 정의 예외는 `Exception` 또는 `RuntimeException`을 상속해서 만들 수 있어.
- `throws`는 던질 수 있는 예외를 명시하는 약속표야.
- 실제 예외 발생은 `throw new 예외객체()`로 이루어져.
- 호출부는 try-catch로 받아주거나, 자신도 throws로 다시 위임할 수 있어.
- 실무에서는 **도메인 상황에 맞는 예외를 직접 정의해서 명확한 에러 흐름**을 설계해!

---

## 🧪 실습 미션: **"나이 제한 & 사용자 정의 예외"**

이번엔 **회원가입 시 나이 조건**을 검사하는 로직을 만들어볼 거야!

잘못된 나이는 아예 예외로 던지고, 정상일 때만 가입되도록 해보자! 👇

---

### 📘 실습 목표

> ❓ "만 14세 미만은 가입이 안 돼요!"
> 
> 
> → `CustomAgeException`을 던지자!
> 

---

### ✨ 실습 조건

1. `CustomAgeException` 클래스를 만들어봐
    - 기본 생성자: `"만 14세 미만은 가입할 수 없습니다."`
    - 메시지 입력 가능한 생성자도 만들기
2. `SignUpService` 클래스 만들기
    - `signUp(String name, int age)` 메서드 작성
    - 나이가 14세 미만이면 예외 던짐 (`throw new CustomAgeException()`)
3. `SignUpMain` 클래스 만들기
    - 여러 사람의 이름/나이로 테스트
    - `try-catch`로 예외 처리하고, 메시지 출력

---

### 📌 보너스 미션

- 예외 메시지를 `이름` 포함해서 다르게 출력해봐!
    
    > 예: "봄이는 만 14세 미만이므로 가입할 수 없습니다."
    >