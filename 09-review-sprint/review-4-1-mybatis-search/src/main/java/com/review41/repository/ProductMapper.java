package com.review41.repository;

import com.review41.dto.ProductResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductResponseDto> findByKeyword(String keyword);
}