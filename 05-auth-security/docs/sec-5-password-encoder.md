# [SEC-5단계] 비밀번호 암호화 (password-encoder)

> 💬 로그인/회원가입에서 비밀번호를 절대 평문으로 저장하지 않기 위한 설계를 학습한다.
> 
> 
> 단방향 해시(BCrypt 등)와 스프링의 PasswordEncoder 사용 패턴을 실무 기준으로 정리한다.
> 
> 이전 단계에서 이미 적용해본 내용을, 이유/원리/운영 관점까지 확장해 정리한다.
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| 평문 저장 금지 | DB 유출 시 즉시 계정 털림 → 법·보안 리스크 치명적 |
| 단방향 해시 | 복호화 불가능한 해시로 저장, 인증 시 `matches(raw, encoded)` 비교 |
| Salt | 같은 비번이어도 해시가 다르게 나오도록 난수(salt) 자동 포함(BCrypt) |
| BCrypt | 느리고 안전한 해시, 자동 salt·work factor(비용) 지원, 업계 표준 |
| Work factor(strength) | 계산 난이도(기본 10~12 권장). 서버 성능/트래픽에 맞춰 조정 |
| DelegatingPasswordEncoder | `{id}encoded` 형태로 알고리즘 식별자 보존 → 향후 알고리즘 교체·혼용 가능 |
| encode / matches | 저장 전 `encode(raw)` / 로그인 때 `matches(raw, encoded)` |
| 마이그레이션 전략 | `{id}` 접두로 혼용 저장 → 로그인 순간 재해시(upgrade-on-login)로 점진 전환 |
| 금고 비유 | 비번 그 자체를 저장하지 않고, 자물쇠(해시) 모양만 저장한다 |

---

## 🧾 예시 코드

### 1) SecurityConfig.java — PasswordEncoder Bean 등록 (권장: Delegating)

```java
package com.springlab21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // {bcrypt} 기본, {noop},{pbkdf2},{scrypt},{argon2} 등 식별자 지원
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

```

### 2) UserService.java — 회원가입/로그인 시 적용 패턴

```java
package com.springlab21.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입: 저장 전에 반드시 해시
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword); // DB에는 이 값 저장
    }

    // 로그인 검증: 평문 vs 해시
    public boolean matches(String rawPassword, String encodedFromDb) {
        return passwordEncoder.matches(rawPassword, encodedFromDb);
    }
}

```

> 참고
> 
> - 이미 BCrypt를 고정 사용한다면 `new BCryptPasswordEncoder(strength)` 로도 가능.
> - 장기적으로는 Delegating을 쓰면 알고리즘 교체가 쉬워 운영 안정성이 높다.

---

## 📌 포인트 요약

- DB에는 항상 **해시값만 저장**한다(평문 금지).
- **DelegatingPasswordEncoder**로 `{id}` 접두를 보존하면, 미래에 알고리즘을 바꿔도 기존 데이터와 **혼용** 가능.
- 회원가입: `encode()` → 저장 / 로그인: `matches()` → 검증.
- BCrypt **work factor**를 환경에 맞게 주기적으로 재평가(성능 테스트 필수).
- 과거 약한 해시를 쓰던 계정은 **로그인 시 재해시(upgrade-on-login)** 로 점진 마이그레이션.
- 로그에 평문 비밀번호 절대 출력 금지(마스킹/비로그 보관).

---

## 🧪 실습 미션

**🎯 목표:** 비밀번호 암호화 체계를 Delegating 기반으로 표준화하고, 기존 계정도 점진적으로 최신 해시로 승격한다.

1. SecurityConfig에 `PasswordEncoderFactories.createDelegatingPasswordEncoder()` 등록.
2. 회원가입 흐름에서 `encode(raw)` 적용 후 DB 저장 여부를 콘솔/DB로 확인.
3. 로그인 흐름에서 `matches(raw, encoded)` 검증이 정상 동작하는지 통합 테스트(Postman)로 확인.
4. 기존 해시(예: `{noop}` 또는 레거시 해시)가 남아있다면, 로그인 성공 시 최신 알고리즘(기본 `{bcrypt}`)으로 **재해시 저장** 로직 적용(서비스 계층).
5. 운영 체크리스트:
    - 실패 로그에 비밀번호 값 미노출
    - 해시 길이/접두 `{id}` 가이드 문서화
    - work factor 변경 시 부하 테스트 절차 수립

> 참고:
> 
> - 예시 코드와 다른 도메인/흐름으로 적용(현재 프로젝트의 `User` 등록/로그인 시나리오에 통합).
> - 이미 BCrypt만 사용 중이라도 Delegating으로 이관하면 추후 알고리즘 업그레이드가 쉬워진다.