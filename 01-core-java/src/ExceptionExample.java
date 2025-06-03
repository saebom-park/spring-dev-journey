public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 0으로 나눔 → 산술 연산(ArithmeticException) 발생
            System.out.println("결과: " + result);
        } catch (ArithmeticException e) {
            System.out.println("예외 발생! 나누기 0은 안돼요.");
        } finally {
            System.out.println("무조건 실행되는 finally 블록");
        }
        
        System.out.println("프로그램 정상 종료");
    }
}
