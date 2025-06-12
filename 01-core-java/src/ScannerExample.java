import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();    // 공백 포함 한줄 전체 입력
        System.out.println("안녕하세요, " + name + "님!");

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt(); // 숫자 입력
        System.out.println("당신은 " + age + "살이군요!");

        sc.close(); // 사용후 닫기 (습관적으로 익혀두기!)
    }

}