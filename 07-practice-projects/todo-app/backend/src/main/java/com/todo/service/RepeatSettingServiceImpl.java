package com.todo.service;

import com.todo.repository.RepeatSettingRepository;
import com.todo.repository.TodoRepository;
import com.todo.dto.RepeatSettingRequestDto;
import com.todo.dto.RepeatSettingDto;
import com.todo.domain.RepeatSetting;
import com.todo.domain.Todo;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class RepeatSettingServiceImpl implements RepeatSettingService {
    private final RepeatSettingRepository repeatSettingRepository;
    private final TodoRepository todoRepository;

    // constructor
    public RepeatSettingServiceImpl(RepeatSettingRepository repeatSettingRepository, TodoRepository todoRepository) {
        this.repeatSettingRepository = repeatSettingRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public RepeatSettingDto addRepeatToTodo(Long todoId, RepeatSettingRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        RepeatSetting repeatSetting = new RepeatSetting(requestDto.getIsRepeated(), requestDto.getRepeatStart(), requestDto.getRepeatDue(), requestDto.getRepeatPattern(), requestDto.getDayOfWeek());
        todo.setRepeatSetting(repeatSetting);
        todoRepository.save(todo);

        return getRepeatSettingDto(todo.getRepeatSetting());
    }

    @Override
    public RepeatSettingDto updateRepeatSetting(Long repeatId, RepeatSettingRequestDto requestDto) {
        Optional<RepeatSetting> optionalRepeatSetting = repeatSettingRepository.findById(repeatId);
        RepeatSetting repeatSetting = optionalRepeatSetting.orElseThrow(() -> new IllegalArgumentException("해당 반복 설정이 존재하지 않습니다."));
        repeatSetting.setIsRepeated(requestDto.getIsRepeated());
        repeatSetting.setRepeatStart(requestDto.getRepeatStart());
        repeatSetting.setRepeatDue(requestDto.getRepeatDue());
        repeatSetting.setRepeatPattern(requestDto.getRepeatPattern());
        repeatSetting.setDayOfWeek(requestDto.getDayOfWeek());

        repeatSettingRepository.save(repeatSetting);

        return getRepeatSettingDto(repeatSetting);
    }

    @Override
    public void deleteRepeatSetting(Long repeatId) {
        Optional<RepeatSetting> optionalRepeatSetting = repeatSettingRepository.findById(repeatId);
        RepeatSetting repeatSetting = optionalRepeatSetting.orElseThrow(() -> new IllegalArgumentException("해당 반복 설정이 존재하지 않습니다."));
        repeatSettingRepository.delete(repeatSetting);
    }

    public RepeatSettingDto getRepeatSettingDto(RepeatSetting repeatSetting) {
        return new RepeatSettingDto(
                repeatSetting.getId(),
                repeatSetting.getIsRepeated(),
                repeatSetting.getRepeatStart(),
                repeatSetting.getRepeatDue(),
                repeatSetting.getRepeatPattern(),
                repeatSetting.getDayOfWeek()
        );
    }
}