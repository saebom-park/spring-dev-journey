package com.todo.controller;

import com.todo.service.RepeatSettingService;
import com.todo.dto.RepeatSettingDto;
import com.todo.dto.RepeatSettingRequestDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
public class RepeatSettingController {
    private final RepeatSettingService repeatSettingService;

    // constructor
    public RepeatSettingController(RepeatSettingService repeatSettingService) {
        this.repeatSettingService = repeatSettingService;
    }

    @PostMapping("/api/todos/{todoId}/repeat")
    public ResponseEntity<RepeatSettingDto> addRepeatToTodo(@PathVariable Long todoId, @RequestBody RepeatSettingRequestDto requestDto) {
        RepeatSettingDto response = repeatSettingService.addRepeatToTodo(todoId, requestDto);

        return ResponseEntity
                .created(URI.create("/api/todos/" + todoId + "/repeat/" + response.getId()))
                .body(response);
    }

    @PutMapping("/api/repeat-settings/{id}")
    public ResponseEntity<RepeatSettingDto> updateRepeatSetting(@PathVariable Long id, @RequestBody RepeatSettingRequestDto requestDto) {
        return ResponseEntity.ok(repeatSettingService.updateRepeatSetting(id, requestDto));
    }

    @DeleteMapping("/api/repeat-settings/{id}")
    public ResponseEntity<Void> deleteRepeatSetting(@PathVariable Long id) {
        repeatSettingService.deleteRepeatSetting(id);

        return ResponseEntity.noContent().build();
    }
}