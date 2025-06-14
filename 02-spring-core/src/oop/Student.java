package oop;
import java.util.Scanner;

public class Student {
    private String name;
    private int grade;
    private int score;

    public Student(String name, int grade, int score) {
        this.name = name;
        this.grade = grade;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Student bomi = new Student("봄이", 2, 50);
        Student oni = new Student("온이", 2, 50);

        Scanner scanner = new Scanner(System.in);
        int[] arrScores = new int[2];
        for (int i = 0; i < arrScores.length; i++) {
            System.out.print("점수를 입력해주세요: ");
            arrScores[i] = scanner.nextInt();
        }

        bomi.setScore(arrScores[0]);
        oni.setScore(arrScores[1]);

        int scoreDiff = oni.getScore() - bomi.getScore();

        System.out.print("이름: " + bomi.getName());
        System.out.print(", 학년: " + bomi.getGrade());
        System.out.println(", 점수: " + bomi.getScore());
        System.out.print("이름: " + oni.getName());
        System.out.print(", 학년: " + oni.getGrade());
        System.out.println(", 점수: " + oni.getScore());

        if (scoreDiff > 0) System.out.println(oni.getName() + "가 " + scoreDiff + "점 차이로 1등입니다.");
        else if (scoreDiff < 0) System.out.println(bomi.getName() + "가 " + Math.abs(scoreDiff) + "점 차이로 1등입니다.");
        else System.out.println(oni.getName() + "와 " + bomi.getName() + "는 " + oni.getScore() + "점으로 공동 1등 입니다.");
    }
}