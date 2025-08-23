package com.todo.service;

import com.todo.repository.TodoRepository;
import com.todo.repository.UserRepository;
import com.todo.repository.CategoryRepository;
import com.todo.repository.ScheduleRepository;
import com.todo.repository.RepeatSettingRepository;
import com.todo.dto.TodoCreateRequestDto;
import com.todo.dto.TodoUpdateRequestDto;
import com.todo.dto.TodoStatusRequestDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoDetailResponseDto;
import com.todo.dto.TodoUpdateResponseDto;
import com.todo.dto.TodoStatsResponseDto;
import com.todo.dto.CategoryDto;
import com.todo.dto.ScheduleDto;
import com.todo.dto.RepeatSettingDto;
import com.todo.domain.Todo;
import com.todo.domain.User;
import com.todo.domain.Category;
import com.todo.domain.Schedule;
import com.todo.domain.RepeatSetting;
import com.todo.enums.TodoStatus;
import com.todo.enums.TodoPriority;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;
    private final RepeatSettingRepository repeatSettingRepository;

    // constructor
    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository, CategoryRepository categoryRepository, ScheduleRepository scheduleRepository, RepeatSettingRepository repeatSettingRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.scheduleRepository = scheduleRepository;
        this.repeatSettingRepository = repeatSettingRepository;
    }

    @Override
    public TodoResponseDto createTodo(TodoCreateRequestDto requestDto) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        Optional<Category> optionalCategory = categoryRepository.findById(requestDto.getCategoryId());
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName(), category.getColor());

        Todo todo = new Todo(requestDto.getContent(), requestDto.getPriority(), category, user);

        if (requestDto.getStartDate() != null || requestDto.getDueDate() != null) {
            Schedule schedule = new Schedule(requestDto.getStartDate(), requestDto.getDueDate());
            // todo 객체와 연결 필요!
            todo.setSchedule(schedule);
        }

        if (requestDto.getIsRepeated()) {
            RepeatSetting repeatSetting = new RepeatSetting(requestDto.getIsRepeated(), requestDto.getRepeatStart(), requestDto.getRepeatDue(), requestDto.getRepeatPattern(), requestDto.getDayOfWeek());
            // todo 객체와 연결 필요!
            todo.setRepeatSetting(repeatSetting);
        }

        todoRepository.save(todo);

        return getTodoResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> getTodos() {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return getTodoResponseDtoList(todoRepository.findByUserId(user.getId()));
    }

    @Override
    public TodoDetailResponseDto getTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getContent(),
                todo.getStatus(),
                todo.getPriority(),
                new CategoryDto(
                        todo.getCategory().getId(),
                        todo.getCategory().getName(),
                        todo.getCategory().getColor()
                ),
                todo.getUser().getNickName(),
                todo.getCreatedAt(),
                todo.getCompletedAt(),
                (todo.getSchedule() != null ) ? new ScheduleDto(todo.getSchedule().getId(), todo.getSchedule().getStartDate(), todo.getSchedule().getDueDate()) : null,
                (todo.getRepeatSetting() != null) ? new RepeatSettingDto(todo.getRepeatSetting().getId(), todo.getRepeatSetting().getIsRepeated(), todo.getRepeatSetting().getRepeatStart(), todo.getRepeatSetting().getRepeatDue(), todo.getRepeatSetting().getRepeatPattern(), todo.getRepeatSetting().getDayOfWeek()) : null
        );
    }

    @Override
    public List<TodoResponseDto> getTodosByStatus(TodoStatus status) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return getTodoResponseDtoList(todoRepository.findByUserIdAndStatus(user.getId(), status));
    }

    @Override
    public List<TodoResponseDto> getTodosByPriority(TodoPriority priority) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return getTodoResponseDtoList(todoRepository.findByUserIdAndPriority(user.getId(), priority));
    }

    @Override
    public List<TodoResponseDto> getTodosByCategory(Long categoryId) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        return getTodoResponseDtoList(todoRepository.findByUserIdAndCategoryId(user.getId(), categoryId));
    }

    @Override
    public List<TodoResponseDto> getTodosWithFilters(TodoStatus status, Long categoryId) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        return getTodoResponseDtoList(todoRepository.findByUserIdAndStatusAndCategoryId(user.getId(), status, categoryId));
    }

    @Override
    public TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        Optional<Category> optionalCategory = categoryRepository.findById(requestDto.getCategoryId());
        Category category = optionalCategory.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        todo.setContent(requestDto.getContent());
        todo.setPriority(requestDto.getPriority());
        todo.setCategory(category);

        todoRepository.save(todo);

        return getTodoResponseDto(todo);
    }

    @Override
    public TodoUpdateResponseDto updateTodoStatus(Long id, TodoStatusRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        todo.setStatus(requestDto.getStatus());
        if (requestDto.getStatus() == TodoStatus.COMPLETED) {
            todo.setCompletedAt(LocalDateTime.now());
        }

        todoRepository.save(todo);

        return new TodoUpdateResponseDto(todo.getId(), todo.getStatus(), todo.getCompletedAt());
    }

    @Override
    public void deleteTodo(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        todoRepository.delete(todo);
    }

    @Override
    public int getTodoCountByStatus(TodoStatus status) {
        // 사용자 임시 조회 (추후 변경 예정)
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return todoRepository.countByUserIdAndStatus(user.getId(), status);
    }

    public List<TodoResponseDto> getTodoResponseDtoList(List<Todo> todos) {
        return todos.stream().map(
                todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getContent(),
                        todo.getStatus(),
                        todo.getPriority(),
                        new CategoryDto(todo.getCategory().getId(), todo.getCategory().getName(), todo.getCategory().getColor()),
                        todo.getUser().getNickName(),
                        todo.getCreatedAt(),
                        todo.getCompletedAt(),
                        (todo.getSchedule() != null && (todo.getSchedule().getStartDate() != null || todo.getSchedule().getDueDate() != null)),
                        todo.getRepeatSetting() != null && todo.getRepeatSetting().getIsRepeated()
                )
        ).collect(Collectors.toList());
    }

    public TodoResponseDto getTodoResponseDto(Todo todo) {
        return new TodoResponseDto(
                todo.getId(),
                todo.getContent(),
                todo.getStatus(),
                todo.getPriority(),
                new CategoryDto(todo.getCategory().getId(), todo.getCategory().getName(), todo.getCategory().getColor()),
                todo.getUser().getNickName(),
                todo.getCreatedAt(),
                todo.getCompletedAt(),
                (todo.getSchedule() != null && (todo.getSchedule().getStartDate() != null || todo.getSchedule().getDueDate() != null)),
                todo.getRepeatSetting() != null && todo.getRepeatSetting().getIsRepeated()
        );
    }

}