package com.review51.controller;

import com.review51.service.ProductService;
import com.review51.dto.ProductRequestDto;
import com.review51.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // constructor
    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping("/register") // 경로 생략 가능 (Rest 규칙에 더 적합하고 간결)
    public void save(@RequestBody ProductRequestDto requestDto) {
        productService.save(requestDto);
    }

    @GetMapping("/select") // 경로 생략 가능 (Rest 규칙에 더 적합하고 간결)
    public List<ProductResponseDto> findAll(){
        return productService.findAll();
    }
}