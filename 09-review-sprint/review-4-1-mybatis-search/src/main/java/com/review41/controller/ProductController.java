package com.review41.controller;

import com.review41.service.ProductService;
import com.review41.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // constructor
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/search")
    public List<ProductResponseDto> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
}