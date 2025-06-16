package oop;
import java.util.Scanner;

public class SmartPhone extends Phone {
    @Override
    public void powerOn() {
        System.out.println("스마트폰 전원을 켭니다. 지문 인식을 시작합니다.");
    }

    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("앱을 실행할까요? (Y/N)");
        String yn = scanner.next();
        if (yn.equalsIgnoreCase("Y")) {
            System.out.println("앱을 실행합니다. 잠시만 기다려 주세요...");
        } else {
            System.out.println("앱을 종료합니다.");
        }
    }
}