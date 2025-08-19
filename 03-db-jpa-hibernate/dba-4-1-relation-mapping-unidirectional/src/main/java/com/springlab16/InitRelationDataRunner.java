package com.springlab16;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitRelationDataRunner implements CommandLineRunner {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    // Constructor
    public InitRelationDataRunner(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {
        Member m = new Member("봄이");
        memberRepository.save(m);

        Order o = new Order("테스트상품3", m);
        orderRepository.save(o);

        orderRepository.findAll().forEach(ord ->
                System.out.println(ord.getId() + ": " + ord.getItemName() + " / " + ord.getMember().getName()));
    }
}