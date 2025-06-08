import java.util.Scanner;

public class BetterAdminUserMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("관리자 이름을 입력하세요: ");
        String inputName = scanner.nextLine();

        BetterAdminUser admin = BetterAdminUser.create(inputName);
        admin.printStatus();
    }
}