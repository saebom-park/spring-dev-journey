package com.review42.controller;

import com.review42.service.ProductService;
import com.review42.dto.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // constructor
    public ProductController(ProductService productService) { this.productService = productService;}

    @GetMapping("/filter")
    public List<ProductResponseDto> getFilteredProducts(@ModelAttribute ProductFilterDto filterDto) {
        return productService.getFilteredProducts(filterDto);
    }
}