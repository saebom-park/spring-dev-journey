package com.review41.repository;

import com.review41.dto.ProductResponseDto;
import java.util.List;

public interface ProductMapper {
    List<ProductResponseDto> findByKeyword(String keyword);
}