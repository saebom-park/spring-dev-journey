# ✅ API 명세서 - 할일 관리 시스템

## 📌 개요
할일 관리 시스템의 REST API 명세서입니다.  
사용자별 할일 관리, 카테고리 분류, 상태 변경 기능을 제공합니다.

## 🌍 Base URL
```
http://localhost:8080/api
```

## 📝 공통 응답 형태

### 성공 응답
```json
{
  "success": true,
  "data": { ... },
  "message": "요청이 성공적으로 처리되었습니다.",
  "timestamp": "2025-08-14T10:30:00"
}
```

### 실패 응답
```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "입력값이 올바르지 않습니다.",
    "details": ["우선순위는 필수값입니다."]
  },
  "timestamp": "2025-08-14T10:30:00"
}
```

---

## 🔐 사용자 API

### 1. 사용자 등록
```http
POST /api/users
```

**Request Body:**
```json
{
  "userName": "user123",
  "nickName": "봄이"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "userName": "user123",
    "nickName": "봄이",
    "createdAt": "2025-08-14"
  },
  "message": "사용자가 등록되었습니다."
}
```

---

## 📂 카테고리 API

### 1. 카테고리 목록 조회
```http
GET /api/categories?userId={userId}
```

**Response (성공):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "업무",
      "color": "#FF6B6B",
      "nickName": "봄이"
    },
    {
      "id": 2,
      "name": "개인",
      "color": "#4ECDC4",
      "nickName": "봄이"
    }
  ]
}
```

### 2. 카테고리 생성
```http
POST /api/categories
```

**Request Body:**
```json
{
  "name": "취미",
  "color": "#45B7D1"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 3,
    "name": "취미",
    "color": "#45B7D1",
    "nickName": "봄이"
  },
  "message": "카테고리가 생성되었습니다."
}
```

### 3. 카테고리 수정
```http
PUT /api/categories/{categoryId}
```

**Request Body:**
```json
{
  "name": "업무(중요)",
  "color": "#FF4757"
}
```

### 4. 카테고리 삭제
```http
DELETE /api/categories/{categoryId}
```

---

## ✅ 할일 API

### 1. 할일 목록 조회
```http
GET /api/todos?userId={userId}&categoryId={categoryId}&status={status}&page=0&size=10
```

**Query Parameters:**
- `userId`: 사용자 ID (필수)
- `categoryId`: 카테고리 ID (선택)
- `status`: 상태 필터 (PENDING, IN_PROGRESS, COMPLETED)
- `page`: 페이지 번호 (기본값: 0)
- `size`: 페이지 크기 (기본값: 10)

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": 1,
        "content": "Spring Boot 프로젝트 완성하기",
        "status": "IN_PROGRESS",
        "priority": "HIGH",
        "category": {
          "id": 1,
          "name": "업무",
          "color": "#FF6B6B"
        },
        "nickName": "봄이",
        "createdAt": "2025-08-14",
        "completedAt": null,
        "hasSchedule": true,
        "hasRepeat": false
      }
    ],
    "page": {
      "number": 0,
      "size": 10,
      "totalElements": 5,
      "totalPages": 1
    }
  }
}
```

### 2. 할일 상세 조회
```http
GET /api/todos/{todoId}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "content": "Spring Boot 프로젝트 완성하기",
    "status": "IN_PROGRESS",
    "priority": "HIGH",
    "category": {
      "id": 1,
      "name": "업무",
      "color": "#FF6B6B"
    },
    "nickName": "봄이",
    "createdAt": "2025-08-14",
    "completedAt": null,
    "schedule": {
      "id": 1,
      "startDate": "2025-08-14",
      "dueDate": "2025-08-20"
    },
    "repeatSetting": {
      "id": 1,
      "isRepeated": true,
      "repeatStart": "2025-08-14",
      "repeatDue": "2025-12-31",
      "repeatPattern": "WEEKLY",
      "dayOfWeek": [1, 3, 5]
    }
  }
}
```

### 3. 할일 생성
```http
POST /api/todos
```

**Request Body:**
```json
{
  "content": "Vue.js 컴포넌트 작성하기",
  "priority": "MEDIUM",
  "categoryId": 1
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 2,
    "content": "Vue.js 컴포넌트 작성하기",
    "status": "PENDING",
    "priority": "MEDIUM",
    "category": {
      "id": 1,
      "name": "업무",
      "color": "#FF6B6B"
    },
    "nickName": "봄이",
    "createdAt": "2025-08-14",
    "completedAt": null,
    "hasSchedule": false,
    "hasRepeat": false
  },
  "message": "할일이 생성되었습니다."
}
```

### 4. 할일 수정
```http
PUT /api/todos/{todoId}
```

