# [SEC-2단계] JWT 인증 (jwt-auth)

> 이번 단계에서는 **JWT(JSON Web Token)** 기반 인증 방식을 학습한다.  
> 세션이 아닌 토큰으로 인증 상태를 유지하는 무상태(Stateless) 구조를 구현해본다.  
> 서버는 로그인 성공 시 토큰을 발급하고, 클라이언트는 이를 요청 헤더에 담아 전송한다.  

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
|------|------|
| JWT | JSON 포맷으로 인코딩된 토큰. Header, Payload, Signature로 구성 |
| Stateless 인증 | 서버에 상태(세션)를 저장하지 않고, 클라이언트가 토큰을 들고 다님 |
| Access Token | 사용자 인증 상태를 증명하는 토큰, 유효기간 짧게 설정 |
| Refresh Token | Access Token 갱신용 토큰, 더 긴 유효기간 |
| 장점 | 확장성(서버 확장 쉬움), 클라이언트 독립적 인증 가능 |
| 단점 | 토큰 탈취 시 위험, 토큰 크기가 커질 수 있음 |

---

## 🧾 예시 코드

### JwtUtil

```java
package com.springlab21.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 30분

    // 토큰 생성
    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // 토큰 검증
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 사용자 이름 추출
    public static String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
```

---

### JwtLoginController

```java
package com.springlab21.controller;

import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.LoginResponse;
import com.springlab21.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class JwtLoginController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        if ("spring".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            String token = JwtUtil.createToken(request.getUsername());
            return new LoginResponse("로그인 성공", token);
        } else {
            return new LoginResponse("로그인 실패", null);
        }
    }

    // validateToken 사용 버전
    @GetMapping("/secure")
    public LoginResponse secure(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsername(token);
            return new LoginResponse("보호된 리소스 접근 성공", username);
        } else {
            return new LoginResponse("토큰이 유효하지 않습니다", null);
        }
    }
    
    // validateToken 없이 쓰는 버전
    @GetMapping("/secure2")
    public LoginResponse secure2(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        try {
            String username = JwtUtil.getUsername(token); // 검증 + 추출 동시에
            return new LoginResponse("보호된 리소스 접근 성공", username);
        } catch (Exception e) {
            return new LoginResponse("토큰이 유효하지 않습니다", null);
        }
    }
}
```

---

### 요청/응답 예시

- **로그인 요청**
```http
POST /api/jwt/login
Content-Type: application/json

{
  "username": "spring",
  "password": "1234"
}
```

- **로그인 성공 응답**
```json
{
  "message": "로그인 성공",
  "username": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

- **보호 API 요청**
```http
GET /api/jwt/secure
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

- **응답**
```json
{
  "message": "보호된 리소스 접근 성공",
  "username": "spring"
}
```

---

## 📌 포인트 요약

- 세션 기반과 달리 서버는 상태 저장 ❌ → 확장성 ↑  
- 토큰은 클라이언트가 직접 관리 (쿠키/로컬스토리지/세션스토리지 등)  
- JWT는 반드시 **서명(Signature)** 으로 위조 방지  
- 만료 시간(expiration) 설정 필수  
- Refresh Token 전략까지 적용하면 보안성 강화 가능  
- **실무 스타일**: validateToken을 분리할 수도 있고, 생략하고 getUsername만 써도 된다  

---

## 🧪 실습 미션

🎯 목표: **JWT 인증 흐름 구현**  

1. `JwtUtil` 유틸리티 클래스 작성  
   - 토큰 생성 (`createToken`)  
   - 토큰 검증 (`validateToken`)  
   - 토큰에서 사용자 이름 추출 (`getUsername`)  
2. `JwtLoginController` 작성  
   - `/login` : 로그인 성공 시 JWT 발급  
   - `/secure` : Authorization 헤더의 토큰 검증 후 사용자 정보 반환  
   - `/secure2` : validateToken 없이 getUsername만 사용하여 검증 + 추출  
3. Postman 테스트  
   - 로그인 요청 → 토큰 발급 확인  
   - Authorization 헤더에 토큰 담아 `/secure` 또는 `/secure2` 요청 → 정상 응답 확인  
   - 토큰 만료/위조 상황도 테스트  

> 참고: JWT 발급 시 HMAC-SHA256 알고리즘과 Secret Key를 사용한다.
