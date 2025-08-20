package com.todo.service;

import com.todo.dto.TodoCreateRequestDto;
import com.todo.dto.TodoUpdateRequestDto;
import com.todo.dto.TodoStatusRequestDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoDetailResponseDto;
import com.todo.dto.TodoUpdateResponseDto;
import com.todo.dto.TodoStatsResponseDto;
import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoCreateRequestDto requestDto);
    List<TodoResponseDto> getTodos();
    TodoDetailResponseDto getTodoById(Long id);
    List<TodoResponseDto> getTodosByStatus(TodoStatus status);
    List<TodoResponseDto> getTodosByPriority(TodoPriority priority);
    List<TodoResponseDto> getTodosByCategory(Long categoryId);
    List<TodoResponseDto> getTodosWithFilters(TodoStatus status, Long categoryId);
    TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto);
    TodoUpdateResponseDto updateTodoStatus(Long id, TodoStatusRequestDto requestDto);
    void deleteTodo(Long id);
    int getTodoCountByStatus(TodoStatus status);
}