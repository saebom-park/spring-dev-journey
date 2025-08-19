# [API-1단계] CRUD API 고도화 (crud-responseentity)

> 이번 단계에서는 모든 응답이 200 OK로만 처리되던 한계를 개선합니다.
> 
> 
> REST API에서 상황에 맞는 **HTTP 상태 코드**를 반환하고,
> 
> `ResponseEntity`와 `@Transactional`을 사용해 **실무형 API**로 발전시킵니다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| HTTP 상태 코드 | 200(OK), 201(Created), 204(No Content), 400(Bad Request), 404(Not Found) 등 REST API 상황별 코드 |
| ResponseEntity | 응답 바디 + 상태 코드 + 헤더를 함께 제어할 수 있는 객체 |
| POST 응답 | 자원 생성 → `201 Created` + Location 헤더 |
| DELETE 응답 | 자원 삭제 성공 → `204 No Content` |
| @Transactional | 트랜잭션 단위로 DB 작업 보장, 원자성 확보 |
| readOnly 옵션 | 조회 메서드 최적화 (`@Transactional(readOnly = true)`) |
| Dirty Checking | 엔티티 변경 시 자동 update SQL 실행 (save 호출 불필요) |

---

## 🧾 예시 코드 (Habit)

### HabitController

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
        return ResponseEntity
                .created(URI.create("/api/habits/" + response.getId())) // Location 헤더
                .body(response); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<HabitResponseDto>> getHabits() {
        return ResponseEntity.ok(habitService.getHabits()); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(habitService.getHabitById(id)); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable Long id,
                                                        @RequestBody HabitRequestDto requestDto) {
        return ResponseEntity.ok(habitService.updateHabit(id, requestDto)); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

```

---

### HabitServiceImpl

```java
package com.springlab19.service;

import com.springlab19.repository.HabitRepository;
import com.springlab19.dto.HabitRequestDto;
import com.springlab19.dto.HabitResponseDto;
import com.springlab19.domain.Habit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
    @Transactional(readOnly = true)
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
        // Dirty Checking에 의해 update SQL 자동 실행
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

## 📌 포인트 요약

- `ResponseEntity`로 상태 코드/바디/헤더 제어 가능
- POST → 201 + Location, DELETE → 204, GET/PUT → 200
- 서비스 계층에는 `@Transactional` 필수
- 조회 전용 메서드는 `@Transactional(readOnly = true)`로 성능 최적화
- Dirty Checking으로 update 시 save() 호출 불필요
- API-0의 단순 CRUD → API-1에서 “RESTful 상태코드 + 트랜잭션 관리”까지 보강

---

## 🧪 실습 미션 (Diary)

🎯 목표: **Diary CRUD API를 구현하고, ResponseEntity + @Transactional을 반영한다.**

1. `Diary` 엔티티 생성 (`id`, `title`, `content`, `createdDate`)
2. `DiaryRequestDto` / `DiaryResponseDto` 설계
3. `DiaryService` + `DiaryServiceImpl` 작성
    - 클래스 레벨 `@Transactional` 적용
    - 조회 메서드는 `@Transactional(readOnly = true)`
4. `DiaryController` 작성 → 아래 규칙 반영
    - POST → 201 + Location 헤더
    - GET/PUT → 200 OK
    - DELETE → 204 No Content
5. Postman으로 직접 호출해서 상태코드 확인