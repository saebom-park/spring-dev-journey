package com.review13;

public class PlatinumMember extends Member {

    // constructor
    public PlatinumMember(String name, String email) {
        super(name, email);
        setMembershipGrade(MembershipGrade.PLATINUM);
    }

    // abstract method
    public double getDiscountRate() { return 0.2; }
    public double getPointRate() { return 0.25; }

}