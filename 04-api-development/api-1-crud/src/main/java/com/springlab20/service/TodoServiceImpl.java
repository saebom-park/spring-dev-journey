package com.springlab20.service;

import com.springlab20.repository.TodoRepository;
import com.springlab20.dto.TodoRequestDto;
import com.springlab20.dto.TodoResponseDto;
import com.springlab20.domain.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    // constructor
    public TodoServiceImpl(TodoRepository todoRepository) { this.todoRepository = todoRepository; }

    @Override
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTitle(), requestDto.isCompleted());
        todoRepository.save(todo);

        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    @Override
    @Transactional(readOnly=true)
    public List<TodoResponseDto> getTodos() {
        return todoRepository.findAll().stream().map(
                todo -> new TodoResponseDto(todo.getId(), todo.getTitle(), todo.isCompleted())
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly=true)
    public TodoResponseDto getTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    @Override
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));
        todo.setTitle(requestDto.getTitle());
        todo.setCompleted(requestDto.isCompleted());
        todoRepository.save(todo);

        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    @Override
    public void deleteTodo(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));
        todoRepository.delete(todo);
    }
}