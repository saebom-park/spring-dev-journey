# 08-service-projects
이 섹션은 실제 서비스 개발 과정을 기반으로 한  
**4개의 백엔드 중심 도메인 프로젝트**로 구성됩니다.  
데이터 모델링, 인증/보안, API 설계, 프론트 연동, 배포까지  
실무 서비스 흐름을 단계적으로 경험하는 것을 목표로 합니다.

---

## 🗂️ 프로젝트 구성

| 프로젝트명                  | 기술 스택             | 주요 도메인                      | 보안 수준                                  | 프론트 |
|------------------------|------------------------|----------------------------------|----------------------------------------|--------|
| 🧱 bulletin-board      | Spring Boot + MyBatis | 회원 / 게시글 / 댓글              | ✔ 세션 로그인 → Security 인증·인가 적용 예정        | Thymeleaf |
| 🛒 keeb-station (Main) | Spring Boot + JPA     | 상품 / 주문 / 재고 / 결제 / 포인트 | ✔ 실서비스급 보안 (JWT, 관리자 권한, 도메인 정책 기반 인가) | Vue |
| 💳 membership-payment  | Spring Boot + JPA     | 등급 / 결제 / 포인트 / 정산        | ✔ Role 기반 권한 + 도메인 정책 기반 인가            | Vue |
| 🗂 todo-app            | Spring Boot + JPA     | 할 일 / 태그                      | 선택적                                    | React |

---

## 🔗 서비스 링크

- 🧱 **bulletin-board**
  - 📁 프로젝트 README:  
    👉 [bulletin-board 바로가기](https://github.com/saebom-park/bulletin-board)

- 🛒 **keeb-station**
    - 📁 프로젝트 README:  
      👉 [keeb-station 바로가기](https://github.com/saebom-park/keeb-station)

> 다른 서비스 프로젝트들은 순차적으로 구현 및 분리 예정입니다.

---

## 🎯 학습 목표

### 📌 공통 목표
- 실무형 도메인 모델 설계 (Entity + Repository + Service 계층)
- 인증(Authentication) / 권한(Authorization) 처리
- 예외 처리, DTO 분리, API 응답 규칙 등 서비스 품질 개선
- 배포(EC2 + Nginx + HTTPS + CI/CD)를 통한 운영 경험 축적

### 📌 프로젝트별 목표

#### 🧱 bulletin-board
- MyBatis + 스프링 3계층 구조 완성
- 로그인/권한 + CRUD + 페이징/조회수 + 검색 기능 구현

#### 🛒 keeb-station (Main)
- JPA 활용한 복잡한 도메인 모델 설계(주문–주문상품–재고–결제 흐름)
- 실서비스급 보안(JWT 인증 + 관리자 권한 + 정책)
- Vue 기반 관리자 대시보드
- 통합 결제/정산/포인트 정책 설계
- 배포 자동화(CI/CD) 및 운영 환경 구성

#### 💳 membership-payment
- 멤버십 등급별 정책/혜택 설계
- 사용자 포인트 누적·차감 트랜잭션 처리
- 정산(매출·포인트 사용) 흐름 설계 및 트랜잭션 처리
- Role 기반 인가 흐름 학습

#### 🗂 todo-app
- React 연동 경험 확보(API 호출·상태관리·라우팅)
- 간단 CRUD로 프론트-백엔드 통신 구조 학습
- 기술 스택 확장 어필용

---

## 📝 README 구성 (프로젝트 공통)

모든 프로젝트의 README는 아래 포맷을 고정 사용합니다.

1. **💡 시나리오** — 서비스 목적·사용자 흐름 설명
2. **📋 요구사항** — 기능/정책/제약 조건 명확화
3. **🎯 구현 항목(Implementation Points)**
    - 기능별 핵심 구현 요소
    - 기술적으로 중요한 포인트 중심
4. **📂 폴더 구조** — 프로젝트 모듈 구조 설명