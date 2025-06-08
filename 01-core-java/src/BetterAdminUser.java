// * 실습 미션 2
// 버전 2: 매개변수 생성자 사용
// 1. `BetterAdminUser` 클래스 만들기
//  - `String name` 필드 선언
//  - `private BetterAdminUser(String name)` 생성자 작성
//  - static 메서드 `create(String name)`에서 name을 생성자에 바로 전달
// 2. `BetterAdminUserMain` 클래스에서 테스트
//  - `create("온이")`로 객체 생성
//  - `printStatus()`에서 이름 출력

public class BetterAdminUser {
    String name;

    private BetterAdminUser(String name) {
        // 외부에서 생성 불가
        this.name = name;
    }

    public static BetterAdminUser create(String name) {
        return new BetterAdminUser(name);
    }

    public void printStatus() {
        System.out.println(name + "님의 등급은 관리자(ADMIN) 입니다.");
    }
}