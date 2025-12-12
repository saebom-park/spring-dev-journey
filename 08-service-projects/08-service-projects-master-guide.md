# 08-service-projects Master Guide  
> 이 문서는 **끊김 없이 동일한 흐름으로 이어가기 위한 기준 문서**입니다.  
> 08-service-projects의 전체 구조, 보안 수준, 기술 스택, 구현 순서, 문서 포맷을 “최신 기준”으로 통합 정리합니다.

---

## 1. 프로젝트 전체 구성 (최신 확정 버전)

| 프로젝트 | 기술 스택 | 주요 도메인 | 보안 수준 | 프론트 |
|---------|-----------|--------------|------------|---------|
| **bulletin-board** | Spring Boot + MyBatis | 회원 / 게시글 / 댓글 | ✔ 세션 로그인 → Security 적용 예정 | Thymeleaf |
| **mini-shop (Main)** | Spring Boot + JPA | 상품 / 주문 / 재고 / 결제 / 포인트 | ✔ 실서비스급 보안 (JWT, 관리자 권한, 도메인 정책 기반 인가) | Vue |
| **membership-payment** | Spring Boot + JPA | 등급 / 결제 / 포인트 / 정산 | ✔ Role 기반 권한 + 정책 기반 인가 | Vue |
| **todo-app** | Spring Boot + JPA | 할 일 / 태그 | 선택적 | React |

---

## 2. 기술 스택 배치 원칙

- **MyBatis** → bulletin-board  
- **JPA 심화** → mini-shop / membership-payment  
- **프론트 경험 확장**  
  - Vue → mini-shop, membership-payment  
  - React → todo-app  
- **보안 단계적 적용**  
  - bulletin-board → 세션 → Security  
  - mini-shop → JWT + 관리자 정책  
  - membership-payment → Role + 정책 기반 인가  
  - todo-app → 선택적

---

## 3. 프로젝트별 상세 구성

### 🧱 bulletin-board
- Spring Boot + MyBatis  
- Thymeleaf 기반 SSR  
- 세션 로그인 → Spring Security로 전환 예정  
- 게시판 핵심 기능: CRUD / 조회수 / 페이징 / 검색  
- 작성자 체크 + 관리자 권한 포함

---

### 🛒 mini-shop (Main Project)
- Spring Boot + JPA  
- Vue 관리자 대시보드  
- JWT 인증 + Role + 정책 기반 인가  
- 핵심 도메인  
  - 주문 / 주문상품 / 재고 / 결제 / 포인트  
- 실서비스 수준 트랜잭션 & 정책 설계  
- CI/CD + 배포까지 완료

---

### 💳 membership-payment
- Spring Boot + JPA  
- Vue 기반 관리 화면  
- Role 기반 인가 + 도메인 정책  
- 핵심 도메인: MembershipGrade / Payment / PointLog  
- 트랜잭션·정산 모델 설계 포함

---

### 🗂 todo-app
- Spring Boot + JPA  
- React SPA  
- 가장 단순한 도메인  
- React 기반 API 연동 경험 확보 목적

---

## 4. 개발 순서 (확정 로드맵)

1. **bulletin-board** (현재 진행 중)  
2. **mini-shop (Main Project)**  
3. **membership-payment**  
4. **todo-app (React 경험용)**  

---

## 5. 문서 포맷 규칙

모든 프로젝트 README 공통 포맷:

1. **💡 시나리오** — 서비스 목적·사용자 흐름 설명  
2. **📋 요구사항** — 기능/정책/제약 조건 명확화  
3. **🎯 구현 항목 (Implementation Points)**  
4. **📂 폴더 구조**  

---

## 6. 보안 적용 수준 정리

| 프로젝트 | 보안 레벨 |
|---------|-----------|
| bulletin-board | 세션 → Security(단순 인가) |
| mini-shop | JWT + 관리자 권한 + 정책 기반 인가 |
| membership-payment | Role 기반 + 정책 기반 인가 |
| todo-app | 선택적 |
