package oop;
import java.util.Scanner;

public class Calculator {

    public static void calcNumber() {
        Scanner scanner = new Scanner(System.in);
        int number1, number2;

        System.out.print("첫번째 숫자를 입력해주세요: ");
        String input1 = scanner.nextLine();
        try {
            number1 = Integer.parseInt(input1);
        } catch (NumberFormatException e) {
            System.out.println("오류 발생: " + e.getMessage());
            System.out.println("계산기를 종료합니다.");
            return;
        }

        System.out.print("두번째 숫자를 입력해주세요: ");
        String input2 = scanner.nextLine();
        try {
            number2 = Integer.parseInt(input2);
        } catch (NumberFormatException e) {
            System.out.println("오류 발생: " + e.getMessage());
            System.out.println("계산기를 종료합니다.");
            return;
        }

        System.out.print("연산자를 입력해주세요: ");
        String operator = scanner.next();

        double result = switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> (double) number1 / number2;
            default -> throw new IllegalArgumentException("유효한 연산자가 아닙니다.");
        };

        System.out.println(number1 + operator + number2 + "=" + result);
    }

    public static void runCalculator() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("계산기를 실행할까요? (Y/N)");
        String yn = scanner.nextLine();

        if (yn.equalsIgnoreCase("y")) {
            System.out.println("계산기 실행중...");
            try {
                calcNumber();
            } catch (IllegalArgumentException e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        } else if (yn.equalsIgnoreCase("n")) {
            System.out.println("계산기를 종료합니다.");
        } else {
            System.out.println("y 또는 n만 입력 가능합니다.");
        }
    }
}