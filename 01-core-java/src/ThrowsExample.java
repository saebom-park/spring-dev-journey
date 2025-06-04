import java.io.IOException;

public class ThrowsExample {
    public static void main(String[] args) {
        try {
            checkAge(15); // 예외를 위임받은 쪽에서 처리
            System.out.println("입장 허가!");
        } catch (IOException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    public static void checkAge(int age) throws IOException {
        if (age < 18) {
            throw new IOException("18세 미만은 입장할 수 없습니다.");
        }
    }
}
