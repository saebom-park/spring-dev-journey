package com.review41.service;

import com.review41.repository.ProductMapper;
import com.review41.dto.ProductResponseDto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    // constructor
    public ProductServiceImpl(ProductMapper productMapper) { this.productMapper = productMapper; }

    @Override
    public List<ProductResponseDto> searchProducts(String keyword) {
        return productMapper.findByKeyword(keyword);
    }
}