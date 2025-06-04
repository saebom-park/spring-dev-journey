// * 실습 미션: "나이 제한 & 사용자 정의 예외"
//  - 이번엔 '회원가입 시 나이 조건'을 검사하는 로직을 만들어볼 거야!
//  - 잘못된 나이는 아예 예외로 던지고, 정상일 때만 가입되도록 해보자!
// * 실습 목표
//  - "만 14세 미만은 가입이 안 돼요!" → CustomAgeException을 던지자!
// * 실습 조건
// 1. CustomAgeException 클래스를 만들어봐
//  - 기본 생성자: "만 14세 미만은 가입할 수 없습니다."
//  - 메시지 입력 가능한 생성자도 만들기
// 2. SignUpService 클래스 만들기
//  - signUp(String name, int age) 메서드 작성
//  - 나이가 14세 미만이면 예외 던짐 (throw new CustomAgeException())
// 3. SignUpMain 클래스 만들기
//  - 여러 사람의 이름/나이로 테스트
//  - try-catch로 예외 처리하고, 메시지 출력
// * 보너스 미션
//  - 예외 메시지를 이름 포함해서 다르게 출력해봐!
//   예: "봄이는 만 14세 미만이므로 가입할 수 없습니다."

public class CustomAgeException extends Exception {
    public CustomAgeException() {
        super("만 14세 미만은 가입할 수 없습니다.");
    }

    public CustomAgeException(String message) {
        super(message);
    }
}
