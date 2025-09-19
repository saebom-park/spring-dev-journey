# [SEC-3-EXTRA1단계] SecurityConfigCustom 완전 해부

> 커스텀 Form Login 구현을 위해 작성한 `SecurityConfigCustom.java` 코드를 한 줄씩 분석한다.  
> 특히, 낯설 수 있는 `UserDetailsService`와 `UserDetails` 개념을 집중적으로 설명하여 이해를 돕는다.  
> InMemoryUserDetailsManager + PasswordEncoder + 커스텀 로그인 페이지 조합의 동작 원리를 학습한다.  

---

## 📄 전체 코드 (`SecurityConfigCustom.java`)

```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigCustom {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("spring")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/secure/hello", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
```

---

## 🧠 한 줄씩 해석

### 1. `@Bean public InMemoryUserDetailsManager userDetailsService(...)`
- **UserDetailsService 구현체** 등록.
- 로그인 시도 시, 사용자의 인증 정보를 조회하는 핵심 창구.
- 여기서는 **메모리(InMemory)**에 계정을 등록해 사용.

```java
UserDetails user = User.withUsername("spring")
    .password(passwordEncoder.encode("1234"))
    .roles("USER")
    .build();
```
- username: `spring`
- password: `1234` (BCrypt로 암호화 저장)
- role: `USER`

👉 이렇게 등록된 계정은 로그인 시 검증 대상이 된다.

---

### 2. `@Bean public PasswordEncoder passwordEncoder()`
- `BCryptPasswordEncoder`를 Bean으로 등록.
- 로그인 시 입력받은 비밀번호를 같은 방식으로 암호화하여 DB/메모리 값과 비교.
- 평문 저장 금지, 반드시 암호화 필요.

---

### 3. `@Bean public SecurityFilterChain filterChain(HttpSecurity http)`
- URL별 보안 규칙을 정의하는 핵심 메서드.

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/public/**", "/login").permitAll()
    .anyRequest().authenticated()
)
```
- `/public/**`, `/login` → 누구나 접근 가능.
- 나머지 요청(`/secure/**`) → 반드시 로그인 필요.

```java
.formLogin(form -> form
    .loginPage("/login")
    .defaultSuccessUrl("/secure/hello", true)
    .permitAll()
)
```
- 커스텀 로그인 페이지 `/login` 사용.
- 로그인 성공 시 `/secure/hello`로 강제 이동.

```java
.logout(logout -> logout.permitAll());
```
- 로그아웃 기능 활성화 (`/logout` 엔드포인트 자동 제공).

---

## 🔍 UserDetailsService 집중 해설

### 🧩 UserDetailsService란?
- **사용자 인증 정보를 불러오는 인터페이스**.
- Security가 "이 username 가진 사용자 있나요?" 물어보면 이곳에서 찾아옴.

```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

- 반환 타입은 `UserDetails` (사용자 카드 객체).
- `UserDetails` 안에는 username, password(암호화된 값), 권한(role) 등이 담겨 있다.

---

### 🔑 UserDetails 구조
```java
public interface UserDetails {
    String getUsername();           // 아이디
    String getPassword();           // 암호화된 비밀번호
    Collection<? extends GrantedAuthority> getAuthorities(); // 권한 목록
    boolean isAccountNonExpired();  // 계정 만료 여부
    boolean isAccountNonLocked();   // 계정 잠금 여부
    boolean isCredentialsNonExpired(); // 비번 만료 여부
    boolean isEnabled();            // 계정 활성 여부
}
```
👉 핵심은 username, password, authorities 3개.
👉 나머지는 계정 상태 관리 옵션 (보통 true로 설정).

---

### ⚖️ InMemory vs DB 기반
- **InMemoryUserDetailsManager**: 연습/테스트용. 계정을 코드에서 직접 등록.
- **CustomUserDetailsService**: 실무용. DB에서 사용자 조회 후 UserDetails로 변환.

---

### 🎟️ 비유로 이해하기
- **SecurityFilterChain** = 회사 출입문 규칙
- **UsernamePasswordAuthenticationFilter** = 출입증 검사기
- **UserDetailsService** = 인사팀 (직원 명부 보관)
- **UserDetails** = 직원 카드 (이름, 암호화된 비번, 권한 적혀 있음)
- **PasswordEncoder** = 비밀번호 검사 장치
- **SecurityContext** = 출입 기록 보관소

👉 게이트가 "이 직원 있나요?" 물어보면 → 인사팀(UserDetailsService)이 직원 카드(UserDetails)를 꺼내줌 → 비번 검사기(PasswordEncoder)로 확인 → 통과하면 출입 기록(SecurityContext)에 저장.

---

## 💬 핵심 요약

- `UserDetailsService` = 사용자 정보 조회 인터페이스 (인사팀).
- `UserDetails` = 사용자 카드 객체 (아이디, 암호, 권한 보유).
- `PasswordEncoder` = 비번 암호화 및 검증.
- `SecurityConfigCustom` = InMemory 계정(spring/1234) 등록 + 커스텀 로그인 페이지 적용.
- 기본 Form Login과의 차이:
  - 기본: 랜덤 user 계정 + 스프링 제공 로그인 페이지.
  - 커스텀: spring/1234 계정 + 직접 만든 login.html.
