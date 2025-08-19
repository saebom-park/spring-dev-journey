package com.springlab15.practice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataRunner implements CommandLineRunner {
    private final OrderRepository orderRepository;

    public InitDataRunner(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        Order o = new Order();
        o.setItemName("테스트상품2");
        o.setPrice(20000);
        orderRepository.save(o);

        orderRepository.findAll().forEach(ord ->
                System.out.println(ord.getId() + ": " + ord.getItemName() + " / " + ord.getPrice()));
    }
}