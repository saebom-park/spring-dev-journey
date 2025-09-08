# [SEC-2단계] Builder 패턴 완전 해부

> Builder 패턴의 구조와 동작 방식을 한 줄씩 해석하고, 실제 `builder()` 메서드 내부 구현과 최종 사용 예제를 통해 완전히 이해한다.  

---

## 📄 전체 코드 (User + Builder 내부 구현)

```java
public class User {
    private String username;
    private int age;
    private String role;

    // Builder 시작 메서드
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // 내부 Builder 클래스
    public static class UserBuilder {
        private String username;
        private int age;
        private String role;

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            User user = new User();
            user.username = this.username;
            user.age = this.age;
            user.role = this.role;
            return user;
        }
    }
}
```

---

## 🧠 한 줄씩 해석

### 1. `public static UserBuilder builder()`
- `User.builder()` 호출 시 실행  
- 내부에서 `new UserBuilder()` 생성해 반환  
- 👉 “User 객체 만들 설계도 꺼내기”

---

### 2. `public static class UserBuilder`
- User와 똑같은 필드(username, age, role)를 가짐  
- 값들을 임시 저장하는 **설계도 클래스**

---

### 3. `public UserBuilder username(String username)`
- 체인 방식으로 값 채워넣기  
- 예: `.username("spring")` → 설계도에 `username = spring` 저장  

---

### 4. `public UserBuilder age(int age)` / `public UserBuilder role(String role)`
- 각각 age, role 채워넣기  
- `return this;` 덕분에 이어서 `.age(25).role("USER")` 식으로 연결 가능  

---

### 5. `public User build()`
- 지금까지 설계도에 넣은 값들을 진짜 User 객체에 복사  
- 완성된 User 객체 반환  
- 👉 JWT의 `build()`와 동일: “설정 끝 → 완성품 생성”

---

## 💬 핵심 요약

- `builder()` = **UserBuilder 설계도 시작**  
- `username()`, `age()`, `role()` = **값 채워넣기**  
- `build()` = **최종 User 객체 생성**  
- Builder 패턴 덕분에 매개변수 순서에 안 헷갈리고, 필요한 값만 선택해서 넣을 수 있음  

---

## ✅ 최종 사용 예시

```java
User user = User.builder()
        .username("spring")
        .age(25)
        .role("USER")
        .build();
```

👉 결론: **우리가 Builder 패턴을 쓰는 이유는 바로 이렇게 가독성 좋게, 직관적으로 객체를 만들기 위해서야!**  
JWT 파서에서 `parserBuilder()...build()` 쓰는 것도 동일한 맥락이다.  
