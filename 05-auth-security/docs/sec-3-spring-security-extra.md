# [SEC-3단계] 추가 개념 정리 (커스텀 Form Login)

> 💬 기본 Form Login 대신, 실무에서는 보통 **커스텀 로그인 페이지 + UserDetailsService + PasswordEncoder**를 조합해 사용한다.  
> 이번 정리에서는 Spring Security의 **커스텀 Form Login 방식**을 학습한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| UserDetailsService | 사용자 인증 정보를 가져오는 인터페이스. DB 조회/검증을 구현 |
| PasswordEncoder | 사용자 비밀번호를 해시/검증하는 컴포넌트. 보통 `BCryptPasswordEncoder` 사용 |
| InMemoryUserDetailsManager | 메모리에 사용자 정보를 저장하는 테스트용 구현체 |
| formLogin().loginPage("/login") | 기본 로그인 페이지 대신, 개발자가 만든 `/login` 뷰 사용 |
| AuthenticationManager | 사용자 인증 요청을 처리하고 Authentication 객체 생성 |

---

## 🧾 예시 코드 (`com.springlab21` 기준)

### 1. `SecurityConfigCustom.java`
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

### 2. `LoginController.java`
```java
package com.springlab21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // templates/login.html 반환
    }
}
```

---

### 3. `login.html` (resources/templates/login.html)
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>로그인 페이지</title>
</head>
<body>
<h2>커스텀 로그인</h2>
<form th:action="@{/login}" method="post">
    <div>
        <label>아이디: <input type="text" name="username"/></label>
    </div>
    <div>
        <label>비밀번호: <input type="password" name="password"/></label>
    </div>
    <div>
        <button type="submit">로그인</button>
    </div>
</form>
</body>
</html>
```

---

## 📌 포인트 요약

- **InMemoryUserDetailsManager**로 사용자 계정 등록 가능.  
- **PasswordEncoder**(`BCryptPasswordEncoder`)를 적용해야 안전.  
- `login.html` 작성 후 `formLogin().loginPage("/login")`으로 **커스텀 페이지** 사용.  
- 로그인 성공 시 `defaultSuccessUrl`로 이동 가능.  

---

## 🧪 실습 미션

🎯 목표: 기본 로그인 폼을 **커스텀 로그인 페이지**로 교체하고, 사용자 계정을 직접 정의한다.  

1. `SecurityConfigCustom`에 `InMemoryUserDetailsManager`와 `PasswordEncoder` 등록.  
2. 사용자 계정 등록 (`username: spring`, `password: 1234`).  
3. `login.html` 템플릿 작성.  
4. `LoginController`에서 `/login` 요청 처리.  
5. 실행 후 브라우저에서 `/secure/hello` 접근 → 로그인 페이지 이동 확인.  
6. `spring/1234` 로그인 성공 시 `/secure/hello` 접근 가능 확인.  
