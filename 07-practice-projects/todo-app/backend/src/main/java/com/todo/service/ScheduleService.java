package com.todo.service;

import com.todo.dto.ScheduleRequestDto;
import com.todo.dto.ScheduleDto;

public interface ScheduleService {
    ScheduleDto addScheduleToTodo(Long todoId, ScheduleRequestDto requestDto);
    ScheduleDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto);
    void deleteSchedule(Long scheduleId);
}