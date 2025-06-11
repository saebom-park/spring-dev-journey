// * 실습 미션
// 1. 오늘과 생일(2025-12-25) 사이 차이 계산
// 2. Period로 월/일 차이 출력
// 3. ChronoUnit으로 총 며칠 남았는지 출력

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDiffPractice {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate christmas = LocalDate.of(2025, 12, 25);

        Period diff = Period.between(today, christmas);
        int diffYear = diff.getYears();
        int diffMonth = diff.getMonths();
        int diffDay = diff.getDays();
        String diffTxt = "크리스마스까지";

        if (diffYear > 0) diffTxt += " " + diffYear + "년";
        if (diffMonth > 0) diffTxt += " " + diffMonth + "개월";
        if (diffDay > 0) diffTxt += " " + diffDay + "일";
        diffTxt += " 남았습니다.";
        System.out.println(diffTxt);

        long days = ChronoUnit.DAYS.between(today, christmas);
        System.out.println("크리스마스까지 " + days + "일 남았습니다.");
    }
}