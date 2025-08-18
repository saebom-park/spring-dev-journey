# API-1 Todo 실습 사양

## 엔티티
- Todo
  - id (Long, PK)
  - title (String)
  - completed (boolean)

## 요구사항
1. Todo 등록
2. Todo 전체 조회
3. Todo 단건 조회
4. Todo 수정
5. Todo 삭제

## 제약 조건
- Service 계층에 @Transactional 적용
- Controller 계층에서 ResponseEntity로 응답 반환
- 입력값 검증, 전역 예외처리는 이번 단계에서 제외

## 엔드포인트
- POST   /api/todos
- GET    /api/todos
- GET    /api/todos/{id}
- PUT    /api/todos/{id}
- DELETE /api/todos/{id}

## 요청/응답 예시

### POST /api/todos
Request
```json
{
  "title": "공부하기",
  "completed": false
}
```

Response (201 Created)
```json
{
  "id": 1,
  "title": "공부하기",
  "completed": false
}
```
