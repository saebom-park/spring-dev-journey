# [SEC-4-EXTRA1단계] 권한 설정 심화 (authorization-advanced)

> 💬 SEC-4에서 로그인 사용자의 Role/Authority에 따라 접근 제어를 학습했다.  
> 이번 심화에서는 실무에서 자주 쓰이는 **권한 계층(RoleHierarchy), SpEL 기반 접근 제어, 커스텀 접근 제어**를 다룬다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Role Hierarchy | 권한 상속 구조 정의: `ROLE_ADMIN > ROLE_MANAGER > ROLE_USER` |
| SpEL 기반 접근 제어 | `@PreAuthorize`에 Spring Expression Language 사용 가능 |
| AccessDecisionManager | 커스텀 접근 제어 로직 구현 시 활용 |
| Domain Object Security | 특정 리소스의 소유자만 접근 허용 (게시글 작성자 본인만 수정 등) |

---

## 🧾 예시 코드

### 1. `RoleHierarchyConfig.java`
```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER \n ROLE_MANAGER > ROLE_USER");
        return roleHierarchy;
    }
}
```

---

### 2. `UserController.java` (SpEL 활용)
```java
package com.springlab21.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/user/{username}/profile")
    public String myProfile(@PathVariable String username) {
        return username + " 님의 개인 프로필";
    }
}
```

---

## 📌 포인트 요약

- `RoleHierarchy`를 적용하면 **상위 권한이 하위 권한을 자동 포함**.  
- SpEL 기반 `@PreAuthorize`로 메서드 파라미터와 인증 사용자 정보를 비교 가능.  
- AccessDecisionManager로 복잡한 비즈니스 로직 기반 접근 제어 구현 가능.  
- 도메인 객체 보안 → 실무에서 "작성자만 수정/삭제" 같은 기능 구현에 활용.  

---

## 🧪 실습 미션

🎯 목표: 기본 Role 제어에서 한 단계 더 나아가 **권한 계층과 SpEL 기반 권한 제어**를 학습한다.  

1. `RoleHierarchyConfig` 작성 → `ROLE_ADMIN > ROLE_MANAGER > ROLE_USER` 계층 정의.  
2. `UserController` 수정 → `@PreAuthorize("#username == authentication.name")` 추가.  
3. DB에 `manager1` 계정 추가 (`ROLE_MANAGER`).  
4. 접근 확인:  
   - `admin1` → `/admin/dashboard` OK, `/user/profile` OK  
   - `manager1` → `/user/profile` OK, `/admin/dashboard` FORBIDDEN  
   - `user1` → 자기 자신의 `/user/{username}/profile`만 접근 OK  
