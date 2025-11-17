package com.review43.service;

import com.review43.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getProductsWithCategory();
}