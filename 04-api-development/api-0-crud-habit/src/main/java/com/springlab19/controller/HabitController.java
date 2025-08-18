package com.springlab19.controller;

import com.springlab19.service.HabitService;
import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;

    // constructor
    public HabitController(HabitService habitService) { this.habitService = habitService; }

    @PostMapping
    public HabitResponseDto createHabit(@RequestBody HabitRequestDto requestDto) {
        return habitService.createHabit(requestDto);
    }

    @GetMapping
    public List<HabitResponseDto> getHabits() {
        return habitService.getHabits();
    }

    @GetMapping("/{id}")
    public HabitResponseDto getHabitById(@PathVariable Long id) {
        return habitService.getHabitById(id);
    }

    @PutMapping("/{id}")
    public HabitResponseDto updateHabit(@PathVariable Long id, @RequestBody HabitRequestDto requestDto) {
        return habitService.updateHabit(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
    }

}