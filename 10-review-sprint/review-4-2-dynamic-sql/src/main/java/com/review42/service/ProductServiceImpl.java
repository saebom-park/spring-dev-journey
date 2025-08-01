package com.review42.service;

import com.review42.dto.*;
import com.review42.repository.ProductMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    // constructor
    public ProductServiceImpl(ProductMapper productMapper) { this.productMapper = productMapper;}

    @Override
    public List<ProductResponseDto> getFilteredProducts(ProductFilterDto filterDto) {
        if (!List.of("product_name", "product_price").contains(filterDto.getSortBy())) {
            filterDto.setSortBy("product_name");
        }
        if (!List.of("asc", "desc").contains(filterDto.getSortDir().toLowerCase())) {
            filterDto.setSortDir("asc");
        }
        return productMapper.selectFilteredProducts(filterDto);
    }
}