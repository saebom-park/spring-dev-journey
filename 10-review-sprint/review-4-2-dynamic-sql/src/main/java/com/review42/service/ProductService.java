package com.review42.service;

import com.review42.dto.*;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getFilteredProducts(ProductFilterDto filterDto);
}