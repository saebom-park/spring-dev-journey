package oop;
import java.util.Scanner;

public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("원을 그립니다.");
    }
    public void radiusInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("원의 반지름을 입력하세요(cm): ");
        int radius = scanner.nextInt();
        scanner.nextLine();

        System.out.println("원의 반지름은 " + radius + "cm 입니다.");
    }
}