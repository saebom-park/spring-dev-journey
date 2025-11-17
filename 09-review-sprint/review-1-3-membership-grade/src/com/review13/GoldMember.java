package com.review13;

public class GoldMember extends Member {

    // constructor
    public GoldMember(String name, String email) {
        super(name, email);
        setMembershipGrade(MembershipGrade.GOLD);
    }

    // abstract method
    public double getDiscountRate() { return 0.1; };
    public double getPointRate() { return 0.15; }

}