package oop;

// 자식 클래스
public class Admin extends Member {
    @Override
    public void showRole() {
        System.out.println("관리자입니다.");
    }
    
    public void manageSystem() {
        System.out.println("시스템을 관리합니다.");
    }
    
    public static void main(String[] args) {
        Member m = new Admin(); // 업캐스팅
        m.showRole();

        //m.manageSystem(); // Member 타입에는 없는 메서드이므로 오류!

        if (m instanceof Admin) {
            Admin admin = (Admin) m; // 다운 캐스팅
            admin.manageSystem();
        }
    }
}