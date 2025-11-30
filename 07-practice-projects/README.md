# 🧷 07. 실무형 미니 프로젝트 (Practice Projects)

> 07-practice-projects는 전체 서비스 아키텍처 경험을 목표로 한  
> **4개의 백엔드 중심 실무형 도메인 프로젝트 그룹**입니다.  
> 모든 프로젝트는 Spring Boot 기반이며,  
> 인증/보안/프론트/배포까지 실제 서비스 구조를 고려하여 설계합니다.

---

## ✅ 프로젝트 목록

| 프로젝트명 | 기술 스택 | 도메인 | 보안 | 프론트 |
|-----------|-----------|--------|-------|---------|
| 🧱 bulletin-board | Spring Boot + MyBatis | 회원/게시글/댓글 | ✔ 로그인·권한 풀세트 | Thymeleaf |
| 🗂 todo-app | Spring Boot + JPA | 할 일 관리 | ❕ 선택적 | React |
| 🛒 mini-shop | Spring Boot + JPA | 주문/상품/재고 | ❕ 최소 | (선택) Thymeleaf/Vue |
| 💳 membership-payment | Spring Boot + JPA + Lombok | 멤버십·결제·포인트 | ✔ 등급 기반 권한 | Vue 대시보드 |

---

## 🎯 학습 목표

- 도메인 중심 설계(DDD-lite)  
- MyBatis + JPA 혼합 경험  
- REST API / MVC / SSR / SPA 전체 흐름  
- 로그인/보안/권한 체계 실전 구현  
- Vue/React를 활용한 프론트 UI 연계  
- AWS EC2 + Nginx 기반 배포 실습  
- 포트폴리오용 서비스 4종 완성

---

## 📂 폴더 구조 예시

```
07-practice-projects/
 ├── bulletin-board/
 │   └── README.md
 ├── todo-app/
 │   └── README.md
 ├── mini-shop/
 │   └── README.md
 └── membership-payment/
     └── README.md
```

---

## 📝 README 구성 (프로젝트 공통)

각 프로젝트의 README는 아래 포맷을 고정 사용합니다.

1. 💡 시나리오  
2. 📋 요구사항  
3. 🎯 체크리스트  
4. 📂 폴더 구조

---

> 모든 프로젝트는 Spring Dev Journey의 백엔드 성장 로드맵을 따라  
> 도메인 설계 능력·보안 실전 경험·전체 서비스 구성 역량을 강화하는 데 초점을 둡니다.