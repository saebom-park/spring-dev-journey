package com.springlab16.practice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitProductDataRunner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InitProductDataRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        Category c = new Category("의류");
        categoryRepository.save(c);

        Product p = new Product("가디건", 1000, c);
        productRepository.save(p);

        productRepository.findAll().forEach(prod ->
                System.out.println(prod.getId() + ": " + prod.getCategory().getName() + " / " + prod.getName() + " / " + prod.getPrice()));
    }
}