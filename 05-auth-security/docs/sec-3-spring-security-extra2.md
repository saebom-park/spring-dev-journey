# [SEC-3단계] 추가 개념 정리 2 (DB 연동 UserDetailsService)

> 💬 커스텀 로그인 페이지와 InMemory 계정은 연습용일 뿐이다.  
> 실무에서는 반드시 **DB 사용자 정보**를 기반으로 인증을 처리해야 한다.  
> 이번 정리에서는 Spring Security에서 `UserDetailsService`를 구현하여 JPA와 연동하는 방식을 학습한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| User 엔티티 | DB에 저장되는 사용자 정보 (username, password, role) |
| UserRepository | JPA 기반으로 사용자 엔티티 조회 |
| UserDetailsService | `loadUserByUsername()` 구현 → DB에서 사용자 정보 로드 |
| UserDetails | Security가 사용하는 사용자 객체 (username, password, authorities) |
| PasswordEncoder | 비밀번호 암호화 및 검증 (BCrypt) |

---

## 🧾 예시 코드 (`com.springlab21` 기준)

### 1. `User.java` (Entity)
```java
package com.springlab21.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getter/setter
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
```

---

### 2. `UserRepository.java`
```java
package com.springlab21.repository;

import com.springlab21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
```

---

### 3. `CustomUserDetails.java`
```java
package com.springlab21.security;

import com.springlab21.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() { return user.getPassword(); }
    @Override
    public String getUsername() { return user.getUsername(); }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
```

---

### 4. `CustomUserDetailsService.java`
```java
package com.springlab21.security;

import com.springlab21.entity.User;
import com.springlab21.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        return new CustomUserDetails(user);
    }
}
```

---

### 5. `SecurityConfig.java` (DB 연동 버전)
```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

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

### 6. `LoginController.java`
```java
package com.springlab21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
```

---

### 7. `login.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>로그인 페이지</title>
</head>
<body>
<h2>DB 기반 로그인</h2>
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

- DB User 엔티티 + Repository로 사용자 정보 관리.  
- `CustomUserDetailsService`에서 DB 사용자 인증 처리.  
- 비밀번호는 **BCrypt 암호화** 필수.  
- 로그인 성공/실패 흐름은 커스텀 Form Login과 동일.  
- 이 방식이 실무에서 가장 많이 쓰이는 **기본 인증 구조**.  

---

## 🧪 실습 미션

🎯 목표: DB 기반 사용자 인증을 구현한다.  

1. `User` 엔티티와 `UserRepository` 작성.  
2. `CustomUserDetails`와 `CustomUserDetailsService` 구현.  
3. `SecurityConfig`에서 `PasswordEncoder` 등록.  
4. `login.html` 페이지 작성.  
5. 실행 후 DB에 사용자 계정 삽입 (`spring` / `1234` → BCrypt 암호화).  
6. `/secure/hello` 접근 → 로그인 페이지 이동 → 로그인 성공 후 접근 확인.  
