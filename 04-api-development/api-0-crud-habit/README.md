# [API-0] Habit Tracker CRUD (테스트 미션)

> 💬 이 실습은 본격적인 API-1 수업 전에, 학습자의 현재 실력을 확인하기 위한 **시험용 과제**입니다.  
> JPA + MySQL 기반으로 **순수 CRUD API**를 직접 구현하는 것을 목표로 합니다.

---

## 💡 시나리오

사용자가 매일 반복하는 습관을 기록할 수 있는 **Habit Tracker** API를 설계하려고 합니다.  

예시 습관:
- 물 8잔 마시기 (`goalPerDay = 8`)
- 책 읽기 (`goalPerDay = 1`)
- 운동하기 (`goalPerDay = 2`)

관리자는 다음 기능을 통해 습관을 관리합니다:

- 새로운 습관을 등록할 수 있음  
- 등록된 습관을 조회할 수 있음  
- 습관을 수정하거나 삭제할 수 있음  

---

## 📋 요구사항

- Spring Boot + JPA + MySQL 프로젝트 구성
- `Habit` 엔티티 설계
  - id(Long)
  - name(String)
  - goalPerDay(int)
- Repository, Service, Controller 계층 분리
- CRUD API 구현:
  - `POST /api/habits` → 생성
  - `GET /api/habits/{id}` → 단건 조회
  - `GET /api/habits` → 전체 조회
  - `PATCH /api/habits/{id}` → 수정
  - `DELETE /api/habits/{id}` → 삭제
- **패키지 규칙**: Gradle `group = 'com.springlab19'` ↔ 최상위 패키지 `com.springlab19` (1:1 매핑), 실습 코드는 `com.springlab19.practice` 하위에 배치

---

## 🎯 체크리스트

- ✅ Spring Boot + JPA + MySQL 환경 설정 (build.gradle, application.yml)
- ✅ `Habit` 엔티티 설계
- ✅ `HabitRepository` (JpaRepository 상속) 구현
- ✅ `HabitService` CRUD 로직 구현
- ✅ `HabitController` REST API 매핑
- ✅ Postman으로 API 호출 테스트
- ✅ README에 실행 방법 + 요청/응답 예시 기록

---

## 📂 폴더 구조 (Gradle 표준, **test 제외**)

```
api-0-crud-habit/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── springlab19/
│       │           ├── Api0CrudHabitApplication.java
│       │           └── practice/
│       │               └── habit/
│       │                   ├── domain/
│       │                   │   └── Habit.java
│       │                   ├── repository/
│       │                   │   └── HabitRepository.java
│       │                   ├── service/
│       │                   │   ├── HabitService.java
│       │                   │   └── HabitServiceImpl.java
│       │                   └── controller/
│       │                       └── HabitController.java
│       └── resources/
│           └── application.yml
└── README.md
```
> DTO를 사용한다면 `src/main/java/com/springlab19/practice/habit/dto/`를 추가하세요.

---

## 🚀 제출 방법
1. `api-0-crud-habit` 폴더에 위 구조로 프로젝트 구성  
2. Postman 테스트 캡처 또는 컬렉션 파일 첨부  
3. `README.md`에 실행 방법 & API 예시 작성 후 제출  

---

## 🔜 다음 단계
- 본 미션 결과를 토대로 **API-1 (CRUD 기본 수업)** 난이도 조절 예정
