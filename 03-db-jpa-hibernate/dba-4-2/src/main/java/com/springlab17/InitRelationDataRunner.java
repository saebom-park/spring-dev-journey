package com.springlab17;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitRelationDataRunner implements CommandLineRunner {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public InitRelationDataRunner(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void run(String... args){
        Member m1 = new Member("온이");
        Order o1 = new Order("테스트상품3", 1000);
        Order o2 = new Order("테스트상품4", 1500);
        m1.addOrder(o1);
        m1.addOrder(o2);
        memberRepository.save(m1);
        orderRepository.save(o1);
        orderRepository.save(o2);

        memberRepository.findAll().forEach(mem -> {
            System.out.println(mem.getName() + "님의 주문 목록: ");
                    mem.getOrders().forEach(ord ->
                            System.out.println(ord.getItemName() + " / " + ord.getPrice()));

        });


    }
}