package oop;
import java.util.Scanner;

public class SmartWatch implements Camera, MusicPlayer {
    @Override
    public void takePhoto() {
        System.out.println("카메라 실행중...");
    }

    @Override
    public void playMusic() {
        System.out.println("음악 실행중...");
    }

    public static void main(String[] args) {
        SmartWatch watch = new SmartWatch();
        Scanner scanner = new Scanner(System.in);
        System.out.print("실행할 앱의 숫자를 입력해주세요 (1: 카메라, 2: 뮤직플레이어, 3: 계산기):");
        String inputNum = scanner.next();
        scanner.nextLine();

        int appNum = 0;
        try {
            appNum = Integer.parseInt(inputNum);
        } catch (NumberFormatException e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
        switch (appNum) {
            case 1 -> watch.takePhoto();
            case 2 -> watch.playMusic();
            case 3 -> Calculator.runCalculator();
            default -> System.out.println("실행할 앱이 없어 종료합니다.");
        }
    }
}