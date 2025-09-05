# [SEC-1단계] 세션 기반 로그인 (sec-1-session-login.md)

> 이번 단계에서는 **세션 기반 로그인**의 개념과 구현 방법을 학습한다.  
> 서버가 발급하는 JSESSIONID를 활용해 로그인 상태를 유지하는 흐름을 구현한다.  
> 이후 JWT와 Spring Security로 확장하기 전, 가장 기본적인 인증 방식을 경험한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
|------|------|
| 세션(Session) | 서버가 클라이언트별 상태 정보를 보관하는 저장 공간 |
| JSESSIONID | 서버가 발급하는 세션 ID, 쿠키에 담겨 클라이언트와 서버 간 교환 |
| 로그인 흐름 | 로그인 요청 → 서버 인증 → 세션 생성 후 ID 발급 → 클라이언트 쿠키 저장 |
| 로그아웃 | 세션 무효화(`invalidate()`)로 인증 상태 제거 |
| 단점 | 서버 확장 시 세션 공유 전략 필요 (예: Redis) |

---

## 🧾 예시 코드

### Controller

```java
package com.springlab21.controller;

import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.LoginResponse;
import com.springlab21.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionLoginController {

    private final UserService userService;

    public SessionLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpSession session) {
        boolean authenticated = userService.authenticate(request.getUsername(), request.getPassword());
        if (authenticated) {
            session.setAttribute("username", request.getUsername());
            return new LoginResponse("로그인 성공", request.getUsername());
        } else {
            return new LoginResponse("로그인 실패", null);
        }
    }

    @GetMapping("/check")
    public LoginResponse check(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return new LoginResponse("로그인 필요", null);
        }
        return new LoginResponse("현재 로그인 사용자", username);
    }

    @PostMapping("/logout")
    public LoginResponse logout(HttpSession session) {
        session.invalidate();
        return new LoginResponse("로그아웃 완료", null);
    }
}
```

---

### Service

```java
package com.springlab21.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 실제로는 DB 조회/비밀번호 암호화 검증이 필요
    public boolean authenticate(String username, String password) {
        return "user".equals(username) && "1234".equals(password);
    }
}
```

---

### DTO

```java
package com.springlab21.dto;

public class LoginRequest {
    private String username;
    private String password;

    // getter, setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
```

```java
package com.springlab21.dto;

public class LoginResponse {
    private String message;
    private String username;

    public LoginResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() { return message; }
    public String getUsername() { return username; }
}
```

---

### 요청/응답 예시

- **로그인 요청**

```http
POST /api/session/login
Content-Type: application/json

{
  "username": "user",
  "password": "1234"
}
```

- **로그인 성공 응답**

```json
{
  "message": "로그인 성공",
  "username": "user"
}
```

- **로그인 확인 요청**

```http
GET /api/session/check
```

- **로그인 확인 응답**

```json
{
  "message": "현재 로그인 사용자",
  "username": "user"
}
```

- **로그아웃 요청**

```http
POST /api/session/logout
```

- **로그아웃 응답**

```json
{
  "message": "로그아웃 완료",
  "username": null
}
```

---

## 📌 포인트 요약

- `HttpSession`으로 로그인 상태 관리  
- 세션 생성 시 서버가 JSESSIONID 발급 → 쿠키로 클라이언트와 주고받음  
- 로그아웃 시 `invalidate()` 호출 필수  
- 현재는 단순 username/password 비교지만, 실제 서비스는 DB 연동 + 암호화 필수  
- 단일 서버에서는 간단하지만, 서버 확장 시 세션 공유 전략 필요  

---

## 🧪 실습 미션

🎯 목표: **세션 기반 로그인 API 구현 및 테스트**  

1. `LoginRequest`, `LoginResponse` DTO를 작성한다.  
2. `UserService`에서 간단한 인증 로직을 구현한다.  
3. `SessionLoginController`를 작성해 `/login`, `/check`, `/logout` 구현한다.  
4. Postman으로 다음 흐름을 테스트한다:  
   - 로그인 성공 → `check`에서 사용자 확인 → 로그아웃 → `check`에서 로그인 필요 확인  
5. JSESSIONID 쿠키가 자동으로 저장·전송되는 흐름을 확인한다.  
