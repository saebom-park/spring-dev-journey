# [SEC-2단계] 질문노트: JWT 인증 (jwt-auth)

> 💬 내가 직접 궁금해서 물어본 것들 + 온이가 설명해준 요약
> 
> 
> (코드: com.springlab21.jwt, com.springlab21.controller 실습 기준)
> 

---

### 💡 1. 파서(Parser)가 뭔데?

| 질문 | 답변 요약 |
| --- | --- |
| 파서가 뭐야? | 문자열을 구조화된 데이터로 변환해주는 해석기야. JWT에서는 토큰 문자열을 Header, Payload, Signature로 쪼개고, 서명·만료 검증까지 해주는 역할이야. |

🌱 정리 키워드

- Parser = 문자열 해석기
- JWT 파서 = 토큰 분리 + 검증 + Claims 추출

---

### 💡 2. setSigningKey는 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| setSigningKey는 왜 쓰는 거야? | JWT를 만들 때 signWith(secretKey)로 서명했으니까, 검증할 때도 같은 키를 지정해야 해. "이 도장으로 찍힌 토큰만 인정해" 라는 의미야. |

🌱 정리 키워드

- signWith = 발급 시 도장 찍기
- setSigningKey = 검증 시 도장 확인하기

---

### 💡 3. build()는 왜 필요한 거야?

| 질문 | 답변 요약 |
| --- | --- |
| build()는 왜 써야 해? | JWT 파서는 옵션(키, 시간 등)이 많아서 Builder 패턴으로 만들어져 있어. build()는 "설정 다 끝났으니 이제 진짜 Parser 객체 만들어" 라는 의미야. |

🌱 정리 키워드

- Builder 패턴
- 설정 → build() → 완성품

---

### 💡 4. parseClaimsJws(token)은 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| parseClaimsJws(token) 이게 뭐야? | 토큰을 해석하면서 동시에 검증도 해. Header/Payload/Signature 분리, 서명·만료 확인 후 Claims 객체 반환. 실패하면 예외 발생해. |

🌱 정리 키워드

- parse = 해석
- Claims = JWT Payload
- JWS = Signature 검증된 JWT

---

### 💡 5. Claims는 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| Claims는 민원, 불평 아닌데 무슨 의미야? | JWT의 Payload 안에 들어있는 데이터들을 Claims라고 불러. "이 토큰은 이런 사실을 주장한다"는 의미야. 예: sub=username, exp=만료일. |

🌱 정리 키워드

- Claim = 토큰이 주장하는 정보
- sub, exp, role 등 key-value 쌍

---

### 💡 6. validateToken은 꼭 필요한 거야?

| 질문 | 답변 요약 |
| --- | --- |
| getUsername도 검증하는데 validateToken 왜 필요해? | getUsername 내부에서도 parseClaimsJws()가 실행되니까 검증은 이미 돼. validateToken은 단순히 유효성만 체크하고 싶을 때 가독성을 위해 보조적으로 둔 거야. |

🌱 정리 키워드

- validateToken = 검증만 (true/false)
- getUsername = 검증 + 값 추출

---

### 💡 7. Jwts 객체는 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| Jwts 이건 정체가 뭐야? | jjwt 라이브러리에서 제공하는 유틸리티 클래스야. JWT 생성/검증을 시작할 때 항상 거치는 entry point. builder()로 토큰 만들고, parserBuilder()로 토큰 해석 시작해. |

🌱 정리 키워드

- Jwts = static 유틸 클래스
- builder() = 생성
- parserBuilder() = 해석

---

### 💡 8. JWT, JWS, Jwts는 뭐가 달라?

| 질문 | 답변 요약 |
| --- | --- |
| JWT, JWS, Jwts 다 헷갈려… | - **JWT (JSON Web Token)**: 토큰 개념 자체, Header + Payload + Signature 구조를 가진 문자열.
- **JWS (JSON Web Signature)**: JWT 중에서도 "서명(Signature) 검증된" 토큰을 말함. 즉, 서명 기반 JWT.
- **Jwts**: jjwt 라이브러리에서 제공하는 유틸리티 클래스. 코드에서 토큰을 만들거나 해석할 때 항상 Jwts를 시작점으로 사용. |

🌱 정리 키워드

- JWT = 토큰 개념
- JWS = 서명 검증된 JWT
- Jwts = 자바 라이브러리 클래스(도구 상자)

---

### 💡 9. 왜 토큰 생성은 builder()인데 검증은 parserBuilder()야?

| 질문 | 답변 요약 |
| --- | --- |
| builder랑 parserBuilder 차이가 뭐야? | - **builder()**: 새 JWT를 만들고 싶을 때. 토큰 생성용 Builder 객체 반환 → setSubject, setExpiration, signWith → compact()로 최종 문자열 완성.  
- **parserBuilder()**: 이미 발급된 JWT를 해석하고 싶을 때. 토큰 검증/해석용 ParserBuilder 반환 → setSigningKey → build() → parseClaimsJws(token) 실행. |

🌱 정리 키워드

- builder() = 토큰 생성 시작점
- parserBuilder() = 토큰 검증/해석 시작점