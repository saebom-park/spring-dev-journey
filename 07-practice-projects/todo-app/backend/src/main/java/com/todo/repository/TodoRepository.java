package com.todo.repository;

import com.todo.domain.Todo;
import com.todo.domain.Category;
import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
    List<Todo> findByUserIdAndStatus(Long userId, TodoStatus status);
    List<Todo> findByUserIdAndPriority(Long userId, TodoPriority priority);
    List<Todo> findByUserIdAndCategoryId(Long userId, Long categoryId);
    List<Todo> findByUserIdAndStatusAndCategoryId(Long userId, TodoStatus status, Long categoryId);
    int countByUserIdAndStatus(Long userId, TodoStatus status);
}