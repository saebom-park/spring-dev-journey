import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDiffExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2025, 8, 18);

        // Period로 차이 구하기
        Period diff = Period.between(today, birthday);
        int diffYear = diff.getYears();
        int diffMonth = diff.getMonths();
        int diffDay = diff.getDays();

        String diffTxt = "생일까지";
        if (diffYear > 0) {
            diffTxt += " " + diffYear + "년";
        }
        if (diffMonth > 0) {
            diffTxt += " " + diffMonth + "개월";
        }
        if (diffDay > 0) {
            diffTxt += " " + diffDay + "일";
        }
        diffTxt += " 남았습니다.";
        System.out.println(diffTxt);

        // ChronoUnit으로 일수 계산
        long days = ChronoUnit.DAYS.between(today, birthday);
        long months = ChronoUnit.MONTHS.between(today, birthday);
        System.out.println("생일까지 " + days + "일 남았습니다.");
        System.out.println("생일까지 " + months + "개월 남았습니다.");
    }
}