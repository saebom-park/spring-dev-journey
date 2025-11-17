package com.review51.service;

import com.review51.repository.ProductRepository;
import com.review51.domain.Product;
import com.review51.dto.ProductRequestDto;
import com.review51.dto.ProductResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    // constructor
    public ProductServiceImpl(ProductRepository productRepository) { this.productRepository = productRepository; }

    @Override
    public void save(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getPrice());
        productRepository.save(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream().map(
                product -> new ProductResponseDto(product.getId(), product.getName(), product.getPrice())
        ).collect(Collectors.toList());
    }
}