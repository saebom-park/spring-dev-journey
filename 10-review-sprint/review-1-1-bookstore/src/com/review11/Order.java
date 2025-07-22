package com.review11;

public class Order implements Printable {
    private Member member;

    public Order(Member member) {
        this.member = member;
    }

    public void printOrder() {
        System.out.println("[주문 정보]");
        this.printInfo();
        member.getBooks().forEach(book -> {
            book.printInfo();
        });

    }

    @Override
    public void printInfo() {
        System.out.println("구매자: " + member.getName() + " (" + member.getEmail() + ")");
    }
}