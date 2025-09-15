# [SEC-3단계] Spring Security 필터 체인 (spring-security-filterchain)

> 💬 SEC-1에서 세션 로그인, SEC-2에서 JWT 인증을 직접 구현했다.  
> 이제는 실무에서 반드시 쓰이는 Spring Security 구조를 학습한다.  
> DelegatingFilterProxy와 SecurityFilterChain의 흐름을 이해하고, **Config 클래스 분리 방식**으로 구현한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| DelegatingFilterProxy | 서블릿 컨테이너(Filter)와 Spring Security(FilterChainProxy)를 연결하는 다리 |
| FilterChainProxy | 여러 SecurityFilterChain을 관리하며 요청별 보안 필터 동작을 위임 |
| SecurityFilterChain | URL 패턴별 보안 규칙(인증/인가/필터)을 정의하는 체인 |
| UsernamePasswordAuthenticationFilter | 기본 로그인 인증 필터 |
| ExceptionTranslationFilter | 인증/인가 과정에서 발생한 예외 처리 |
| SecurityContextPersistenceFilter | 로그인 성공 후 SecurityContext 생성 및 저장 |

---

## 🧾 예시 코드 (실무 스타일: Config 분리)

### 1. `SpringSecurityApplication.java`
```java
package com.springlab21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
```

---

### 2. `SecurityConfig.java`
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

### 3. `PublicController.java`
```java
package com.springlab21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "공개 페이지: 누구나 접근 가능!";
    }
}
```

---

### 4. `SecureController.java`
```java
package com.springlab21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "보호된 페이지: 로그인 성공 후 접근 가능!";
    }
}
```

---

## 📌 포인트 요약

- Spring Boot 실무에서는 `@SpringBootApplication`은 **진입점만 담당**.  
- 보안 규칙은 `@Configuration` 클래스(`SecurityConfig`)로 분리 관리.  
- `/public/**` → 누구나 접근 가능, `/secure/**` → 로그인 필요.  
- `formLogin()`으로 기본 로그인 페이지 자동 제공.  

---

## 🧪 실습 미션

🎯 목표: Spring Security 기본 FilterChain을 **Config 분리 방식**으로 구현하고, 접근 제어를 확인한다.  

1. `springlab21` 프로젝트에 `com.springlab21.config.SecurityConfig` 클래스 생성.  
2. `SecurityFilterChain` Bean을 등록하고 `/public/**`는 허용, 나머지는 인증 필요 설정.  
3. `PublicController` 작성 → `/public/hello`: 인증 없이 접근 가능.  
4. `SecureController` 작성 → `/secure/hello`: 로그인 성공 후 접근 가능.  
5. 애플리케이션 실행 후 확인:  
   - `/public/hello` → 바로 접근 가능.  
   - `/secure/hello` → 로그인 페이지 리다이렉트.  
   - 로그에서 임시 계정(`user` + 랜덤 비밀번호) 확인 후 로그인 성공.  
6. 로그아웃 요청(`/logout`) → 다시 `/secure/hello` 접근 시 로그인 페이지로 이동.  
