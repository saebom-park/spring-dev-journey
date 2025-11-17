package com.review13;

public class SilverMember extends Member {

    // constructor
    public SilverMember(String name, String email) {
        super(name, email);
        setMembershipGrade(MembershipGrade.SILVER);
    }

    // abstract method
    public double getDiscountRate() { return 0.05; }
    public double getPointRate() { return 0.1; }
}