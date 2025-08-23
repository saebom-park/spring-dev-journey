package com.todo.controller;

import com.todo.service.ScheduleService;
import com.todo.dto.ScheduleDto;
import com.todo.dto.ScheduleRequestDto;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5175"})
public class ScheduleController {
    private final ScheduleService scheduleService;

    // constructor
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/api/todos/{todoId}/schedule")
    public ResponseEntity<ScheduleDto> addScheduleToTodo(@PathVariable Long todoId, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleDto response = scheduleService.addScheduleToTodo(todoId, requestDto);
        return ResponseEntity
                .created(URI.create("/api/todos/" + todoId + "/schedule/" + response.getId()))
                .body(response);
    }

    @PutMapping("/api/schedules/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    @DeleteMapping("/api/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}