// * 실습 미션: 오늘 날짜를 "2025년 06월 10일" 형식으로 출력해보자!
// 1. 오늘 날짜를 "yyyy년 MM월 dd일" 형식으로 포맷하여 출력
// 2. 문자열 "2030-01-01"을 날짜로 파싱하여 출력

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatPractice {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formatted = today.format(formatter);
        System.out.println("오늘 날짜 (포맷): " + formatted);

        String dateStr = "2030-01-01";
        LocalDate parsed = LocalDate.parse(dateStr);
        System.out.println("파싱된 날짜: " + parsed);

        // 추가: 날짜 파싱
        String dateStr2 = "2030/01/01";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate parsed2 = LocalDate.parse(dateStr2, formatter2);
        System.out.println("파싱된 날짜2: " + parsed2);
    }

}