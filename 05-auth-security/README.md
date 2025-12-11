# 05-auth-security
로그인 기능의 핵심 개념인 인증(Authentication)과 권한(Authorization)을 실습 중심으로 학습하는 단계입니다.  
세션 기반 인증부터 JWT 기반 인증까지 구현하며 Spring Security의 구조를 이해합니다.

---

## 🗂️ 구성 안내
- `docs/` : 인증/인가, JWT, Security Filter Chain 등 단계별 개념 정리 문서
- 이 섹션은 **개념 이해 중심**이므로 코드 기반 오류가 적어, mistakes 문서는 대부분 생성되지 않습니다.

---

## 🎯 학습 목표
- HttpSession 기반 로그인·로그아웃 흐름 이해
- JWT 토큰 발급 및 검증 구조 구현
- Spring Security Filter Chain 동작 방식 이해
- 사용자 역할(Role) 기반 접근 제어 설계
- BCryptPasswordEncoder 기반 안전한 비밀번호 저장 방식 적용
- 세션 인증과 토큰 인증의 차이 이해

---

## 🧠 학습 구성 (총 5단계)

| 단계    | 주제                    | 설명                                                |
|-------|-----------------------|---------------------------------------------------|
| SEC-1 | 세션 기반 로그인             | HttpSession 로그인/로그아웃 구현                           |
| SEC-2 | JWT 인증                | Access Token 발급 → 헤더 전달 → 필터 검증                   |
| SEC-3 | Spring Security 필터 체인 | DelegatingFilterProxy → SecurityFilterChain 구조 이해 |
| SEC-4 | 권한 제어(Authorization)  | Role 기반 접근 제어 (`hasRole`, `@PreAuthorize`)        |
| SEC-5 | 비밀번호 암호화              | BCryptPasswordEncoder 적용                          |

---

## 📂 문서 구성 규칙

이 섹션에서는 개념 이해 중심이라 **mistakes 문서가 생성되지 않을 수 있습니다.**

| 파일명 형식                       | 설명                              |
|------------------------------|---------------------------------|
| `sec-<단계>-<주제>.md`           | 기본 수업자료 (개념 요약 · 코드 흐름 · 설정 설명) |
| `sec-<단계>-<주제>-questions.md` | 학습 중 발생한 질문 정리 (필요 시 생성)        |
| `sec-<단계>-<주제>-extra.md`     | 추가 개념/흐름 정리 (필요 시 생성)           |

---

## 🧭 학습 흐름

1. **SEC-1 — 세션 로그인 흐름 파악**
    - 로그인/로그아웃 처리
    - 세션 저장 방식과 JSESSIONID 이해

2. **SEC-2 — JWT 인증 구현**
    - Access Token 생성
    - 커스텀 필터에서 토큰 검증
    - Header → SecurityContext 저장 흐름

3. **SEC-3 — Security Filter Chain 구조 이해**
    - DelegatingFilterProxy → Filter Chain 동작
    - 인증/인가 처리 순서 파악

4. **SEC-4 — 권한 제어(Authorization)**
    - Role 기반 접근 제어
    - URL / 메서드 단위 인가 처리

5. **SEC-5 — 비밀번호 암호화**
    - BCryptPasswordEncoder 기반 단방향 해싱
    - 안전한 비밀번호 저장 방식 구축

---

## 📌 작성 기준

- Spring Security 6.x 기준 (`SecurityFilterChain` 사용)
- JWT는 Access Token 중심 구조
- Controller → Service → Repository 계층 유지
- Security 설정 클래스와 커스텀 필터 분리 설계
- Role 기반 권한 정책 설계 가능