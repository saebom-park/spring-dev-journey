// * 실습 미션: "throw 키워드로 예외 위임하기"
// 1. `checkLogin(String id)` 메서드를 만들고,
//  → `id`가 `"admin"`이 아니면 `throw new IllegalArgumentException(...)`
//  → `throws IllegalArgumentException` 선언 포함
// 2. `main()`에서 `checkLogin()` 호출 후,
//  → `try-catch`로 예외 처리
// 3. 정상 로그인 시 `"접속 성공!"` 출력
//  → 예외 발생 시 `"접속 실패: 관리자만 접속 가능"` 출력

import java.util.Objects;

public class LoginValidator {
    public static void main(String[] args) {
        try {
            checkLogin("spring");
            System.out.println("접속 성공!");
        } catch (IllegalArgumentException e) {
            System.out.println("접속 실패: " + e.getMessage());
        }
    }

    public static void checkLogin(String id) throws IllegalArgumentException {
        if (!Objects.equals(id, "admin")) {
            throw new IllegalArgumentException("관리자만 접속 가능");
        }
    }
}
