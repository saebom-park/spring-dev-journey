package com.review13;

import java.util.ArrayList;
import java.util.List;

public class MembershipService {
    public static int memberCnt = 0;
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
        memberCnt ++;
    }

    public void printAllBenefit() {
        // 피드백 5 수정
        System.out.println("총 회원수: " + memberCnt + "명");
        members.forEach(Member::printBenefitInfo);
    }
}