package com.review13;

public class Main {
    public static void main(String[] args) {
        Member m1 = new SilverMember("봄이", "spring@dev.com");
        Member m2 = new GoldMember("온이", "oni@dev.com");
        Member m3 = new PlatinumMember("여름이", "summer@dev.com");

        MembershipService membershipService = new MembershipService();
        membershipService.addMember(m1);
        membershipService.addMember(m2);
        membershipService.addMember(m3);

        membershipService.printAllBenefit();

        Order o1 = new Order("상품1", 15000);
        Order o2 = new Order("상품2", 17500);
        Order o3 = new Order("상품3", 22500);

        m1.addOrder(o1);
        m1.addOrder(o2);
        m1.addOrder(o3);

        m2.addOrder(o1);
        m2.addOrder(o2);
        m2.addOrder(o3);

        m3.addOrder(o1);
        m3.addOrder(o2);
        m3.addOrder(o3);

        OrderService orderService = new OrderService();
        orderService.printTotalAmount(m1);
        orderService.printTotalAmount(m2);
        orderService.printTotalAmount(m3);
    }
}

/* 🔍 피드백 요약
1. OrderService 내부 누적 변수는 지역변수로 전환하자!
   공유 필드로 만들면 나중에 상태 꼬일 위험이 있어.

2. Order.getDiscountPrice() 계산식은 중복되므로,
   할인율 변수로 분리해 가독성을 높이자.

3. Member.toString()과 printBenefitInfo() 출력 구조는 깔끔하고 실무적으로도 훌륭함!

4. Member에 포인트 누적 필드를 추가하면
   장기적으로 적립 포인트 기능을 확장할 수 있음.

5. MembershipService.memberCnt는 선언만 있고 활용이 없어!
   추후 출력용 메서드와 연결하거나 없애도 무방함.
*/