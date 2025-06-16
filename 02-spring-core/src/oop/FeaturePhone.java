package oop;
import java.util.Scanner;

public class FeaturePhone extends Phone {
    @Override
    public void powerOn() {
        System.out.println("피쳐폰 전원을 켭니다. 키패드를 초기화합니다.");
    }

    public void useKeypad() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("키패드를 사용 중입니다. 번호를 입력하세요: ");
        String number = scanner.nextLine();
        System.out.println("입력하신 번호는 " + number + "입니다. 해당 번호로 전화를 연결할까요? (Y/N)");
        String yn = scanner.next();
        if (yn.equalsIgnoreCase("Y")) {
            System.out.println("입력하신 번호로 전화를 연결합니다...");
        } else if (yn.equalsIgnoreCase("N")) {
            System.out.println("전화 연결을 취소합니다.");
        } else {
            System.out.println("잘못 입력하셨습니다. 전화 연결을 취소합니다.");
        }
    }
}