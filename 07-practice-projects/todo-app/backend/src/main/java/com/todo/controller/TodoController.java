package com.todo.controller;

import com.todo.service.TodoService;
import com.todo.dto.TodoCreateRequestDto;
import com.todo.dto.TodoUpdateRequestDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoDetailResponseDto;
import com.todo.dto.TodoStatusRequestDto;
import com.todo.dto.TodoUpdateResponseDto;
import com.todo.enums.TodoPriority;
import com.todo.enums.TodoStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    // constructor
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoCreateRequestDto requestDto) {
        TodoResponseDto response = todoService.createTodo(requestDto);

        return ResponseEntity
                .created(URI.create("/api/todos/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDetailResponseDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodosByFilters(
            @RequestParam(required=false) TodoStatus status,
            @RequestParam(required=false) Long categoryId,
            @RequestParam(required=false) TodoPriority priority
    ) {
        if (status != null && categoryId != null) {
            return ResponseEntity.ok(todoService.getTodosWithFilters(status, categoryId));
        } else if (status != null) {
            return ResponseEntity.ok(todoService.getTodosByStatus(status));
        } else if (categoryId != null) {
            return ResponseEntity.ok(todoService.getTodosByCategory(categoryId));
        } else if (priority != null) {
            return ResponseEntity.ok(todoService.getTodosByPriority(priority));
        } else {
            return ResponseEntity.ok(todoService.getTodos());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, requestDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TodoUpdateResponseDto> updateTodoStatus(@PathVariable Long id, @RequestBody TodoStatusRequestDto requestDto) {
        return ResponseEntity.ok(todoService.updateTodoStatus(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/{status}")
    public ResponseEntity<Integer> getTodoCountByStatus(@PathVariable TodoStatus status) {
        return ResponseEntity.ok(todoService.getTodoCountByStatus(status));
    }
}