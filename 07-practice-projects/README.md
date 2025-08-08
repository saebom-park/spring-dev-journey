# 🧷 07. 미니 프로젝트 모음 (Practice Projects)

> 이 폴더는 자바 백엔드 실력을 실무에 가깝게 다지기 위한  
> **기능 단위 중심의 미니 프로젝트 실습 공간**입니다.  
> 실전 프로젝트에 들어가기 전, 구조 복원 능력과 구현 감각을 쌓는 단계입니다.

---

## ✅ 미니 프로젝트 목표

- 회원가입, 게시판, 주문 처리 등 기능 단위로 설계/구현 훈련
- 스프링 핵심 개념을 실제로 사용해보며 실전 감각 내재화
- 반복 가능한 손코딩 기반 구조 복원 훈련 진행
- API 요청/응답, 예외 처리, 트랜잭션 흐름에 익숙해지기
- GitHub에 포트폴리오용 문서까지 함께 정리

---

## 🧩 예시 실습 목록 (기능 중심)

| 프로젝트명 | 내용 | 주요 기술 | 핵심 키워드 |
|------------|------|-----------|-------------|
| `bulletin-board` | 회원제 게시판 + 댓글 + 로그인 | Spring Boot, Vue | 인증, 게시글, 프론트연동 |
| `todo-app` | 일정 관리 + 상태 변경 | Spring Boot, JPA | 상태값, 로직 분기 |
| `library-system` | 도서 대출/반납 시스템 | Spring Boot, JPA, Security | 관리자 기능, 인증 |
| `team-collab` | 팀 일정 공유 + 알림 | Spring Boot, OAuth, CI/CD | 팀 협업, 자동 배포 |

> ⚠️ 현재 실습 중인 프로젝트는 `bulletin-board`입니다.  
> 이후 실습은 진행하면서 순차적으로 채워질 예정입니다.

---

## 📂 폴더 구조 예시

```
07-practice-projects/
├── bulletin-board/
│   ├── backend/
│   ├── frontend/
│   └── README.md
├── todo-app/
├── library-system/
└── team-collab/
```

---

## 📝 각 프로젝트 문서 포함 항목

각 `README.md`는 다음 형식을 따릅니다:

1. 💡 시나리오  
2. 📋 요구사항  
3. 🎯 체크리스트  
4. 📂 폴더 구조  
5. 🧪 실행 방법 (선택)  
6. 🧠 트러블슈팅 & 회고 (선택)

---

## 🛠️ 개발 시 고정 기준

- 커밋 메시지: [Conventional Commits](https://www.conventionalcommits.org/) 스타일
- 프로젝트 구조: Controller → Service → Repository 계층 분리
- 도메인 설계: JPA 기반 설계 및 DTO 분리 원칙
- 문서 포맷: [spring-doc-format-rules.md] 기준 고정

---

> “실전에서 나오는 질문이 진짜 실력이다.”  
> 지금까지 쌓아온 개념을 실제 프로젝트로 연결해보자.  
>  
> ✅ 실무 포트폴리오용 단일 대형 프로젝트는 `08-real-project` 폴더에서 진행됩니다.
