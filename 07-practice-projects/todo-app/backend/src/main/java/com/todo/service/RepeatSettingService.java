package com.todo.service;

import com.todo.dto.RepeatSettingRequestDto;
import com.todo.dto.RepeatSettingDto;

public interface RepeatSettingService {
    RepeatSettingDto addRepeatToTodo(Long todoId, RepeatSettingRequestDto requestDto);
    RepeatSettingDto updateRepeatSetting(Long repeatId, RepeatSettingRequestDto requestDto);
    void deleteRepeatSetting(Long repeatId);
}