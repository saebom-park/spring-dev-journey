# [SEC-4단계] 질문노트: 권한 설정 (User)

> 💬 내가 직접 궁금해서 물어본 것들 + 온이가 설명해준 요약
> 
> 
> (코드: com.springlab21.entity.User 실습 기준)
> 

---

### 💡 1. Set은 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| Set은 뭐야? | 자바의 **컬렉션(Collection)** 중 하나로, **중복을 허용하지 않는 집합 자료구조**야. List처럼 인덱스로 접근하는 게 아니라, 같은 값이 있으면 자동으로 걸러져. 순서는 보장하지 않지만, Role/권한처럼 **중복이 의미 없는 데이터**를 관리할 때 유용해. |

🌱 정리 키워드

- Set = 집합 자료구조
- 중복 허용 ❌
- 순서 보장 ❌
- 권한(Role) 저장에 적합

---

### 💡 2. @ElementCollection(fetch=FetchType.EAGER), @CollectionTable(name="user_roles", joinColumns=@JoinColumn(name="user_id")) 각각 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| @ElementCollection(fetch=FetchType.EAGER)는 뭐야? | 엔티티가 아닌 **값 타입 컬렉션**을 매핑할 때 쓰는 어노테이션이야. 여기서는 `Set<String> roles` 같은 단순 문자열 집합을 별도 테이블에 저장할 때 사용돼. `fetch=FetchType.EAGER`는 User를 조회할 때 roles 컬렉션도 항상 즉시 함께 가져오라는 뜻이야. |
| @CollectionTable(name="user_roles", joinColumns=@JoinColumn(name="user_id"))는 뭐야? | 값 컬렉션을 담을 별도 테이블 정보를 지정하는 거야. `name="user_roles"` → 테이블 이름을 user_roles로 만들라는 뜻. `joinColumns=@JoinColumn(name="user_id")` → 이 컬렉션 테이블이 User 엔티티와 외래키(user_id)로 연결된다는 의미야. |

🌱 정리 키워드

- @ElementCollection = 값 타입 컬렉션 매핑
- fetch=EAGER = User 조회 시 roles도 즉시 조회
- @CollectionTable = 별도 테이블 정의
- joinColumns = User와 연결할 외래키 지정

---

### 💡 3. users 테이블에 이미 role 컬럼이 있는데, 왜 user_roles 테이블에 user_id랑 role을 또 저장하는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| users 테이블에 role 컬럼 하나만 두면 안 돼? | 가능은 하지만, 한 사용자에게 권한이 여러 개일 경우(예: ROLE_USER + ROLE_ADMIN) 컬럼 하나로는 표현이 힘들어. |
| 그럼 user_roles 테이블을 쓰는 이유는? | `@ElementCollection` 방식은 **1:N 구조**로 권한을 저장할 수 있어. 즉, 한 명의 User가 여러 개의 Role을 가질 수 있게 하려고 별도 테이블(user_roles)을 둔 거야. DB 설계상 정규화도 잘 맞고, ROLE 추가/삭제가 유연해. |

🌱 정리 키워드

- users.role (단일 컬럼) = 권한 1개만 저장 가능
- user_roles 테이블 = 권한 여러 개 저장 가능 (1:N)
- 정규화된 구조 → 확장성 높음

---

### 💡 4. @Column(name="role") 이거는 users 테이블 컬럼이기도 하고 user_roles 테이블 컬럼이기도 한 거야?

| 질문 | 답변 요약 |
| --- | --- |
| @Column(name="role")는 어디 컬럼이야? | 이 어노테이션은 **user_roles 테이블의 컬럼**을 지정하는 거야. users 테이블과는 관계없어. |
| 왜냐면? | `@ElementCollection` + `@CollectionTable(name="user_roles")`를 쓰면 JPA는 별도 테이블을 만들어서 컬렉션 데이터를 저장해. 이때 각 Role 값이 들어가는 컬럼 이름을 `@Column(name="role")`로 정하는 거야. |

🌱 정리 키워드

- @Column(name="role") → **user_roles 테이블의 컬럼**
- users 테이블과 별개
- 각 Role 문자열을 저장하는 실제 컬럼 이름 지정

---

### 💡 5. 그럼 users 테이블에 role 컬럼은 필요 없는 거야?

| 질문 | 답변 요약 |
| --- | --- |
| users 테이블에 role 컬럼을 남겨둬야 해? | 굳이 필요 없어. 역할을 여러 개 가질 수 있는 구조라면 `user_roles` 테이블이 권한 저장을 전담하는 게 맞아. |
| 그렇다면 users.role은 언제 쓰여? | 예전 방식(권한 1개만 저장)에서는 쓰일 수 있어. 하지만 권한이 여러 개인 경우엔 컬렉션 테이블을 쓰는 게 더 정규화되고 유연해. 실무에서는 보통 users 테이블에 role 컬럼은 두지 않고, user_roles 테이블만 둬. |

🌱 정리 키워드

- users.role 컬럼 = 권한 1개만 필요할 때만 사용 가능
- user_roles 테이블 = 권한 여러 개 저장 가능, 더 실무적
- 실무에서는 user_roles 중심 설계

---

### 💡 6. 보통 엔티티 필드값이 컬럼명이잖아. 아 그럼 @Column(name="role")은 그냥 member-order 관계에서처럼 읽기 전용 필드 같은 거야?

