package com.springlab19.service;

import com.springlab19.repository.HabitRepository;
import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import com.springlab19.domain.Habit;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    // constructor
    public HabitServiceImpl(HabitRepository habitRepository) { this.habitRepository = habitRepository; }

    @Override
    public HabitResponseDto createHabit(HabitRequestDto requestDto) {
        Habit habit = new Habit(requestDto.getName(), requestDto.getGoalPerDay());
        habitRepository.save(habit);

        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    public List<HabitResponseDto> getHabits() {
        return habitRepository.findAll().stream().map(
                habit -> new HabitResponseDto(
                        habit.getId(),
                        habit.getName(),
                        habit.getGoalPerDay()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public HabitResponseDto getHabitById(Long id) {
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        Habit habit = optionalHabit.orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));

        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    public HabitResponseDto updateHabit(Long id, HabitRequestDto requestDto) {
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        Habit habit = optionalHabit.orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));
        habit.setName(requestDto.getName());
        habit.setGoalPerDay(requestDto.getGoalPerDay());
        habitRepository.save(habit);

        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    public void deleteHabit(Long id) {
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        Habit habit = optionalHabit.orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));
        habitRepository.delete(habit);
    }
}