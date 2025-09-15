# [SEC-3단계] Spring Security 필터 체인 개념노트

> Spring Security의 핵심 구조인 DelegatingFilterProxy, FilterChainProxy, SecurityFilterChain을  
> **비유와 흐름 시뮬레이션**을 통해 이해하기 위한 문서입니다.  

---

## 📄 전체 구조 요약

```
[클라이언트 요청]
        ↓
 DelegatingFilterProxy (톰캣 레벨)
        ↓
   FilterChainProxy (보안 대장)
        ↓
 SecurityFilterChain (검문소 묶음)
        ↓
  여러 개의 Security Filter (여권 검사, 권한 확인 등)
        ↓
   DispatcherServlet → 컨트롤러 실행
```

---

## 🧠 한 단계씩 해석

### 1. DelegatingFilterProxy
- 서블릿 컨테이너(톰캣)에 등록되는 **문지기**  
- 요청이 들어올 때 "이건 Spring Security로 검사해야 돼!" 하고 보안 필터 체인으로 전달  

**비유:**  
> 공항 입구에 있는 첫 경비원.  
> “탑승객은 무조건 보안검색대로 보내야 한다” 하고 넘겨주는 역할  

---

### 2. FilterChainProxy
- 여러 개의 보안 규칙 집합을 관리하는 **보안 대장**  
- URL 패턴에 따라 알맞은 SecurityFilterChain을 선택  

**비유:**  
> 보안팀장: “국내선 승객은 A라인으로, 국제선 승객은 B라인으로 가세요.”  

---

### 3. SecurityFilterChain
- 요청 경로별로 적용되는 **검문소 묶음**  
- `/public/**` → 통과, `/secure/**` → 로그인 확인 필요  

**비유:**  
> “이 구역은 자유 출입, 저 구역은 여권 필수”라고 구역을 나눈 보안 검색대  

---

### 4. 주요 필터들

#### 4-1. UsernamePasswordAuthenticationFilter
- 로그인 폼에서 `username/password` 인증  

**비유:**  
> 탑승객이 신분증(ID, PW)을 보여주면 진짜인지 확인하는 직원  

---

#### 4-2. ExceptionTranslationFilter
- 인증/인가 도중 오류가 나면 처리  

**비유:**  
> “여권이 없으시네요, 저쪽 로그인 카운터로 가세요” 안내하는 직원  

---

#### 4-3. SecurityContextPersistenceFilter
- 로그인 성공한 사용자 정보를 **SecurityContext**에 저장/불러오기  

**비유:**  
> VIP라벨을 여권에 붙여서, 다음 보안검색대에서도 계속 확인할 수 있게 해줌  

---

## 🔄 흐름 시뮬레이션

### `/public/hello` 요청
1. DelegatingFilterProxy → 보안필터로 전달  
2. FilterChainProxy → “/public/**니까 자유통과”  
3. Controller 응답  

👉 로그인 필요 없이 바로 통과  

---

### `/secure/hello` 요청
1. DelegatingFilterProxy → 보안필터로 전달  
2. FilterChainProxy → “/secure/**니까 인증 필요”  
3. UsernamePasswordAuthenticationFilter → 로그인 안 돼 있으면 `/login`으로 리다이렉트  
4. 로그인 성공 시 SecurityContext에 사용자 정보 저장  
5. 이후 요청은 SecurityContext를 통해 “이미 로그인됨” 확인 가능  

👉 로그인 후 접근 가능  

---

## 💬 핵심 요약

- **Filter = 보안검색대**  
- **Filter Chain = 검색대 줄줄이 연결**  
- **DelegatingFilterProxy = 문 앞 경비원**  
- **FilterChainProxy = 보안 대장**  
- **SecurityFilterChain = 보안구역 규칙 모음**  
- **UsernamePasswordAuthenticationFilter = 신분증 검사원**  
- `/public/**`는 통과, `/secure/**`는 로그인 필요  
