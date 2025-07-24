package com.review13;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public void printTotalAmount(Member member) {
        System.out.println(member);
        // 피드백 1 수정
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalPoint = 0;

        for (Order order : member.getOrders()) {
            totalPrice += order.getPrice();
            totalDiscountPrice += order.getDiscountPrice(member);
            totalPoint += order.getPoint(member);
            order.printOrderInfo(member);
        }

        System.out.println("- 총 가격: " + totalPrice + "원 / 할인 가격: " + totalDiscountPrice + "원 / 적립 포인트: " + totalPoint);
    }
}