package com.todo.service;

import com.todo.dto.CategoryCreateRequestDto;
import com.todo.dto.CategoryResponseDto;
import com.todo.dto.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryCreateRequestDto requestDto);
    List<CategoryResponseDto> getCategories();
    CategoryResponseDto getCategoryById(Long id);
    void updateCategory(Long id, CategoryUpdateRequestDto requestDto);
    void deleteCategory(Long id);
}