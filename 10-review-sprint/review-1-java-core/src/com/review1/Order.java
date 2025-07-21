package com.review1;

public class Order {
    private Member member;

    public Order(Member member) {
        this.member = member;
    }

    // getter
    public Member getMember() { return member; }

    public String getOrder() {
        return member.getName() + "님의 주문 내역" +
                member.getBooks();

    }
}