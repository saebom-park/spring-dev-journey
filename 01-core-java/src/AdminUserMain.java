import java.util.Scanner;

public class AdminUserMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("관리자 이름을 입력하세요: ");
        String inputName = scanner.next();

        AdminUser admin = AdminUser.create(inputName);
        admin.printStatus();
    }
}