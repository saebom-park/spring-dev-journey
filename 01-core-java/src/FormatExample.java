import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("오늘 날짜: " + today);
        
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        // 날짜 → 문자열 포맷
        String formatted = today.format(formatter);
        System.out.println("오늘 날짜 (포맷): " + formatted);

        // 문자열 → 날짜 파싱
        String dateStr = "2025-08-18";
        LocalDate parsed = LocalDate.parse(dateStr); // ISO 기본 형식이라 formatter 없이 도 가능
        System.out.println("파싱된 날짜: " + parsed);

        // 문자열이 날짜 기본 형식이 아닌 경우
        // DateTimeFormatter 필요
        String dateStr2 = "2025.08.18";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate parsed2 = LocalDate.parse(dateStr2, formatter2);
        System.out.println("파싱된 날짜2: " + parsed2);

    }
}