// * 실습 미션: 모든 회원의 총 소비 금액 추적하기
// 1. Member 클래스를 만들자
//  - String name : 인스턴스 변수
//  - int personalSpent : 인스턴스 변수 (개인 소비 금액)
//  - static int totalSpent : 클래스 전체 누적 소비 금액
// 2. spend(int amount) 메서드를 만들자
//  - 개인 소비금액에 더하고
//  - totalSpent에도 누적되도록 작성
// 3. 출력 메서드 printStatus()에서
//  - 이름 + 개인 소비 + 총 소비 출력

public class Member {
    String name;
    int personalSpent;
    static int totalSpent;

    Member(String name, int firstSpent) {
        this.name = name;
//        this.personalSpent = personalSpent; // (1) 초기값을 반영 후
        spend(firstSpent); // (수정1) 생성자에서 초기 값 반영
    }

    public void spend(int amount) {
        personalSpent += amount;    // (2) 같은값을 다시 더함
        totalSpent += amount;
    }

    public void printStatus() {
        System.out.print("name: " + name + ", ");
        System.out.print("personalSpent: " + personalSpent + ", ");
        System.out.println("totalSpent: " + totalSpent);
    }

    public static void main(String[] args) {
        Member m1 = new Member("봄이", 1000);
        Member m2 = new Member("온이", 4000);
        Member m3 = new Member("지피티", 2500);

//        m1.spend(m1.personalSpent);
//        m2.spend(m2.personalSpent);
//        m3.spend(m3.personalSpent);

        m1.spend(2500);
        m2.spend(3000);
        m3.spend(500);

        m1.printStatus();
        m2.printStatus();
        m3.printStatus();
    }
}
