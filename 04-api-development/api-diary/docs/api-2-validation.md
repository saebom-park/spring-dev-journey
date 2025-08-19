# [API-2단계] 입력값 검증 (validation-dto)

> 이번 단계에서는 아무 제약 없이 입력을 저장하던 한계를 개선합니다.
> 
> 
> Bean Validation을 통해 잘못된 입력을 자동 차단하고,
> 
> 컨트롤러 입구에서 검증 오류를 처리하는 흐름을 학습합니다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Bean Validation (JSR-380) | 자바 표준 입력 검증 API (`jakarta.validation`) |
| 주요 어노테이션 | `@NotBlank`(문자열 필수), `@NotNull`, `@Min`, `@Size`, `@Email` 등 |
| @Valid | 컨트롤러 메서드 파라미터 검증 트리거 |
| BindingResult | 검증 결과를 객체로 받아 추가 로직 실행 가능 |
| 자동 응답 | 검증 실패 시 Spring이 400 Bad Request 자동 반환 |
| 실무 의미 | DB까지 가지 않고 컨트롤러 입구에서 “불량 데이터” 차단 |

---

## 🧾 예시 코드 (Habit)

### HabitRequestDto (검증 추가)

```java
package com.springlab19.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class HabitRequestDto {

    @NotBlank(message = "습관 이름은 필수입니다.")
    private String name;

    @Min(value = 1, message = "하루 목표는 1 이상이어야 합니다.")
    private int goalPerDay;

    public HabitRequestDto() {}

    public HabitRequestDto(String name, int goalPerDay) {
        this.name = name;
        this.goalPerDay = goalPerDay;
    }

    public String getName() { return name; }
    public int getGoalPerDay() { return goalPerDay; }
}

```

---

### HabitController (검증 적용)

```java
@PostMapping
public ResponseEntity<HabitResponseDto> createHabit(@Valid @RequestBody HabitRequestDto requestDto) {
    HabitResponseDto response = habitService.createHabit(requestDto);
    return ResponseEntity
            .created(URI.create("/api/habits/" + response.getId()))
            .body(response); // 201 Created
}

@PutMapping("/{id}")
public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable Long id,
                                                    @Valid @RequestBody HabitRequestDto requestDto) {
    return ResponseEntity.ok(habitService.updateHabit(id, requestDto)); // 200 OK
}

```

---

### 검증 실패 시 응답 예시

```json
{
  "timestamp": "2025-08-20T10:15:30",
  "status": 400,
  "errors": [
    "습관 이름은 필수입니다.",
    "하루 목표는 1 이상이어야 합니다."
  ]
}

```

---

## 📌 포인트 요약

- DTO 레벨에서 입력값 제약을 선언 → 코드/DB에 도달하기 전 차단
- `@Valid`와 검증 어노테이션만 붙이면 Spring이 자동으로 400 반환
- 검증 실패 메시지는 커스터마이즈 가능 (`message` 속성)
- `BindingResult`를 쓰면 개발자가 직접 응답 포맷 제어 가능 (API-3에서 본격화)
- API-0/1은 “무조건 저장” 구조 → API-2부터 **입력 검증 체계**를 갖춤

---

## 🧪 실습 미션 (Diary)

🎯 목표: **Diary API에 Bean Validation을 적용하여 불량 입력을 자동 차단한다.**

1. `DiaryRequestDto` 작성 → 필드 제약 추가
    - `title`: `@NotBlank(message = "제목은 필수입니다.")`
    - `content`: `@NotBlank(message = "내용은 비워둘 수 없습니다.")`
2. `DiaryController`에 `@Valid` 적용
    - POST/PUT 요청 시 DTO 검증 실행
3. Postman 테스트 →
    - title 누락 → 400 반환
    - content 빈 문자열 → 400 반환
4. 검증 실패 응답 메시지가 클라이언트에서 확인되도록 설정

> 💡 이 단계에서는 “입력 검증”만 집중!
> 
> 
> 전역 에러 포맷 표준화는 API-3에서 다룸.
>