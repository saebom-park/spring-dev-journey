import java.util.Scanner;

public class ThrowExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("나눗셈 분자를 입력하세요: ");
        int numerator = scanner.nextInt();

        System.out.print("나눗셈 분모를 입력하세요: ");
        int denominator = scanner.nextInt();

        try {
            int result = divide(numerator, denominator);
            System.out.println("나눗셈 결과: " + result);
        } catch (ArithmeticException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    public static int divide(int a, int b) {
//        if (b == 0) {
//            throw new ArithmeticException("0으로 나눌 수 없습니다.");
//        }
        return a/b;
    }
}
