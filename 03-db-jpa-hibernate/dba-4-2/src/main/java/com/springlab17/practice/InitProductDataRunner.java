package com.springlab17.practice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitProductDataRunner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Constructor
    public InitProductDataRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Category c1 = new Category("도서");
        Product p1 = new Product("상품1", 15000);
        Product p2 = new Product("상품2", 20000);

        c1.addProduct(p1);
        c1.addProduct(p2);
        categoryRepository.save(c1);
        productRepository.save(p1);
        productRepository.save(p2);

        categoryRepository.findAll().forEach(cat -> {
            System.out.println(cat.getName() + " 카테고리의 상품 목록: ");
           cat.getProducts().forEach(prod -> System.out.println("- " + prod.getItemName() + " / " + prod.getPrice() + "원"));
        });
    }
}