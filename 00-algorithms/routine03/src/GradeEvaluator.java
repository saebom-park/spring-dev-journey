import java.util.Scanner;

public class GradeEvaluator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        scanner.nextLine();

        String grade;
        if (score >= 90 && score <= 100) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println(grade);
    }
}