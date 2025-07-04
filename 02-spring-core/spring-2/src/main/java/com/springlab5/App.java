package com.springlab5;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderController orderController = context.getBean(OrderController.class);
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("🎁 주문 ID를 입력해주세요: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            orderController.placeOrder(id);
        } catch (Exception e) {
            System.out.println("⚠️ 오류 발생: " + e.getMessage());
        }
    }
}