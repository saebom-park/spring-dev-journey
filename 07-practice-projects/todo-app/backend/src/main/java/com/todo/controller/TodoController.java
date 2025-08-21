package com.todo.controller;

import com.todo.service.TodoService;
import com.todo.service.ScheduleService;
import com.todo.service.RepeatSettingService;
import com.todo.dto.TodoCreateRequestDto;
import com.todo.dto.TodoUpdateRequestDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoDetailResponseDto;
import com.todo.dto.TodoStatusRequestDto;
import com.todo.dto.TodoUpdateResponseDto;
import com.todo.dto.TodoStatsResponseDto;
import com.todo.domain.Todo;
import com.todo.domain.Schedule;
import com.todo.domain.RepeatSetting;
import com.todo.enums.RepeatPattern;
import com.todo.enums.TodoPriority;
import com.todo.enums.TodoStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;
    private final ScheduleService scheduleService;
    private final RepeatSettingService repeatSettingService;

    // constructor
    public TodoController(TodoService todoService, ScheduleService scheduleService, RepeatSettingService repeatSettingService) {
        this.todoService = todoService;
        this.scheduleService = scheduleService;
        this.repeatSettingService = repeatSettingService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoCreateRequestDto requestDto) {
        TodoResponseDto response = todoService.createTodo(requestDto);

        return ResponseEntity
                .created(URI.create("api/todos/" + response.getId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDetailResponseDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<TodoResponseDto>> getTodosByStatus(@PathVariable TodoStatus status) {
        return ResponseEntity.ok(todoService.getTodosByStatus(status));
    }

    @GetMapping("/{priority}")
    public ResponseEntity<List<TodoResponseDto>> getTodosByPriority(@PathVariable TodoPriority priority) {
        return ResponseEntity.ok(todoService.getTodosByPriority(priority));
    }
}