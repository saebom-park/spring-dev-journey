package com.review43.service;

import com.review43.dto.ProductResponseDto;
import com.review43.repository.ProductMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    // constructor
    public ProductServiceImpl(ProductMapper productMapper) { this.productMapper = productMapper; }

    @Override
    public List<ProductResponseDto> getProductsWithCategory() {
        return productMapper.selectProductsWithCategory();
    }
}