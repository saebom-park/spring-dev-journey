package com.todo.service;

import com.todo.dto.ScheduleCreateRequestDto;
import com.todo.dto.ScheduleDto;
import com.todo.dto.ScheduleUpdateRequestDto;

public interface ScheduleService {
    ScheduleDto addScheduleToTodo(Long todoId, ScheduleCreateRequestDto requestDto);
    void updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto);
    void deleteSchedule(Long scheduleId);
}