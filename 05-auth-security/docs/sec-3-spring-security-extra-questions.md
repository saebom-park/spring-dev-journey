# [SEC-3-EXTRA1단계] 질문노트: Spring Security (SecurityConfigCustom)

> 💬 내가 직접 궁금해서 물어본 것들 + 온이가 설명해준 요약
> 
> 
> (코드: SecurityConfigCustom.java 실습 기준)
> 

---

### 💡 1. UserDetailsService 라는 객체가 어딨다는거야?

| 질문 | 답변 요약 |
| --- | --- |
| UserDetailsService 라는 객체가 실제로 어디에 있는 거야? | UserDetailsService는 **객체가 아니라 인터페이스**야. 패키지는 `org.springframework.security.core.userdetails.UserDetailsService`에 있어. 실제로는 이걸 구현한 **구현체**(`InMemoryUserDetailsManager`, `JdbcUserDetailsManager`, `CustomUserDetailsService`)가 Bean으로 등록돼서 동작해. 로그인 시 Spring Security가 `loadUserByUsername()`을 자동 호출하면, 그 구현체가 DB나 메모리에서 사용자 찾아서 `UserDetails` 객체를 넘겨줘. |

🌱 정리 키워드

- UserDetailsService = 인터페이스 (객체 자체 아님)
- 실제 동작 = 구현체 (예: InMemoryUserDetailsManager)
- 로그인 시 loadUserByUsername() 호출
- UserDetails 반환 → PasswordEncoder로 검증

---

### 💡 2. crypto는 무슨 뜻이고 bcrypt는 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| crypto는 무슨 뜻이고, bcrypt는 무슨 뜻이야? | **crypto**는 Cryptography(암호학)의 줄임말로, 암호화/해시/서명 같은 보안 기능을 다루는 기술이나 라이브러리를 의미해. Spring Security의 `org.springframework.security.crypto` 패키지도 이걸 담당해. **bcrypt**는 비밀번호 해시 알고리즘으로, 단방향 해시 + salt 자동 추가 + 연산 난이도 조절 기능이 있어서 비밀번호 저장에 안전하게 쓰여. Spring Security에서 `BCryptPasswordEncoder`가 이 방식을 구현해. |

🌱 정리 키워드

- crypto = 암호학(Cryptography), 보안 관련 도구 모음
- bcrypt = 안전한 비밀번호 해시 알고리즘
- BCryptPasswordEncoder = bcrypt 구현 클래스

---

### 💡 3. import org.springframework.security.core.userdetails.User; 이거는 왜 필요한거야? 그리고 엔티티 User랑은 다른 거지?

| 질문 | 답변 요약 |
| --- | --- |
| import org.springframework.security.core.userdetails.User; 이건 왜 필요한 거야? | Spring Security에서 제공하는 **User 클래스**는 `UserDetails`의 기본 구현체야. Builder 패턴을 이용해 username, password, roles를 손쉽게 지정해서 `UserDetails` 객체를 만들 수 있어. InMemoryUserDetailsManager에 계정을 등록할 때 이걸 주로 사용해. |
| 엔티티 User랑 import한 User 클래스는 다른 거야? | 맞아, 완전히 다른 거야. `org.springframework.security.core.userdetails.User`는 로그인 검증용 `UserDetails` 구현체고, 우리가 JPA로 만드는 `@Entity User`는 DB 저장/관리용 클래스야. 이름만 같을 뿐 역할이 달라. 실무에서는 엔티티 User를 조회한 뒤 → CustomUserDetails로 감싸서 → Security에서 사용하는 UserDetails로 변환하는 흐름을 쓴다. |

🌱 정리 키워드

- Security User = UserDetails 구현체 (로그인 전용)
- Entity User = DB 저장/관리용 (회원 데이터)
- 역할 다름 → 실무에서는 Entity → CustomUserDetails → Security UserDetails 흐름

---

### 💡 4. formLogin(form -> form) 여기서 왜 람다 변수 이름이 form이야? 기본 수업 때는 login 썼는데?

| 질문 | 답변 요약 |
| --- | --- |
| formLogin(form -> form) 여기서 왜 람다 변수 이름이 form이야? 기본 수업 때는 login 썼는데? | 람다식에서 쓰는 변수 이름은 그냥 **개발자가 붙이는 별명**일 뿐이야. 실제 타입은 `FormLoginConfigurer<HttpSecurity>`로 동일해. 앞 수업에서 login -> login이라고 쓴 건 변수를 login으로 명명한 거고, 여기서는 form으로 한 거지. 어떤 이름을 쓰든 동작에는 차이가 없어. 보통 가독성을 위해 form이라고 자주 쓰는 것뿐이야. |

🌱 정리 키워드

- formLogin 람다 파라미터 = FormLoginConfigurer 타입
- 변수 이름은 개발자가 임의로 지정 가능
- login -> login, form -> form 모두 같은 의미
- 가독성을 위해 보통 form이라고 많이 씀

---

### 💡 5. defaultSuccessUrl(url, true) 여기서 true 값은 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| defaultSuccessUrl(url, true)에서 true는 무슨 뜻이야? | `true`는 **항상 지정한 URL로 리다이렉트하라**는 의미야. 기본적으로는 로그인 직전에 접근하려던 페이지로 리다이렉트하는데, true를 주면 무조건 `defaultSuccessUrl`로 보내버려. false면 "원래 가려던 페이지가 있으면 거기로, 없으면 defaultSuccessUrl로" 이런 동작을 해. |

🌱 정리 키워드

- defaultSuccessUrl(url, true) = 항상 해당 URL로 이동
- defaultSuccessUrl(url, false) = 원래 요청 있으면 그쪽, 없으면 해당 URL로 이동

---

### 💡 6. return "login"; 이게 어떻게 login.html로 반환되는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| return "login"; 이게 어떻게 login.html로 반환되는 거야? | Spring MVC에서 컨트롤러 메서드가 `String`을 리턴하면 그 값은 **뷰 이름(view name)**으로 처리돼. 스프링 부트의 ViewResolver가 이 뷰 이름에 prefix(`classpath:/templates/`), suffix(`.html`)를 붙여서 최종 경로를 찾는 거야. 그래서 `return "login";`은 결국 `resources/templates/login.html`을 찾아 렌더링해주는 거지. |

🌱 정리 키워드

- Controller 메서드에서 String = 뷰 이름
- ViewResolver가 prefix/suffix 붙여서 경로 조립
- "login" → templates/login.html 렌더링

---

### 💡 7. 왜 여기서는 @RestController가 아니라 @Controller를 쓰는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| 왜 여기서는 @RestController가 아니라 @Controller를 쓰는 거야? | `@Controller`는 뷰를 반환할 때 사용돼. 메서드가 String을 리턴하면 그걸 뷰 이름으로 해석해서 ViewResolver가 templates의 html을 찾아 보여줘. 반면에 `@RestController`는 데이터를 반환할 때 쓰는 거라, String을 리턴하면 그냥 그 문자열이 HTTP 응답으로 내려가. 이번 경우는 로그인 화면(html 뷰)을 띄우는 거라 `@Controller`를 써야 맞아. |

🌱 정리 키워드

- @Controller = 뷰 반환용 (템플릿 렌더링)
- @RestController = 데이터 반환용 (JSON/Text)
- 로그인 화면처럼 html 뷰 필요할 때는 @Controller 사용