package com.review51.service;

import com.review51.dto.ProductRequestDto;
import com.review51.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    void save(ProductRequestDto requestDto);
    List<ProductResponseDto> findAll();
}