**Request Body:**
```json
{
  "content": "Vue.js 컴포넌트 작성 및 테스트하기",
  "priority": "HIGH",
  "categoryId": 2
}
```

### 5. 할일 상태 변경
```http
PATCH /api/todos/{todoId}/status
```

**Request Body:**
```json
{
  "status": "COMPLETED"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 2,
    "status": "COMPLETED",
    "completedAt": "2025-08-14"
  },
  "message": "할일 상태가 변경되었습니다."
}
```

### 6. 할일 삭제
```http
DELETE /api/todos/{todoId}
```

---

## 📅 일정 API

### 1. 할일에 일정 추가
```http
POST /api/todos/{todoId}/schedule
```

**Request Body:**
```json
{
  "startDate": "2025-08-15",
  "dueDate": "2025-08-20"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "startDate": "2025-08-15",
    "dueDate": "2025-08-20"
  },
  "message": "일정이 추가되었습니다."
}
```

### 2. 일정 수정
```http
PUT /api/schedules/{scheduleId}
```

**Request Body:**
```json
{
  "startDate": "2025-08-16",
  "dueDate": "2025-08-22"
}
```

### 3. 일정 삭제
```http
DELETE /api/schedules/{scheduleId}
```

---

## 🔄 반복 설정 API

### 1. 할일에 반복 설정 추가
```http
POST /api/todos/{todoId}/repeat
```

**Request Body:**
```json
{
  "isRepeated": true,
  "repeatStart": "2025-08-15",
  "repeatDue": "2025-12-31",
  "repeatPattern": "WEEKLY",
  "dayOfWeek": [1, 3, 5]
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "isRepeated": true,
    "repeatStart": "2025-08-15",
    "repeatDue": "2025-12-31",
    "repeatPattern": "WEEKLY",
    "dayOfWeek": [1, 3, 5]
  },
  "message": "반복 설정이 추가되었습니다."
}
```

### 2. 반복 설정 수정
```http
PUT /api/repeat-settings/{repeatId}
```

### 3. 반복 설정 삭제
```http
DELETE /api/repeat-settings/{repeatId}
```

---

## 📊 통계 API

### 1. 할일 통계 조회
```http
GET /api/todos/stats?userId={userId}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "totalTodos": 15,
    "completedTodos": 8,
    "pendingTodos": 5,
    "inProgressTodos": 2,
    "completionRate": 53.3,
    "categoryStats": [
      {
        "categoryName": "업무",
        "totalCount": 10,
        "completedCount": 6
      },
      {
        "categoryName": "개인",
        "totalCount": 5,
        "completedCount": 2
      }
    ]
  }
}
```

---

## 🎨 Enum 값 정의

### TodoStatus (상태)
- `PENDING`: 대기
- `IN_PROGRESS`: 진행중
- `COMPLETED`: 완료

### TodoPriority (우선순위)
- `HIGH`: 높음
- `MEDIUM`: 보통
- `LOW`: 낮음

### RepeatPattern (반복 패턴)
- `DAILY`: 매일
- `WEEKLY`: 매주
- `MONTHLY`: 매월

---

## ⚠️ 에러 코드

| HTTP Status | Error Code | 설명 |
|-------------|------------|------|
| 400 | VALIDATION_ERROR | 입력값 검증 실패 |
| 401 | UNAUTHORIZED | 인증 실패 |
| 403 | FORBIDDEN | 권한 없음 (본인 할일이 아님) |
| 404 | NOT_FOUND | 리소스를 찾을 수 없음 |
| 409 | CONFLICT | 중복된 데이터 |
| 500 | INTERNAL_SERVER_ERROR | 서버 내부 오류 |

---

## 📋 개발 체크리스트

### 백엔드 (Spring Boot)
- [x] User, Todo, Category, Schedule, RepeatSetting 엔티티 설계
- [x] JPA Repository 구성
- [ ] 할일 CRUD API 구현
- [ ] 카테고리 관리 API 구현
- [ ] 상태 변경 API 구현
- [ ] 일정 관리 API 구현
- [ ] 반복 설정 API 구현
- [ ] 통계 API 구현
- [ ] 예외 처리 및 응답 형태 통일

### 프론트엔드 (Vue)
- [ ] 라우터 설정 (목록, 생성, 상세, 편집)
- [ ] 할일 목록 컴포넌트 (필터링 포함)
- [ ] 할일 생성/수정 컴포넌트
- [ ] 카테고리 관리 컴포넌트
- [ ] 상태 변경 UI
- [ ] 일정 설정 컴포넌트
- [ ] 통계 대시보드 컴포넌트
- [ ] API 연동 (Axios)

---

**작성일**: 2025-08-14  
**작성자**: 루이 & 봄이