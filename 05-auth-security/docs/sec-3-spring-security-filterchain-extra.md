# [SEC-3단계] SecurityFilterChain 완전 해부

> Spring Security의 핵심 설정인 `SecurityFilterChain` 메서드를 한 줄씩 분석하여,  
> Spring Security가 어떻게 요청을 필터링하고 인증/인가를 적용하는지 이해하기 위한 문서입니다.  
> (실무 스타일: `SecurityConfig` 클래스 분리 기준)  

---

## 📄 전체 코드 (`SecurityConfig.java`)

```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()   // 누구나 접근 가능
                .anyRequest().authenticated()                // 그 외 요청은 인증 필요
            )
            .formLogin(form -> form                        // 기본 로그인 폼 활성화
                .loginPage("/login")                       // 기본 제공 로그인 페이지
                .permitAll()
            )
            .logout(logout -> logout.permitAll());         // 로그아웃 허용

        return http.build();
    }
}
```

---

## 🧠 한 줄씩 해석

### 1. `@Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) ...`
- Spring Security의 **보안 규칙(필터 체인)**을 정의하는 메서드  
- `HttpSecurity` DSL을 사용해 인증/인가 규칙을 선언적으로 작성  

---

### 2. `http.authorizeHttpRequests(auth -> auth ...)`
- 들어오는 **HTTP 요청 URL별 접근 권한**을 정의하는 부분  
- 내부적으로 `AuthorizationFilter` 같은 여러 필터를 구성  

---

#### 2-1. `.requestMatchers("/public/**").permitAll()`
- `/public/`로 시작하는 모든 요청은 인증 없이 접근 가능  
- 정적 리소스, 공개 API 등에 사용  

---

#### 2-2. `.anyRequest().authenticated()`
- 위에서 허용한 경로를 제외한 **모든 요청은 인증 필요**  
- 즉, `/secure/**` 같은 경로는 반드시 로그인해야 접근 가능  

---

### 3. `.formLogin(form -> form ...)`
- **Form 기반 로그인** 기능 활성화  
- Spring Security가 제공하는 기본 로그인 페이지를 사용  

---

#### 3-1. `.loginPage("/login")`
- 로그인 페이지 URL을 `/login`으로 지정  
- 우리가 별도 페이지를 만들지 않으면, Spring이 제공하는 **기본 로그인 폼** 노출  

---

#### 3-2. `.permitAll()`
- 로그인 페이지 자체는 인증 없이 접근 가능해야 함  
- (로그인하려고 로그인 페이지 들어가는데 인증 필요하면 안 되니까!)  

---

### 4. `.logout(logout -> logout.permitAll())`
- **로그아웃 기능 활성화**  
- Spring Security가 `/logout` 엔드포인트를 자동 제공  
- `permitAll()` → 누구든 로그아웃 요청 가능  

---

### 5. `return http.build();`
- 지금까지 정의한 규칙을 실제 `SecurityFilterChain` 객체로 생성  
- Spring Security가 이 필터 체인을 사용해 요청을 처리  

---

## 💬 핵심 요약

- `SecurityFilterChain` → Spring Security의 **보안 규칙 집합**  
- `/public/**` → 누구나 접근 가능  
- `anyRequest().authenticated()` → 나머지는 로그인 필수  
- `formLogin()` → 기본 로그인 페이지 활성화  
- `logout()` → 로그아웃 기능 제공  
- **우리는 규칙만 정의**하고, 나머지 필터 연결/프록시 등록은 Spring Boot가 자동으로 처리  
