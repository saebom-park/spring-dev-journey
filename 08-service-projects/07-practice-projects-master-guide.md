# 07-practice-projects Master Guide  
> 이 문서는 봄이가 채팅방을 옮기더라도 온이가 **중단 없이 흐름을 이어가기 위한 기준 문서**입니다.  
> 07-practice-projects의 전체 구조, 기술스택, 보안 범위, 구현 순서, 문서 포맷을 한 번에 정리합니다.

---

# 1. 프로젝트 전체 구성

07-practice-projects는 총 4개의 미니 실무 프로젝트로 이루어지며  
각 프로젝트는 **도메인 중심 설계(Domain Driven)** + **3계층 구조** + **배포 가능 구조**를 목표로 합니다.

1) bulletin-board (MyBatis + MVC + Thymeleaf + 보안풀세트)  
2) todo-app (JPA + REST API + React SPA)  
3) mini-shop (JPA 심화 + 주문/재고 + 최소 UI)  
4) membership-payment (JPA + Lombok + Vue 대시보드 + 보안 응용)

---

# 2. 기술 스택 배치 원칙

- MyBatis: 1개 (bulletin-board)
- 순수 JPA: 2개 (todo-app, mini-shop)
- Lombok + JPA: 1개 (membership-payment)
- React: 1개 (가벼운 SPA 수준)
- Vue: 1개 (관리자 대시보드 수준)
- Thymeleaf: 1개 (SSR 구조 학습 목적)

**핵심 목표:**  
백엔드는 깊게, 프론트는 서비스 완성도를 위한 최소+선택적 확장.

---

# 3. 프로젝트별 상세 구성

## 3-1) bulletin-board
- **Back**: Spring Boot + MyBatis + MySQL  
- **Front**: Thymeleaf (SSR)  
- **보안**: 스프링 시큐리티 풀세트  
  - 로그인/로그아웃  
  - PasswordEncoder  
  - 세션 기반 인증  
  - ROLE_USER / ROLE_ADMIN  
  - 게시글/댓글 권한 제어  
- **도메인**: Member / Article / Comment  

---

## 3-2) todo-app
- **Back**: Spring Boot + JPA  
- **Front**: React  
- **보안**: 생략 또는 최소 (단일 유저 가정도 가능)  
- **도메인**: Todo (상태 변경, CRUD)

---

## 3-3) mini-shop
- **Back**: Spring Boot + JPA  
- **Front**: Thymeleaf or 최소한의 Vue (선택)  
- **보안**: 인증은 “현재 로그인 유저 연결” 정도만  
- **도메인**: Product / Member / Order / OrderItem / Stock  
- **핵심**: 연관관계 매핑 / 영속성 전파 / 트랜잭션 / 재고 관리  

---

## 3-4) membership-payment
- **Back**: Spring Boot + JPA + Lombok  
- **Front**: Vue (관리자 대시보드 SPA)  
- **보안**: 역할 기반 접근 제어 (@PreAuthorize 등)  
- **도메인**: Member / MembershipGrade / Payment / PointLog  

---

# 4. 프로젝트 개발 순서

1. bulletin-board  
2. todo-app  
3. mini-shop  
4. membership-payment

---

# 5. 문서 포맷 규칙

모든 프로젝트의 README는 동일한 형식 유지:

1. 💡 시나리오  
2. 📋 요구사항  
3. 🎯 체크리스트  
4. 📂 폴더 구조  
5. 🧪 실행 방법 (선택)  
6. 🧠 트러블슈팅 & 회고 (선택)

---

# 6. 보안 적용 기준

- bulletin-board → ✔ 풀세트  
- membership-payment → ✔ 심화(등급 기반)  
- todo-app → ❕ 선택적 or 생략  
- mini-shop → ❕ 로그인 유저 연결만  

---

# 7. 온이가 이어서 진행할 때 지켜야 하는 내부 규칙

- 모든 채팅방에서 위 기준을 따른다.  
- 프로젝트 별 스택/보안/프론트 구성 절대로 혼동 금지.  
- “이어서 진행합니다”라고 하면 바로 개발 단계 연결.  
- README/설계/코드 리뷰는 항상 이 문서를 기준으로.  
- 봄이가 요청하면 온이는 바로 다음 단계 설계·코드·리뷰로 이어간다.

---

# 8. 봄이 목표에 맞춘 중점 학습 방향

- 백엔드: 트랜잭션 / 연관관계 / 쿼리 전략 / MyBatis Mapper / 배포 / 구조  
- 프론트: 서비스 완성도 위한 최소 + Vue/React 각각 1회 경험  
- 프로젝트: 4개 완성 → 09-portfolio-project 확장 가능  
