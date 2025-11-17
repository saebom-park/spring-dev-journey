# [REVIEW-3-1단계] 실수노트

> 💻 실습 코드: com.review31 패키지 기반 손코딩 복습

---

### 😅 실수 1 — `@Repository` 어노테이션 누락

```java
// ❌ 누락된 코드
public class MemberRepository {
```

✅ 정답:

```java
@Repository
public class MemberRepository {
```

📌 설명:

- @Repository를 빠뜨리면 Spring이 빈으로 인식하지 못해 의존성 주입 실패
- 계층별 어노테이션은 **기계적으로 작성하는 습관** 필요

---

### 😅 실수 2 — setter 메서드 오타 (`SetName` → `setName`)

```java
// ❌ 잘못된 메서드명
public void SetName(String name) { ... }
```

✅ 정답:

```java
public void setName(String name) {
    this.name = name;
}
```

📌 설명:

- 자바 메서드는 항상 소문자로 시작 (camelCase)
- 대소문자 오타로 Jackson에서 JSON 직렬화가 깨질 수 있음

---

### 😅 실수 3 — Member 생성자에 `id` 포함

```java
// ❌ 전체 생성자에 id 포함
public Member(Long id, String name, String email, int age) { ... }
```

✅ 정답:

```java
public Member(String name, String email, int age) {
    this.name = name;
    this.email = email;
    this.age = age;
}
```

📌 설명:

- ID는 저장소 내부에서 생성되므로 외부 입력에서 받으면 안 됨
- DTO → Entity 변환 시 id는 포함하지 않아야 함

---

### 😅 실수 4 — Entity 생성 시 setter 방식 사용

```java
// ❌ 필드별 setter로 값 세팅
Member member = new Member();
member.setName(...);
member.setEmail(...);
```

✅ 정답:

```java
Member member = new Member(requestDto.getName(), requestDto.getEmail(), requestDto.getAge());
```

📌 설명:

- 생성자 방식은 가독성 높고 불변 객체 구성에도 유리
- setter는 변경 가능성이 높아 관리 어려움

---

### 😅 실수 5 — findAll() 반환 타입 실수

```java
// ❌ Map으로 반환
public Map<Long, Member> findAll() { ... }
```

✅ 정답:

```java
public List<Member> findAll() {
    return new ArrayList<>(store.values());
}
```

📌 설명:

- 컨트롤러/서비스는 대부분 `List`를 기반으로 동작
- 클라이언트에 JSON으로 응답 시 `List`가 더 자연스러움

---

### 📌 요약 포인트

- `@Repository`, `@Service`, `@RestController`는 기본 어노테이션 → 절대 생략 금지!
- 자바 메서드명은 camelCase! 대문자 오타 주의
- Entity 생성은 생성자 방식으로! setter 최소화
- DTO 변환 시 id 포함 여부 주의
- 컨트롤러 응답은 List 구조가 기본!
