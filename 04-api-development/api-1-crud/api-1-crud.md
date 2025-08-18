# [API-1단계] CRUD API (crud-api)

> 이번 단계에서는 API-0에서 직접 구현했던 Habit CRUD를 기반으로,
> 
> 
> 실무에서 필수적으로 적용되는 **트랜잭션(@Transactional)** 과
> 
> **응답(ResponseEntity) 처리 방식**을 보강한다.
> 
> 이를 통해 단순한 CRUD에서 한 단계 발전된 API 작성법을 익힌다.
> 

---

## 💡 핵심 개념 요약

- **@Transactional**
    - Service 계층에서 트랜잭션 경계를 설정
    - 조회용 메서드는 `readOnly = true` → 성능 최적화
    - 수정/삭제 메서드는 기본 모드 사용
- **ResponseEntity**
    - HTTP 상태 코드 + 응답 데이터를 함께 반환
    - 클라이언트와 명확한 통신 가능 (`201 Created`, `404 Not Found` 등)

---

## 🧾 예시 코드 (Habit 보강 버전)

### HabitServiceImpl.java

```java
package com.springlab19.service;

import com.springlab19.repository.HabitRepository;
import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import com.springlab19.domain.Habit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional  // 클래스 전체에 기본 트랜잭션 적용
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public HabitResponseDto createHabit(HabitRequestDto requestDto) {
        Habit habit = new Habit(requestDto.getName(), requestDto.getGoalPerDay());
        habitRepository.save(habit);
        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    @Transactional(readOnly = true) // 조회 최적화
    public List<HabitResponseDto> getHabits() {
        return habitRepository.findAll().stream()
                .map(habit -> new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HabitResponseDto getHabitById(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));
        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    public HabitResponseDto updateHabit(Long id, HabitRequestDto requestDto) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));
        habit.setName(requestDto.getName());
        habit.setGoalPerDay(requestDto.getGoalPerDay());
        return new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay());
    }

    @Override
    public void deleteHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 습관이 존재하지 않습니다."));
        habitRepository.delete(habit);
    }
}

```

---

### HabitController.java

```java
package com.springlab19.controller;

import com.springlab19.service.HabitService;
import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitResponseDto> createHabit(@RequestBody HabitRequestDto requestDto) {
        HabitResponseDto response = habitService.createHabit(requestDto);
        return ResponseEntity.created(URI.create("/api/habits/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponseDto>> getHabits() {
        return ResponseEntity.ok(habitService.getHabits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable Long id, @RequestBody HabitRequestDto requestDto) {
        return ResponseEntity.ok(habitService.updateHabit(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}

```

---

## 📌 비교 포인트

- **API-0**: 단순 CRUD (예외는 `IllegalArgumentException` 던짐, 상태코드 직접 지정 안함)
- **API-1**:
    - Service 계층에 `@Transactional` 적용 → 데이터 일관성 보장
    - Controller에서 `ResponseEntity` 활용 → 명확한 상태 코드 반환 (`201`, `200`, `204`)

---

## 🧪 실습 미션

🎯 목표: Habit 예시처럼 **Todo 관리 API**를 직접 구현하되,

이번에는 `@Transactional`과 `ResponseEntity`를 반드시 적용한다.

1. `Todo` 엔티티 생성 (id, title, completed)
2. Request/Response DTO 작성
3. Repository/Service/Controller 계층 구현
4. `@Transactional`과 `ResponseEntity` 보강 적용
5. Postman으로 CRUD 요청 테스트

> 참고: POST → 201 Created, DELETE → 204 No Content 응답 형태 확인 필수
>