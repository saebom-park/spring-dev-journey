package com.springlab20.service;

import com.springlab20.dto.TodoRequestDto;
import com.springlab20.dto.TodoResponseDto;
import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto requestDto);
    List<TodoResponseDto> getTodos();
    TodoResponseDto getTodoById(Long id);
    TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto);
    void deleteTodo(Long id);
}