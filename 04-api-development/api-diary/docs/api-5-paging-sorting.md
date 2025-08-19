# [API-5단계] 조회 API 확장 (paging-sorting)

> 이번 단계에서는 findAll()만 사용하던 단순 조회를 개선합니다.
> 
> 
> Spring Data JPA의 **페이징(Pageable)**과 **정렬(Sort)** 기능을 적용해,
> 
> 대용량 데이터를 효율적으로 조회하는 방법을 학습합니다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Pageable | `page`, `size`, `sort` 파라미터로 페이징·정렬 제어 |
| Page<T> | 페이징 결과를 담는 객체 (content, totalElements, totalPages 등 포함) |
| Slice<T> | 다음 페이지 여부만 확인하는 경량 페이징 |
| 정렬 | `sort=필드명,asc` / `sort=필드명,desc` |
| 실무 포인트 | `findAll()`은 성능·응답 모두 비효율 → 페이징/정렬은 API 기본기 |

---

## 🧾 예시 코드 (Habit)

### Controller

```java
@GetMapping
public ResponseEntity<Page<HabitResponseDto>> getHabits(Pageable pageable) {
    Page<HabitResponseDto> response = habitService.getHabits(pageable);
    return ResponseEntity.ok(response);
}

```

---

### Service

```java
@Override
@Transactional(readOnly = true)
public Page<HabitResponseDto> getHabits(Pageable pageable) {
    return habitRepository.findAll(pageable)
            .map(habit -> new HabitResponseDto(habit.getId(), habit.getName(), habit.getGoalPerDay()));
}

```

---

### Repository (기존 그대로)

```java
public interface HabitRepository extends JpaRepository<Habit, Long> {
}

```

---

### 요청 예시

- `GET /api/habits?page=0&size=5&sort=goalPerDay,desc`

응답(JSON):

```json
{
  "content": [
    { "id": 1, "name": "운동", "goalPerDay": 3 },
    { "id": 2, "name": "독서", "goalPerDay": 2 }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  },
  "totalPages": 10,
  "totalElements": 50,
  "last": false}

```

---

## 📌 포인트 요약

- `Pageable` 인터페이스 자동 매핑 (`page`, `size`, `sort` 파라미터)
- `Page<T>` 객체를 통해 totalElements, totalPages, next 여부 제공
- 실무에서는 **대용량 데이터**일수록 `findAll()` → ❌, 페이징 → ✅
- API 설계 시 응답 DTO를 감싸 별도 페이징 응답 포맷 정의하기도 함
- API-0~4까지 만든 CRUD를 **실무용 조회 API**로 업그레이드

---

## 🧪 실습 미션 (Diary)

🎯 목표: **Diary API에 페이징/정렬을 적용한다.**

1. `DiaryRepository`를 `JpaRepository<Diary, Long>` 상속
2. `DiaryService`에 `Page<DiaryResponseDto> getDiaries(Pageable pageable)` 추가
3. `DiaryController`에 GET `/api/diaries` 구현
    - `page`, `size`, `sort` 파라미터 지원
4. 더미 데이터 50개 이상 넣고 Postman 테스트
    - `page=0&size=5` → 첫 페이지 확인
    - `sort=createdDate,desc` → 최신순 정렬 확인
5. 응답 JSON에서 totalPages, totalElements, content 구조 확인