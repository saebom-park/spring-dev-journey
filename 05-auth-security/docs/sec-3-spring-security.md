# [SEC-3단계] Spring Security 필터 체인 (spring-security-filterchain)

> 💬 SEC-1에서 세션 로그인, SEC-2에서 JWT 인증을 직접 구현했다.  
> 이제는 실무에서 반드시 쓰이는 Spring Security의 구조를 학습한다.  
> DelegatingFilterProxy와 SecurityFilterChain의 흐름을 이해하고, 기본 Form Login 방식을 적용한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| DelegatingFilterProxy | 서블릿 컨테이너(Filter)와 Spring Security(FilterChainProxy)를 연결해주는 브릿지 |
| FilterChainProxy | 여러 SecurityFilterChain을 관리하며 요청별 보안 필터 동작을 위임 |
| SecurityFilterChain | 요청 URL 패턴별 보안 규칙(인증/인가/필터)을 정의하는 체인 |
| UsernamePasswordAuthenticationFilter | 기본 로그인 인증 필터 |
| ExceptionTranslationFilter | 인증/인가 과정에서 발생한 예외를 처리 |
| SecurityContextPersistenceFilter | SecurityContext 생성 및 저장 (세션/컨텍스트 연동) |

---

## 🧾 예시 코드 (`com.springlab21` 기준)

### 1. `SpringSecurityApplication.java`
```java
package com.springlab21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

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

### 2. `PublicController.java`
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

### 3. `SecureController.java`
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

- Spring Security는 **Filter 기반 아키텍처**로 동작한다.  
- `DelegatingFilterProxy` → `FilterChainProxy` → `SecurityFilterChain` 구조가 핵심이다.  
- `HttpSecurity` DSL로 접근 권한을 선언적으로 지정한다.  
- `/public/**`는 인증 없이 접근 가능, `/secure/**`는 로그인 필요.  
- 기본 제공 로그인 페이지를 활용해 인증 흐름을 빠르게 체험한다.  

---

## 🧪 실습 미션

🎯 목표: Spring Security의 기본 FilterChain을 이해하고, 인증/인가 흐름을 직접 확인한다.  

1. **기존 `springlab21` 프로젝트**에서 이어서 진행한다.  
2. `SecurityFilterChain` Bean을 등록하고 `/public/**`는 허용, 나머지는 인증 필요하도록 설정한다.  
3. `PublicController` 작성 → `/public/hello`: 인증 없이 접근 가능.  
4. `SecureController` 작성 → `/secure/hello`: 로그인 성공 후 접근 가능.  
5. 애플리케이션 실행 후 브라우저/Postman에서 테스트한다.  
   - `http://localhost:8080/public/hello` → 바로 접근 가능해야 함.  
   - `http://localhost:8080/secure/hello` → 로그인 페이지로 리다이렉트 발생.  
6. 콘솔 로그에 출력되는 임시 계정(`user` + 랜덤 비밀번호)으로 로그인 후 `/secure/hello` 접근 성공 확인.  
7. 로그아웃 후 다시 `/secure/hello` 접근 시도 → 로그인 페이지로 리다이렉트 확인.  
