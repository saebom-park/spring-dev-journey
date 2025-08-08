# 📋 API 명세서 - 회원제 게시판 시스템

## 📌 개요
회원제 게시판 시스템의 REST API 명세서입니다.  
로그인한 사용자만 게시글 작성 및 댓글 등록이 가능합니다.

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
  "timestamp": "2025-08-08T10:30:00"
}
```

### 실패 응답
```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "입력값이 올바르지 않습니다.",
    "details": ["이메일 형식이 올바르지 않습니다."]
  },
  "timestamp": "2025-08-08T10:30:00"
}
```

---

## 🔐 인증 API

### 1. 회원가입
```http
POST /api/auth/register
```

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "홍길동"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "email": "user@example.com",
    "name": "홍길동",
    "createdAt": "2025-08-08T10:30:00"
  },
  "message": "회원가입이 완료되었습니다."
}
```

### 2. 로그인
```http
POST /api/auth/login
```

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "user": {
      "id": 1,
      "email": "user@example.com",
      "name": "홍길동"
    },
    "sessionId": "session_12345"
  },
  "message": "로그인이 성공했습니다."
}
```

### 3. 로그아웃
```http
POST /api/auth/logout
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
```

**Response (성공):**
```json
{
  "success": true,
  "message": "로그아웃이 완료되었습니다."
}
```

### 4. 현재 사용자 정보 조회
```http
GET /api/auth/me
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "email": "user@example.com",
    "name": "홍길동"
  }
}
```

---

## 📝 게시글 API

### 1. 게시글 목록 조회
```http
GET /api/posts?page=0&size=10
```

**Query Parameters:**
- `page`: 페이지 번호 (0부터 시작, 기본값: 0)
- `size`: 페이지 크기 (기본값: 10)

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": 1,
        "title": "첫 번째 게시글",
        "content": "게시글 내용입니다.",
        "author": {
          "id": 1,
          "name": "홍길동"
        },
        "commentCount": 3,
        "createdAt": "2025-08-08T10:30:00",
        "updatedAt": "2025-08-08T10:30:00"
      }
    ],
    "page": {
      "number": 0,
      "size": 10,
      "totalElements": 25,
      "totalPages": 3
    }
  }
}
```

### 2. 게시글 상세 조회
```http
GET /api/posts/{postId}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "첫 번째 게시글",
    "content": "게시글 내용입니다.",
    "author": {
      "id": 1,
      "name": "홍길동"
    },
    "comments": [
      {
        "id": 1,
        "content": "첫 번째 댓글입니다.",
        "author": {
          "id": 2,
          "name": "김철수"
        },
        "createdAt": "2025-08-08T11:00:00"
      }
    ],
    "createdAt": "2025-08-08T10:30:00",
    "updatedAt": "2025-08-08T10:30:00"
  }
}
```

### 3. 게시글 작성
```http
POST /api/posts
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
Content-Type: application/json
```

**Request Body:**
```json
{
  "title": "새 게시글 제목",
  "content": "게시글 내용입니다."
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 2,
    "title": "새 게시글 제목",
    "content": "게시글 내용입니다.",
    "author": {
      "id": 1,
      "name": "홍길동"
    },
    "createdAt": "2025-08-08T12:00:00"
  },
  "message": "게시글이 작성되었습니다."
}
```

### 4. 게시글 수정
```http
PUT /api/posts/{postId}
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
Content-Type: application/json
```

**Request Body:**
```json
{
  "title": "수정된 제목",
  "content": "수정된 내용입니다."
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "수정된 제목",
    "content": "수정된 내용입니다.",
    "author": {
      "id": 1,
      "name": "홍길동"
    },
    "updatedAt": "2025-08-08T12:30:00"
  },
  "message": "게시글이 수정되었습니다."
}
```

### 5. 게시글 삭제
```http
DELETE /api/posts/{postId}
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
```

**Response (성공):**
```json
{
  "success": true,
  "message": "게시글이 삭제되었습니다."
}
```

---

## 💬 댓글 API

### 1. 댓글 목록 조회
```http
GET /api/posts/{postId}/comments
```

**Response (성공):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "content": "첫 번째 댓글입니다.",
      "author": {
        "id": 2,
        "name": "김철수"
      },
      "createdAt": "2025-08-08T11:00:00"
    }
  ]
}
```

### 2. 댓글 작성
```http
POST /api/posts/{postId}/comments
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
Content-Type: application/json
```

**Request Body:**
```json
{
  "content": "새로운 댓글입니다."
}
```

**Response (성공):**
```json
{
  "success": true,
  "data": {
    "id": 2,
    "content": "새로운 댓글입니다.",
    "author": {
      "id": 1,
      "name": "홍길동"
    },
    "createdAt": "2025-08-08T13:00:00"
  },
  "message": "댓글이 등록되었습니다."
}
```

### 3. 댓글 삭제
```http
DELETE /api/comments/{commentId}
```

**Headers:**
```
Cookie: JSESSIONID=session_12345
```

**Response (성공):**
```json
{
  "success": true,
  "message": "댓글이 삭제되었습니다."
}
```

---

## ⚠️ 에러 코드

| HTTP Status | Error Code | 설명 |
|-------------|------------|------|
| 400 | VALIDATION_ERROR | 입력값 검증 실패 |
| 401 | UNAUTHORIZED | 인증 실패 (로그인 필요) |
| 403 | FORBIDDEN | 권한 없음 (본인 글/댓글이 아님) |
| 404 | NOT_FOUND | 리소스를 찾을 수 없음 |
| 409 | CONFLICT | 이미 존재하는 데이터 (중복 이메일 등) |
| 500 | INTERNAL_SERVER_ERROR | 서버 내부 오류 |

---

## 📋 개발 체크리스트

### 백엔드 (Spring Boot)
- [ ] User, Post, Comment 엔티티 설계
- [ ] JPA Repository 구성
- [ ] 인증 API 구현 (세션 기반)
- [ ] 게시글 CRUD API 구현
- [ ] 댓글 CRUD API 구현
- [ ] 예외 처리 및 응답 형태 통일
- [ ] 권한 검증 (본인만 수정/삭제)

### 프론트엔드 (Vue)
- [ ] 라우터 설정 (로그인, 목록, 상세, 작성)
- [ ] 로그인/회원가입 컴포넌트
- [ ] 게시글 목록 컴포넌트
- [ ] 게시글 상세/댓글 컴포넌트
- [ ] 게시글 작성/수정 컴포넌트
- [ ] API 연동 (Axios)
- [ ] 인증 상태 관리 (Vuex/Pinia)

---

**작성일**: 2025-08-08  
**작성자**: 루이 & 봄이