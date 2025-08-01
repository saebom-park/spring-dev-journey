package com.review42.repository;

import com.review42.dto.*;
import java.util.List;

public interface ProductMapper {
    List<ProductResponseDto> selectFilteredProducts(ProductFilterDto filterDto);
}