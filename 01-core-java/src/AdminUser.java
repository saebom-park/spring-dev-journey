// * 실습 미션: `AdminUser` 객체를 static 방식으로만 생성하기
// 1. `AdminUser` 클래스 만들기
//  - `String name`, `String role` 멤버 변수
//  - 생성자는 `private`으로 막기
// 2. static 메서드 `create(String name)` 작성
//  - `role`은 자동으로 `"ADMIN"`으로 설정
// 3. `AdminUserMain`에서 객체 생성하고, 정보 출력하기
//  - 객체는 `AdminUser.create("봄이")`처럼 생성해야 함
// 4. 직접 `new AdminUser()`는 불가능해야 함!

public class AdminUser {
    String name;
    static String role;

    private AdminUser(String name) {
        // 외부에서 생성 못함!
        this.name = name;
    }

    static {
        role = "ADMIN";
    }

    public static AdminUser create(String name) {
        return new AdminUser(name);
    }

    public void printStatus() {
        System.out.println(name + "님의 관리자 계정이 생성 되었습니다. 역할은 " + role + "입니다.");
    }
}