// * 실습 미션 2
// 버전 1: 기본 생성자 사용
// 1. `BasicAdminUser` 클래스 만들기
//  - `String name` 필드 선언
//  - `private BasicAdminUser()` 기본 생성자 사용
//  - static 메서드 `create(String name)` 만들기 → name 수동 설정
// 2. `BasicAdminUserMain` 클래스에서 테스트
//  - `create("봄이")`로 객체 생성
//  - `printStatus()`에서 이름 출력

public class BasicAdminUser {
    String name;
    
    private BasicAdminUser() {
        // 외부에서 생성 불가
    }

    public static BasicAdminUser create(String name) {
        BasicAdminUser bAdmin = new BasicAdminUser();
        bAdmin.name = name;

        return bAdmin;
    }

    public void printStatus() {
        System.out.println(name + "님의 등급은 관리자(ADMIN) 입니다.");
    }
}