package com.todo.service;

import com.todo.repository.UserRepository;
import com.todo.dto.UserCreateRequestDto;
import com.todo.dto.UserResponseDto;
import com.todo.domain.User;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // constructor
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserResponseDto createUser(UserCreateRequestDto requestDto) {
        User user = new User(requestDto.getUserName(), requestDto.getNickName());
        userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getUserName(), user.getNickName(), user.getCreatedAt());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        return new UserResponseDto(user.getId(), user.getUserName(), user.getNickName(), user.getCreatedAt());
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(
                user -> new UserResponseDto(user.getId(), user.getUserName(), user.getNickName(), user.getCreatedAt())
        ).collect(Collectors.toList());
    }

    @Override
    public void updateUser(Long id, UserCreateRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));

        user.setUserName(requestDto.getUserName());
        user.setNickName(requestDto.getNickName());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));
        userRepository.delete(user);
    }
}