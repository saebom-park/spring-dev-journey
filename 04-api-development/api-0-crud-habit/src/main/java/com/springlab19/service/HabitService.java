package com.springlab19.service;

import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import java.util.List;

public interface HabitService {
    HabitResponseDto createHabit(HabitRequestDto requestDto);
    List<HabitResponseDto> getHabits();
    HabitResponseDto getHabitById(Long id);
    HabitResponseDto updateHabit(Long id, HabitRequestDto requestDto);
    void deleteHabit(Long id);
}