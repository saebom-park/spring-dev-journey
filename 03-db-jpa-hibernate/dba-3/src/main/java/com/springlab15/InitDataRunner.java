package com.springlab15;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataRunner implements CommandLineRunner {

    private final ProductRepository productRepository;

    public InitDataRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Product p = new Product();
        p.setName("테스트상품");
        p.setPrice(10000);
        productRepository.save(p);

        productRepository.findAll().forEach(prod ->
                System.out.println(prod.getId() + ": " + prod.getName() + " / " + prod.getPrice()));
    }
}