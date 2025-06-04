// * 실습 미션: "입장 가능 나이 확인기" 만들기
//throws 키워드로 예외 위임 연습 + 조건 만족 시 입장 허용 / 미만 시 예외 발생
// * 요구사항
// 1. checkEntrance(int age) 메서드 작성
//  - age가 19세 미만이면
//   → throw new IllegalAccessException("19세 이상만 입장 가능")
//   → 선언부에 throws IllegalAccessException 포함
// 2. main()에서 사용자 나이를 직접 지정해 테스트
//  - try-catch로 예외 처리
// 3. 출력 조건
//  - 입장 가능 → "입장 가능! 즐거운 시간 되세요 :)"
//  - 입장 불가 → "입장 불가: [예외 메시지]"

public class AgeGateChecker {
    public static void main(String[] args) {
        try {
            checkEntrance(30);
            System.out.println("입장 가능! 즐거운 시간 되세요 :)");
        } catch  (IllegalAccessException e) {
            System.out.println("입장 불가: " + e.getMessage());
        }
    }
    public static void checkEntrance(int age) throws IllegalAccessException {
        if (age < 19) {
            throw new IllegalAccessException("19세 이상만 입장 가능");
        }
    }
}
