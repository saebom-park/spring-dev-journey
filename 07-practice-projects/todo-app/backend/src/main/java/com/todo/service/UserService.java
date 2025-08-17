package com.todo.service;

import com.todo.dto.UserCreateRequestDto;
import com.todo.dto.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserCreateRequestDto requestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    void updateUser(Long id, UserCreateRequestDto requestDto);
    void deleteUser(Long id);
}