package oop;
import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private String name;
    private String movie;
    private int ticketCnt;
    private LocalDate bookingDate;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBookingInfo() {
        String bookingInfo;
        if (ticketCnt > 0) {
            bookingInfo = "영화 이름: " + movie + ", 티켓수: " + ticketCnt + ", 예매일: " + bookingDate;
        } else {
            bookingInfo = "예매 내역 없음.";
        }
        return bookingInfo;
    }

    public void bookMovie() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("예매할 영화를 입력해 주세요: ");
            movie = scanner.nextLine();
            System.out.print("티켓수를 입력해 주세요: ");
            int i = 0;
            while (i < 3) {
                ticketCnt = scanner.nextInt();
                if (ticketCnt == 0) {
                    System.out.println("최소 1장 이상 입력해주세요.");
                    i++;
                } else {
                    break;
                }
                System.out.println("입력 오류입니다. 처음부터 다시 진행해주세요.");
            }
            bookingDate = LocalDate.now();
            System.out.println("- 예매 영화: " + movie);
            System.out.println("- 티켓 수: " + ticketCnt + "장");
            System.out.println(name + "님의 예매를 요청 중입니다.");


        } catch (ArithmeticException e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

}