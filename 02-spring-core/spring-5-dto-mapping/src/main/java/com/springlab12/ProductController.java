package com.springlab12;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return new ProductResponseDto(
                requestDto.getName(),
                requestDto.getPrice(),
                "상품이 정상적으로 등록되었습니다."
        );
    }
}