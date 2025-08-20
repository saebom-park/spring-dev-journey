package com.todo.service;

import com.todo.dto.UserRequestDto;
import com.todo.dto.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);
    void deleteUser(Long id);
}