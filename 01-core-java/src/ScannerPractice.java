// * 실습 미션
// - 사용자에게 이름과 나이를 입력받고 출력
// - 사용자에게 사는 도시 이름을 입력받고 출력
import java.util.Scanner;

public class ScannerPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("나이를 입력하세요: ");
        int age = scanner.nextInt();

        scanner.nextLine(); // 엔터 처리용 (int 후 개행 처리 필수)

        System.out.println(name + "님의 나이는 " + age + " 세 입니다.");

        System.out.print("사는 도시를 입력하세요: ");
        String addr = scanner.nextLine();
        System.out.println(addr + "에 거주하는 " + name + "님, 환영합니다!");
        scanner.close();    // 자원 정리 습관
    }
}