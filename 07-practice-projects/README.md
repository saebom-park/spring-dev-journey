# 🧷 07. 실전 프로젝트 모음 (Practice Projects)

> 이 폴더는 자바 백엔드 개발자로 성장하기 위한  
> **실제 프로젝트 실습 공간**입니다.  
> 작은 토이부터 포트폴리오용 중형 프로젝트까지 단계별로 도전합니다.

---

## ✅ 실전 프로젝트 목표

- 요구사항 분석 → ERD 설계 → API 설계 → 구현 → 배포까지  
  **실제 개발 사이클**을 경험합니다.
- 다양한 기능(회원가입, 게시판, 로그인 등)을 직접 구현하며  
  개념과 실무 감각을 연결합니다.
- GitHub에 포트폴리오용 문서까지 함께 정리합니다.

---

## 🧩 추천 프로젝트 목록

| 프로젝트명 | 내용 | 주요 기술 | 난이도 |
|------------|------|-----------|--------|
| `member-crud` | 회원 등록/조회/수정/삭제 API | Spring Boot, JPA, H2 | ⭐ |
| `board-api` | 게시글 목록, 댓글 관리 | Spring Boot, MyBatis | ⭐⭐ |
| `todo-app` | 일정 관리 기능 + 상태 변경 | Spring Boot, JPA | ⭐⭐ |
| `library-system` | 도서관 대출/반납 + 관리자 기능 | Spring Boot, JPA, Security | ⭐⭐⭐ |
| `team-collab` | 팀 협업 일정 공유 시스템 | Spring Boot, JPA, OAuth, CI/CD | ⭐⭐⭐⭐ |

> 난이도는 별 1~4 기준입니다.  
> 점진적으로 도전하며 실력을 다져보세요!

---

## 📂 폴더 구성 예시

07-practice-projects/
├── member-crud/
│ ├── src/ # 프로젝트 소스 코드
│ ├── README.md # 프로젝트 설명 문서
│ ├── domain/, controller/, ... # 기능별 패키지
├── board-api/
├── todo-app/
├── ...

---

## 📝 문서 포함 항목

각 프로젝트별 `README.md`에는 아래 항목을 포함합니다:

- 프로젝트 개요 (기획 배경 + 사용 기술)
- 주요 기능 및 화면
- ERD 및 도메인 모델
- API 명세서 (요청/응답 예시 포함)
- 실행 방법 (로컬 & 배포 환경)
- 트러블슈팅 & 회고

---

## 🛠️ 개발 시 참고 기준

- Git 커밋 메시지는 [Conventional Commits](https://www.conventionalcommits.org/) 스타일로
- 폴더 구조는 도메인 기반 계층 설계
- DTO, Entity, Service, Repository 명확하게 분리

---

> “실전에서 나오는 질문이 진짜 실력이다.”  
>  
> 지금까지 쌓아온 지식과 실수를 바탕으로  
> **나만의 프로젝트를 세상에 내보내자!** 🌍
>
> 👉 포트폴리오 제출용 단일 대형 프로젝트는 [08-real-project]에 따로 정리됩니다.
