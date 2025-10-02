# Spring Security All-in-One (SEC-1 ~ SEC-5)

> 💬 이 프로젝트는 Spring Security 학습 과정(SEC-1 ~ SEC-5)을 **하나의 흐름으로 통합**하여 복습하기 위해 만든 통합 실습이다.  
> 
> - 세션 로그인 → JWT 인증 → 필터체인 → 권한 제어 → 비밀번호 암호화  
> - 실제 서비스에서 사용하는 **인증 + 권한 + 암호화 전체 플로우**를 백지 손코딩으로 다시 구현한다.

---

## 💡 학습 시나리오

1. 사용자가 회원가입 시 비밀번호는 반드시 해시 처리(BCrypt) 후 저장한다.
2. 로그인 시 아이디/비밀번호를 검증하고, 세션 또는 JWT 토큰을 발급한다.
3. 발급받은 인증 정보를 이용해 보호된 API에 접근한다.
4. 권한(Role)에 따라 접근 가능한 API가 달라진다.
5. 관리자는 일반 사용자보다 더 많은 권한을 가진다.
6. 모든 과정은 Spring Security FilterChain을 통해 동작한다.

---

## 📋 단계 요약

| 단계 | 주제 | 핵심 포인트 |
|------|------|-------------|
| SEC-1 | 세션 기반 로그인 | HttpSession 기반 인증 처리 |
| SEC-2 | JWT 인증 | AccessToken, RefreshToken 발급/검증 |
| SEC-2-EXTRA | JWT 심화 | 토큰 갱신(Refresh Flow), 보안 고려사항 |
| SEC-3 | 필터체인 | SecurityFilterChain 커스터마이징, 동작 순서 |
| SEC-4 | 권한 제어 | RoleHierarchy, @PreAuthorize, 도메인 보안 |
| SEC-5 | 비밀번호 암호화 | BCrypt + DelegatingPasswordEncoder, upgrade-on-login |

---

## 🎯 손코딩 체크리스트

- [ ] `User` 엔티티에 username/password/roles 필드 정의
- [ ] `UserRepository` 구현 (Spring Data JPA)
- [ ] `CustomUserDetails`, `CustomUserDetailsService` 구현
- [ ] `SecurityConfig`에서 `SecurityFilterChain` + `PasswordEncoder` 등록
- [ ] 로그인/회원가입 컨트롤러 작성
- [ ] 세션 로그인 + JWT 로그인 모두 확인
- [ ] `@PreAuthorize`로 관리자/사용자 접근 제한 테스트
- [ ] DB 비밀번호가 `{bcrypt}...` 형태로 저장되는지 확인
- [ ] 로그인 성공 시 기존 계정 비밀번호를 최신 해시로 재암호화(upgrade-on-login)

---

## 📂 폴더 구조 (예시)

```
05-auth-security-all-in-one/
 ┣ src/
 ┃ ┣ main/java/com/springlab22
 ┃ ┃ ┣ config/        ← SecurityConfig
 ┃ ┃ ┣ controller/    ← AuthController, UserController
 ┃ ┃ ┣ entity/        ← User
 ┃ ┃ ┣ repository/    ← UserRepository
 ┃ ┃ ┣ security/      ← CustomUserDetails, JwtFilter, JwtProvider
 ┃ ┃ ┗ service/       ← UserService, AuthService
 ┃ ┗ main/resources
 ┃   ┣ application.yml
 ┃   ┗ schema.sql / data.sql (테스트 데이터)
 ┣ build.gradle
 ┣ settings.gradle
 ┗ README.md
```

---

## 🌱 정리 키워드

- **Authentication**: 인증 (누구인가?)  
- **Authorization**: 권한 (무엇을 할 수 있는가?)  
- **FilterChain**: 인증/권한 검증 흐름  
- **PasswordEncoder**: 비밀번호 해시 처리 (BCrypt, Delegating)  
- **JWT**: 토큰 기반 인증, 무상태(stateless) 서버  
- **RoleHierarchy**: 권한 계층 구조  
