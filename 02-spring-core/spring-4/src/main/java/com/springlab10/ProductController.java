package com.springlab10;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto(productId, "온이", 1000);
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword) {
        return "검색어: " + keyword;
    }
}