import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateExample {
    public static void main(String[] args) {
        // 현재 날짜와 시간
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("오늘 날짜: " + today);
        System.out.println("현재 시간: " + now);

        // 날짜 연산
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("내일 날짜: " + tomorrow);
        System.out.println("어제 날짜: " + yesterday);

        // 특정 날짜 생성
        LocalDate birthDay = LocalDate.of(1996, 8, 18);
        System.out.println("생일: " + birthDay);

        // 문자열 → 날짜 파싱
        LocalDate parsed = LocalDate.parse("2025-06-09");
        System.out.println("파싱된 날짜: " + parsed);
    }
}