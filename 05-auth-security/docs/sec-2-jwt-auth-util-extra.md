# [SEC-2단계] JwtUtil 클래스 완전 해부

> JwtUtil 클래스의 주요 메서드를 한 줄씩 분석하여 JWT 인증의 동작 원리를 완전히 이해하기 위한 문서입니다.  

---

## 📄 전체 코드

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

## 🧠 한 줄씩 해석

### 1. `private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);`
- 토큰을 서명할 때 사용할 **비밀 키** 생성
- HS256(HMAC-SHA256) 알고리즘 기반
- 서버만 알고 있는 키로 서명 → 위조 방지

---

### 2. `private static final long EXPIRATION_TIME = 1000 * 60 * 30;`
- 토큰 만료 시간 설정 (30분)
- `1000ms * 60 = 1분` → `* 30 = 30분`

---

### 3. `public static String createToken(String username) { ... }`
- 사용자 이름을 담은 **Access Token 발급 메서드**

#### 3-1. `.setSubject(username)`
- 토큰의 **주제(Subject)** 로 username 저장
- Payload에 `"sub": "spring"` 같은 값 들어감

#### 3-2. `.setIssuedAt(new Date())`
- 토큰 발급 시간 기록

#### 3-3. `.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))`
- 현재 시각 + 30분 → 만료 시간 설정
- 만료되면 토큰은 더 이상 사용 불가

#### 3-4. `.signWith(secretKey)`
- 아까 만든 비밀 키로 토큰 서명
- Payload 위조 방지

#### 3-5. `.compact()`
- 최종적으로 `xxx.yyy.zzz` 형태의 문자열(JWT) 반환

---

### 4. `public static boolean validateToken(String token) { ... }`
- 전달받은 토큰이 **유효한지 검사**

#### 4-1. `Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);`
- secretKey로 서명 검증
- 만료 여부, 형식 오류, 위조 여부 확인

#### 4-2. `return true;`
- 검증 통과 시 `true`

#### 4-3. `catch (Exception e) { return false; }`
- 검증 실패 시(만료·위조·형식 불일치) `false`

---

### 5. `public static String getUsername(String token) { ... }`
- 토큰 안에서 **사용자 이름 추출**

#### 5-1. `Claims claims = ...parseClaimsJws(token).getBody();`
- 토큰 Payload(Body) 꺼내기
- JSON 형태로 들어 있음

#### 5-2. `return claims.getSubject();`
- Payload 안의 `"sub"` 값(username) 반환

---

## 💬 핵심 요약

- `createToken` → username 담아 JWT 발급  
- `validateToken` → 위조/만료 여부 검사  
- `getUsername` → 토큰에서 username 꺼내기  
- secretKey + HS256 알고리즘으로 **위조 방지**  
- 만료 시간(expiration) 반드시 설정해야 함  
