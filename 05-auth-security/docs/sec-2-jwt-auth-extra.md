# [SEC-2단계] 추가 개념 정리

> 💬 SEC-2 JWT 인증의 기본 흐름을 학습한 뒤, 실무에서 자주 쓰이는 확장 개념들을 정리한다.
> 
> 
> Access Token만으로는 부족한 실제 인증 시나리오를 보완하며, 세션 기반 방식과의 차이도 비교한다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| JWT 디코딩 | JWT는 Base64Url 인코딩된 문자열 → jwt.io 같은 도구로 Header/Payload/Signature 확인 가능 |
| Refresh Token | Access Token이 만료되면 새로운 Access Token을 발급받기 위한 토큰. 유효기간이 더 길다 |
| 토큰 갱신 플로우 | Access 만료 → 클라이언트가 Refresh Token 요청 → 서버가 검증 후 새 Access 발급 |
| 세션 vs JWT | 세션은 서버 저장소 기반, JWT는 클라이언트 보관 기반. 확장성은 JWT가 유리, 무효화는 세션이 유리 |

---

## 🧾 예시 코드

### Refresh Token 발급 & 갱신

```java
package com.springlab21.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtilExtra {

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ACCESS_EXP = 1000 * 60 * 30;   // 30분
    private static final long REFRESH_EXP = 1000 * 60 * 60 * 24 * 7; // 7일

    // Access Token 생성
    public static String createAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXP))
                .signWith(secretKey)
                .compact();
    }

    // Refresh Token 생성
    public static String createRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .signWith(secretKey)
                .compact();
    }
}

```

---

### Refresh Controller

```java
package com.springlab21.controller;

import com.springlab21.jwt.JwtUtilExtra;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class RefreshController {

    @PostMapping("/refresh")
    public String refresh(@RequestHeader("Authorization") String authHeader) {
        String refreshToken = authHeader.replace("Bearer ", "");
        // 실제로는 DB나 Redis에 저장된 Refresh Token 검증 필요
        return JwtUtilExtra.createAccessToken("spring");
    }
}

```

---

## 📌 포인트 요약

- JWT 구조를 직접 디코딩(jwt.io) 해보면 Header/Payload가 눈에 보임 → 이해도 상승
- Access Token은 유효기간 짧게, Refresh Token은 길게 → 실무에서 반드시 함께 사용
- Refresh Token은 보통 **DB/Redis**에 저장해 서버에서 검증 후 Access 재발급
- 세션 vs JWT 비교:
    - 세션: 서버가 상태 관리, 무효화 쉬움
    - JWT: Stateless, 확장성 뛰어남

---

## 🧪 실습 미션

🎯 목표: **JWT 확장 개념 체험**

1. **JWT 디코딩**
    - Postman으로 발급받은 Access Token을 jwt.io에 붙여넣고 Header/Payload 확인
2. **Refresh Token**
    - `JwtUtilExtra`에 Refresh Token 발급 메서드 추가
    - 로그인 시 Access + Refresh 동시 발급
    - `/refresh` API 호출로 새로운 Access Token 발급 테스트
3. **세션 vs JWT 비교**
    - SEC-1(세션 로그인)과 SEC-2(JWT 인증) 동일한 로그인/보호 API 요청 시나리오 실행
    - 로그아웃 이후 차이점 확인 (세션은 끊기고 JWT는 만료 전까지 유지됨)

---

📌 이 문서는 **심화 학습용**이므로, SEC-2 기본 실습을 완료한 후 이어서 진행해야 한다.