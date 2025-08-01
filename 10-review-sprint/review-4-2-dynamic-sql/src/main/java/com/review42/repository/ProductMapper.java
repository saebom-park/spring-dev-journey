package com.review42.repository;

import com.review42.dto.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductResponseDto> selectFilteredProducts(ProductFilterDto filterDto);
}