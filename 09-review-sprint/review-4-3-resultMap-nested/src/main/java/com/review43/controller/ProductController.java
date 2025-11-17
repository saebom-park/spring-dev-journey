package com.review43.controller;

import com.review43.service.ProductService;
import com.review43.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // constructor
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/nested")
    public List<ProductResponseDto> getProductsWithCategory() {
        return productService.getProductsWithCategory();
    }
}