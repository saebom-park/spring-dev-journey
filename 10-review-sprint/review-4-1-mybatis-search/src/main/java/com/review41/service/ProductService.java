package com.review41.service;

import com.review41.dto.ProductResponseDto;
import java.util.*;

public interface ProductService {
    List<ProductResponseDto> searchProducts(String keyword);
}