# [SEC-4단계] 권한 설정 (authorization)

> 💬 SEC-3에서 Spring Security 기본 필터 체인과 로그인 인증을 학습했다.  
> 이제는 로그인한 사용자의 **권한(Role/Authority)에 따라 접근 가능한 페이지를 제한**하는 방법을 배운다.  
> 실무에서는 관리자/일반 사용자 구분이 반드시 필요하다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Role vs Authority | `ROLE_USER`, `ROLE_ADMIN` 같은 권한 문자열을 부여하여 접근 제어 |
| hasRole / hasAuthority | 특정 Role/Authority를 가진 사용자만 접근 허용하는 메서드 |
| @PreAuthorize | 메서드 단위 접근 제한 → SpEL(`hasRole`, `hasAuthority`) 표현식 사용 |
| GlobalMethodSecurity | `@EnableMethodSecurity`로 메서드 보안 활성화 |
| GrantedAuthority | UserDetails에 부여된 권한을 담는 객체 |
| 인증(Authentication) vs 인가(Authorization) | 인증: 로그인 확인 / 인가: 권한 검증 |

---

## 🧾 예시 코드

### 1. `SecurityConfig.java`
```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // @PreAuthorize 사용 가능하게 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")   // ADMIN만 접근 가능
                .anyRequest().authenticated()                   // 그 외 요청은 인증 필요
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
```

---

### 2. `User.java` (Entity)
```java
package com.springlab21.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, unique = true)
   private String username;

   @Column(nullable = false)
   private String password;

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "role")
   private Set<String> roles; // ROLE_USER, ROLE_ADMIN 저장

   // 기본 생성자 (JPA 필수)
   public User() {}

   // 전체 필드 생성자 (편의용)
   public User(String username, String password, Set<String> roles) {
      this.username = username;
      this.password = password;
      this.roles = roles;
   }

   // Getter / Setter
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<String> getRoles() {
      return roles;
   }

   public void setRoles(Set<String> roles) {
      this.roles = roles;
   }
}
```

---

### 3. `AdminController.java`
```java
package com.springlab21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminPage() {
        return "관리자 전용 페이지!";
    }
}
```

---

### 4. `UserController.java`
```java
package com.springlab21.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/profile")
    public String profile() {
        return "일반 사용자 프로필 페이지";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/only-admin")
    public String onlyAdmin() {
        return "이건 관리자만 볼 수 있는 사용자 API!";
    }
}
```

---

## 📌 포인트 요약

- `hasRole("ADMIN")` → 내부적으로 `ROLE_ADMIN` 문자열과 매칭됨.  
- `@EnableMethodSecurity` → 컨트롤러/서비스 메서드 단위에서 `@PreAuthorize` 사용 가능.  
- **인증(Authentication)**: 로그인 성공 여부, **인가(Authorization)**: 권한 확인.  
- 실무에서는 DB(User 테이블)에 `roles`를 저장해 유연하게 관리.  

---

## 🧪 실습 미션

🎯 목표: 로그인 후 사용자 권한(Role)에 따라 접근 가능한 페이지를 구분한다.  

1. `users` 테이블에 `roles` 필드 추가 (`ROLE_USER`, `ROLE_ADMIN`).  
2. `SecurityConfig` 수정 → `/admin/**` 요청은 `ADMIN`만 접근 가능.  
3. `AdminController` 작성 → `/admin/dashboard` 페이지는 관리자만 접근.  
4. `UserController` 작성 → `/user/profile`은 모든 로그인 사용자 접근 가능.  
   - 추가로 `@PreAuthorize("hasRole('ADMIN')")` 메서드 작성 → 관리자만 호출 가능 API.  
5. DB에 테스트 데이터 추가:  
   - user1 / password → ROLE_USER  
   - admin1 / password → ROLE_ADMIN  
6. 로그인 후 접근 확인:  
   - `user1` → `/user/profile` OK, `/admin/dashboard` FORBIDDEN.  
   - `admin1` → `/admin/dashboard` OK.  