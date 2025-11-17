package com.review13;

import java.util.ArrayList;
import java.util.List;

public abstract class Member implements BenefitPrintable {
    private String name;
    private String email;
    private MembershipGrade membershipGrade;
    private List<Order> orders = new ArrayList<>();

    // constructor
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter
    public String getName() { return name; }
    public String getEmail() { return email; }
    public MembershipGrade getMembershipGrade() { return membershipGrade; }
    public List<Order> getOrders() { return orders; }

    // setter
    public void setMembershipGrade(MembershipGrade membershipGrade) { this.membershipGrade = membershipGrade; }

    // abstract method
    abstract double getDiscountRate();
    abstract double getPointRate();

    @Override
    public String toString() {
        return "[회원 정보]\n- " + name + " (" + email + ") / " + membershipGrade;
    }

    @Override
    public void printBenefitInfo() {
        System.out.println(this);
        System.out.println("[" + membershipGrade + " 회원 혜택 정보]");
        System.out.println("- 결제 시 할인: " + getDiscountRate() + "%");
        System.out.println("- 포인트 적립: " + getPointRate() + "%");
    }

    public void addOrder(Order order) throws InvalidAmountException {
        if (order.getPrice() <= 0) {
            throw new InvalidAmountException();
        }
        getOrders().add(order);
    }
}