package com.todo.service;

import com.todo.repository.ScheduleRepository;
import com.todo.repository.TodoRepository;
import com.todo.dto.ScheduleCreateRequestDto;
import com.todo.dto.ScheduleDto;
import com.todo.dto.ScheduleUpdateRequestDto;
import com.todo.domain.Schedule;
import com.todo.domain.Todo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;

    // constructor
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, TodoRepository todoRepository) {
        this.scheduleRepository = scheduleRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public ScheduleDto addScheduleToTodo(Long todoId, ScheduleCreateRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일이 존재하지 않습니다."));

        Schedule schedule = new Schedule(requestDto.getStartDate(), requestDto.getDueDate());
        todo.setSchedule(schedule);
        scheduleRepository.save(schedule);

        return new ScheduleDto(schedule.getId(), schedule.getStartDate(), schedule.getDueDate());
    }

    @Override
    public void updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        schedule.setStartDate(requestDto.getStartDate());
        schedule.setDueDate(requestDto.getDueDate());
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        scheduleRepository.delete(schedule);
    }
}