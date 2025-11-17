package com.review43.repository;

import com.review43.dto.ProductResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductResponseDto> selectProductsWithCategory();
}