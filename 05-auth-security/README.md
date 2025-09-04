# 📎 05. 인증 & 보안 (Auth & Security)

이 섹션에서는 로그인 기능의 핵심인 **인증(Authentication)** 과 **권한(Authorization)** 을 학습한다.  
Spring Security의 기본 구조부터 세션/토큰 기반 인증, 권한 제어, 비밀번호 암호화까지 실습을 통해 이해한다.  
최종적으로는 **JWT 기반 인증 시스템**을 직접 구현해본다.

---

## ✅ 학습 목표
- 세션 기반 로그인 흐름 이해 및 구현
- JWT 토큰 발급 및 검증 처리
- Spring Security 필터 체인 동작 구조 학습
- 사용자 역할(Role) 기반 접근 제어 정책 구성
- 안전한 비밀번호 저장 방식(BCryptPasswordEncoder) 적용

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| 세션 로그인 | HttpSession 기반 로그인/로그아웃 처리 |
| JWT 인증 | 토큰 발급 → 헤더 전달 → 필터에서 검증 |
| 스프링 시큐리티 구조 | 기본 필터 체인, DelegatingFilterProxy 동작 이해 |
| 권한 설정 | ROLE_USER, ROLE_ADMIN 등 Role 기반 접근 제어 |
| 비밀번호 암호화 | BCryptPasswordEncoder 기반 단방향 해싱 |
| 인가 처리 | URL, 메서드 단위 권한 제어 (`@PreAuthorize`, `hasRole`) |
| 세션 vs 토큰 | 상태 기반 인증 vs 무상태 인증 비교 |

---

## 📂 문서 구성

| 파일명 | 설명 |
|--------|------|
| sec-1-session-login.md | 세션 기반 로그인 |
| sec-2-jwt-auth.md | JWT 인증 |
| sec-3-security-filter-chain.md | Spring Security 필터 체인 |
| sec-4-role-authorization.md | 권한 설정 |
| sec-5-password-encoding.md | 비밀번호 암호화 |
| sec-*-mistakes.md | 각 단계별 실수노트 |
| sec-*-questions.md | 각 단계별 질문노트 |
| sec-*-extra.md | 추가 개념 정리 (있을 경우) |

---

## 🧭 학습 흐름
1. **SEC-1**: 세션 기반 로그인 (HttpSession 활용)
2. **SEC-2**: JWT 기반 인증 흐름 구현
3. **SEC-3**: Spring Security 필터 체인 구조 이해 및 설정
4. **SEC-4**: 사용자 권한(Role) 기반 접근 제어
5. **SEC-5**: 비밀번호 암호화 적용 및 보안 강화

---

## 📌 작성 기준
- Spring Security 6.x 기준 (SecurityFilterChain 사용)
- JWT는 Access Token 중심 구성
- Controller / Service / Repository 3계층 구조 유지
- 설정 클래스와 커스텀 필터 분리 설계

---

> **“보안은 선택이 아니라 필수다.”**  
> 안전한 인증 시스템을 설계하는 능력이 백엔드 개발자의 기본 자격이다. 🔒