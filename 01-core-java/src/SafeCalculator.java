// * 실습 미션: "숫자 계산기 + 예외 처리"
// * 요구사항:
// 1. 사용자에게 연산 종류를 먼저 입력받아 (예: +, -, *, /)
// 2. 숫자 두 개를 입력받아 해당 연산을 수행
// 3. 다음 예외를 처리해야 함:
//  - 0으로 나누기 시 예외 발생 처리
//  - 잘못된 연산자 입력 시 예외 직접 발생시키기

import java.util.Objects;
import java.util.Scanner;

public class SafeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("연산 종류를 입력하세요 (예: +, -, *, /): ");
        String operator = scanner.next();
        System.out.print("첫번째 숫자를 입력하세요: ");
        double numeric1 = scanner.nextDouble();
        System.out.print("두번째 숫자를 입력하세요: ");
        double numeric2 = scanner.nextDouble();

        try {
            double result = calculator(operator, numeric1, numeric2);
            System.out.print("결과: ");
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            System.out.println("error: " + e2.getMessage());
        } finally {
            System.out.println("프로그램을 종료합니다.");
        }



    }

    public static double calculator(String oper, double n1, double n2) {
        double returnValue;

        if (Objects.equals(oper, "+")) {
            returnValue = n1 + n2;
        } else if (Objects.equals(oper, "-")) {
            returnValue = n1 - n2;
        } else if (Objects.equals(oper, "*")) {
            returnValue = n1 * n2;
        } else if (Objects.equals(oper, "/")) {
            if (n2 == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            else {
                returnValue = n1 / n2;
            }
        }
        else {
            throw new IllegalArgumentException("유효하지 않은 연산자 입니다.");
        }

        return returnValue;
    }
}