| 질문 | 답변 요약 |
| --- | --- |
| @Column(name="role")이 읽기 전용 같은 건가? | 조금 달라. `@ElementCollection`이랑 같이 쓰이는 @Column은 **roles 컬렉션 안에 들어가는 값**이 저장될 **컬렉션 테이블의 컬럼 이름**을 지정하는 거야. 즉, 이건 users 테이블 컬럼이 아니라 user_roles 테이블에 실제로 생기는 "role" 컬럼이야. |
| member-order 관계랑 비교하면? | 거기서의 읽기 전용 필드는 연관관계 매핑 시 반대편 데이터를 단순 조회만 하게 할 때 쓰는 거고, 여기서는 실제 저장 컬럼을 정의하는 거라 용도가 달라. |

🌱 정리 키워드

- @Column(name="role") = user_roles 테이블에 생기는 컬럼
- 읽기 전용 ❌ → 실제 Role 값이 저장되는 컬럼
- member-order 관계의 읽기 전용 필드(@ManyToOne 등과 차이 있음)

---

### 💡 7. 지난단계에서는 @Controller 썼는데, 왜 이번엔 @RestController로 돌아온 거야?

| 질문 | 답변 요약 |
| --- | --- |
| 지난단계에서는 @Controller를 썼는데, 이번엔 @RestController로 바뀐 이유는? | 지난 단계(로그인 페이지)는 **html 뷰를 반환**해야 해서 `@Controller`를 사용했어. `return "login";` 같은 뷰 이름을 리턴해야 했거든. 이번 단계(SEC-4)는 **보호된 API 요청/응답**을 테스트하는 거라, html 뷰가 아니라 **문자열이나 JSON을 바로 HTTP 응답으로 내려주면 돼.** 그래서 `@RestController`를 쓰는 게 맞아. |
| 실무에서는 언제 뭘 써? | 보통 **웹페이지 렌더링 프로젝트** → `@Controller`, **API 서버** → `@RestController`를 주로 사용해. 두 가지를 한 프로젝트 안에 섞어 쓰는 것도 가능해. |

🌱 정리 키워드

- @Controller → html 뷰 반환 (템플릿)
- @RestController → 데이터 반환 (JSON/Text)
- 로그인 화면 = @Controller, 권한 테스트 API = @RestController

---

### 💡 8. 컨트롤러 메서드 이름은 어떤 기준으로 지어야 해?

| 질문 | 답변 요약 |
| --- | --- |
| 메서드명을 adminPage로 한 기준은 뭐야? | 컨트롤러 메서드 이름은 **HTTP URL이나 역할을 설명하는 의미 있는 동사/명사 조합**으로 짓는 게 좋아. Spring MVC는 메서드 이름 자체를 호출하지 않으니까 기능 동작엔 영향 없지만, 코드 읽는 사람 입장에서 직관적인 게 중요해. |
| 그럼 네이밍 가이드가 있어? | 보통 **응답하는 자원(Resource)** 중심으로 이름을 정해. 예: `/admin/dashboard` → `adminDashboard()`, `/user/profile` → `getProfile()`. REST API라면 `getUsers()`, `createUser()`, `deleteUser()`처럼 HTTP 동작을 드러내기도 해. |

🌱 정리 키워드

- 기능 동작엔 영향 없음 → 가독성 위해 짓는 것
- 자원(Resource)나 기능 중심 네이밍 권장
- REST API라면 CRUD 동작이 드러나는 이름 선호

---

### 💡 9. import 문에서 `prepost`는 무슨 뜻이야?

| 질문 | 답변 요약 |
| --- | --- |
| `import org.springframework.security.access.prepost.PreAuthorize;` 여기서 prepost는 무슨 의미야? | Spring Security에서 **메서드 단위 권한 제어**를 할 때 쓰는 애노테이션 묶음이 `prepost` 패키지야. "pre"는 메서드 실행 전에 검사, "post"는 메서드 실행 후에 검사를 의미해. |
| 주요 애노테이션은 뭐가 있어? | `@PreAuthorize`(실행 전 권한 확인), `@PostAuthorize`(실행 후 반환값까지 고려해서 권한 확인), `@PreFilter`(실행 전 파라미터 필터링), `@PostFilter`(실행 후 결과 필터링). |

🌱 정리 키워드

- pre = 실행 전 권한/데이터 검사
- post = 실행 후 권한/데이터 검사
- @PreAuthorize / @PostAuthorize / @PreFilter / @PostFilter 제공
- 메서드 단위 보안 제어용 패키지

---

### 💡 10. @PreAuthorize랑 SecurityConfig에서 설정하는 hasRole 차이는 뭐야?

| 질문 | 답변 요약 |
| --- | --- |
| SecurityConfig에서 `.requestMatchers("/admin/**").hasRole("ADMIN")`랑, `@PreAuthorize("hasRole('ADMIN')")`는 뭐가 달라? | 둘 다 **권한 체크**를 하는 건 맞아. 하지만 적용 범위가 달라. |
| SecurityConfig (HttpSecurity DSL) | **URL 단위 보안**을 설정하는 거야. 특정 경로 패턴(`/admin/**`)에 접근할 때 권한을 검사. 주로 컨트롤러 전반에 걸친 URL 접근 제어에 사용해. |
| @PreAuthorize | **메서드 단위 보안**을 설정하는 거야. 컨트롤러, 서비스 메서드 위에 붙여서 실행 전에 권한을 검사. URL뿐 아니라 메서드 로직 자체의 접근 권한을 제어할 수 있어. |

🌱 정리 키워드

- SecurityConfig → URL 단위 접근 제어
- @PreAuthorize → 메서드 단위 접근 제어
- 실무에서는 URL 보안(큰 틀) + 메서드 보안(세밀한 제어) **둘 다 병행**