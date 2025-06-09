// 실습 미션: 날짜 다루는 기본기 연습!
//  - 오늘 날짜 출력
//  - 오늘 기준 3일 전과 5일 후 출력
//  - 특정 날짜(예: 2000년 1월 1일) 생성
//  - "2025-10-31" 문자열을 날짜로 변환 후 출력

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatePractice {
    public static void main(String[] args) {
        // 오늘 날짜 출력
        LocalDate today = LocalDate.now();
        System.out.println("오늘 날짜: " + today);
        
        // 날짜 연산
        LocalDate beforeDay = today.minusDays(3);
        LocalDate afterDay = today.plusDays(5);
        System.out.println("3일 전 날짜: " + beforeDay);
        System.out.println("5일 후 날짜: " + afterDay);
        
        // 특정 날짜 생성
        LocalDate specialDay = LocalDate.of(2000, 1, 1);
        System.out.println("특정 날짜: " + specialDay);

        // 문자열 → 날짜 파싱
        LocalDate parsed = LocalDate.parse("2025-10-31");
        System.out.println("파싱된 날짜: " + parsed);

        // 오늘 요일 출력
        DayOfWeek weekDay = today.getDayOfWeek();
        System.out.println("오늘의 요일은 " + weekDay + "입니다.");

    }
}