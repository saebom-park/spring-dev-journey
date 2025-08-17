package com.todo.service;

import com.todo.repository.CategoryRepository;
import com.todo.repository.UserRepository;
import com.todo.dto.CategoryCreateRequestDto;
import com.todo.dto.CategoryResponseDto;
import com.todo.dto.CategoryUpdateRequestDto;
import com.todo.domain.Category;
import com.todo.domain.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // constructor
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryCreateRequestDto requestDto) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        Category category = new Category(requestDto.getName(), requestDto.getColor(), user);
        categoryRepository.save(category);

        return new CategoryResponseDto(category.getId(), category.getName(), category.getColor(), category.getUser().getNickName());
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return categoryRepository.findByUserId(user.getId()).stream().map(
                category -> new CategoryResponseDto(category.getId(), category.getName(), category.getColor(), category.getUser().getNickName())
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        return new CategoryResponseDto(category.getId(), category.getName(), category.getColor(), category.getUser().getNickName());
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateRequestDto requestDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        category.setName(requestDto.getName());
        category.setColor(requestDto.getColor());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        categoryRepository.delete(category);
    }
}