# 🧷 07. 실무형 미니 프로젝트 (Practice Projects)

> 이 폴더는 **김영한 스프링 로드맵 학습과 병행하여**,  
> 실무 수준의 구조 설계와 구현 능력을 강화하는 단계입니다.  
> 각 프로젝트는 도메인 단위로 분리되어 있으며,  
> **설계 → 구현 → 테스트 → 배포 → 문서화** 전 과정을 직접 수행합니다.

---

## ✅ 학습 목표

- 김영한 로드맵에서 배운 개념을 **직접 실무 프로젝트에 적용**  
- 기능 단위가 아닌 **도메인 중심 설계 (DDD-lite)** 훈련  
- Controller → Service → Repository 계층형 아키텍처 완전 정착  
- MyBatis와 JPA를 모두 활용한 데이터 접근 기술 숙련  
- AWS EC2 + Nginx 기반 **배포 및 운영 환경 구성 경험 확보**  
- 포트폴리오 제출용 **프로젝트 4종 세트 완성**

---

## 🧩 실습 프로젝트 목록

| 프로젝트명 | 도메인 | 주요 기술 | 병행 로드맵 | 배포 목표 |
|-------------|----------|-------------|--------------|-------------|
| 🧱 **bulletin-board** | 회원제 게시판 + 댓글 + 로그인 | Spring Boot + MyBatis | INF-1~2 (Core/HTTP) | ✅ EC2 + Nginx |
| ✅ **todo-app** | 일정 관리 + 상태 변경 | Spring Boot + JPA | INF-3~4 (MVC 1~2편) | 🚧 배포 예정 |
| 📚 **library-system** | 도서 대출/반납 시스템 | Spring Boot + JPA + Security | INF-5~6 (DB 1~2편) | 🚧 배포 예정 |
| 👥 **team-collab** | 팀 일정 공유 + 알림 | Spring Boot + JPA + OAuth + CI/CD | INF-7~8 (고급/Boot편) | 🚧 배포 예정 |

> ⚙️ `bulletin-board`는 **MyBatis 기반**으로 진행하며,  
> 이후 모든 프로젝트는 **JPA 기반**으로 전환합니다.  
>  
> 이 4개 도메인은 포트폴리오 제출용으로 발전시킬 예정입니다.

---

## 📂 폴더 구조 예시

```
07-practice-projects/
├── bulletin-board/
│   ├── backend/
│   │   ├── src/
│   │   └── build.gradle
│   ├── docs/
│   │   └── README.md
│   └── frontend/  (선택)
├── todo-app/
├── library-system/
└── team-collab/
```

---

## 📝 각 프로젝트 문서 구성

모든 프로젝트의 `README.md`는 다음 형식을 고정 사용합니다.

1. 💡 시나리오  
2. 📋 요구사항  
3. 🎯 체크리스트  
4. 📂 폴더 구조  
5. 🧪 실행 방법 (선택)  
6. 🧠 트러블슈팅 & 회고 (선택)

---

## 🛠️ 개발 고정 기준

- **커밋 메시지:** [Conventional Commits](https://www.conventionalcommits.org/) 스타일 고정  
- **설계 구조:** Controller → Service → Repository → Domain  
- **도메인 설계:** JPA 중심 설계 (단, 1단계는 MyBatis 예외)  
- **문서 포맷:** `spring-doc-format-rules.md` 기준 준수  
- **코드 실행:** 모든 예시는 `public class` 포함한 완성형 구조로 제공  
- **DB 연결:** MySQL 기반, 실습별 `application.yml`로 분리

---

> “진짜 실력은, 개념을 손으로 구현해보는 순간 만들어진다.”  
>  
> 각 도메인을 직접 설계하고 배포하면서  
> **스프링 백엔드 실무 개발자**로 성장하는 여정입니다.  
>  
> ✅ 실무 포트폴리오 통합 프로젝트는 `09-portfolio-project` 폴더에서 진행됩니다.
