# 🧷 05. 인증 & 보안 (Auth & Security)

> 이 단계에서는 로그인 기능의 핵심인 인증(Authentication)과 권한(Authorization)을 학습합니다.  
> 스프링 시큐리티의 기본 구조와 JWT 기반 인증 흐름까지 익히게 됩니다.

---

## ✅ 학습 목표

- 스프링 시큐리티 필터 흐름 이해
- 로그인, 로그아웃, 접근 제어 처리 구현
- JWT 토큰 발급 및 검증 흐름 습득
- 사용자 역할(Role)에 따른 보안 정책 구성

---

## 📚 학습 주제

| 주제 | 설명 |
|------|------|
| 스프링 시큐리티 구조 | 기본 필터 체인, 인증 vs 인가 흐름 |
| 로그인 처리 | `UsernamePasswordAuthenticationFilter` 기반 로그인 |
| 비밀번호 암호화 | `BCryptPasswordEncoder` 사용 |
| 사용자 역할 설정 | `ROLE_USER`, `ROLE_ADMIN` 등의 권한 기반 제어 |
| 인가 처리 | URL 접근 권한 설정 (`hasRole`, `hasAuthority`) |
| JWT 인증 흐름 | 토큰 발급 → 헤더 전달 → 필터에서 인증 처리 |
| 시큐리티 설정 | `SecurityFilterChain`, `WebSecurityCustomizer` |
| 세션 vs 토큰 | 상태 기반 인증 vs 무상태 인증 비교

---

## 📂 문서 구성

| 파일명 예시 | 설명 |
|-------------|------|
| `01-security-basic.md` | 시큐리티 구조와 필터 흐름 |
| `02-form-login.md` | 기본 로그인 처리 구현 |
| `03-password-encoding.md` | 비밀번호 암호화 및 검증 실습 |
| `04-jwt-authentication.md` | JWT 기반 로그인 구현 |
| `...-mistakes.md` | 토큰 오류, 필터 등록 실수 등 보안 실수 정리 |
| `...-extra.md` | 세션 인증 vs JWT 인증 비교, 보안 취약점 참고 |

---

## 🧭 학습 흐름

1. 기본 Form 로그인부터 시큐리티 구조 이해
2. 사용자 정보 저장 → 인증 처리 → 인가 처리 흐름 직접 구현
3. JWT 토큰 기반 인증 흐름 확장
4. 보안 이슈 발생 사례와 함께 실수 노트 정리

---

## 📌 작성 기준

- Spring Security 6 기준 (SecurityFilterChain 사용)
- JWT는 Access Token 중심 구성
- 설정 클래스와 커스텀 필터 분리 구조

---

> “백엔드 개발자라면 보안은 선택이 아니야.”  
>  
> 사용자의 정보를 지키는 가장 기본적인 약속,  
> **안전한 인증 시스템을 설계할 수 있어야 진짜 개발자!** 🔒
