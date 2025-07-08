# [SPRING-3단계] 추가 설명 - 예시 코드 분석

## 🧾 예시 코드 전체 구조

Spring Boot 구조에는 총 3개 클래스가 있어:

1. `SpringbootStructureApplication.java` – **메인 실행 클래스 (앱 진입점)**
2. `UserController.java` – **REST API 컨트롤러**
3. `UserDto.java` – **응답용 데이터 객체 (DTO)**

---

## 🔍 SpringbootStructureApplication.java — **애플리케이션 진입점**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

- `SpringApplication`: Spring Boot를 실행하는 클래스 (서버 띄워줌)
- `@SpringBootApplication`: 아래 3개 애노테이션을 합친 복합 애노테이션
    - `@Configuration`: 설정 클래스
    - `@EnableAutoConfiguration`: 자동 설정
    - `@ComponentScan`: 컴포넌트 자동 등록

---

```java
@SpringBootApplication
public class SpringbootStructureApplication {
```

- 스프링 부트의 "메인 클래스"
- 클래스 이름은 원하는 이름으로 바꿔도 되지만, 관례적으로 `Application`으로 끝남

---

```java
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStructureApplication.class, args);
    }
}
```

- `main()` 메서드는 자바 애플리케이션의 시작점
- `SpringApplication.run(...)`으로 스프링 부트를 시작함 → 서버 실행 + 컴포넌트 스캔 + 빈 등록 모두 자동

---

## 🔍 UserController.java — **REST API 요청 처리 클래스**

```java
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
```

- API 관련 애노테이션들을 사용하기 위한 import
    - `@RestController`: JSON 응답용 컨트롤러
    - `@GetMapping`: GET 요청 처리
    - `@PathVariable`: URL에서 값 추출
    - `@RequestMapping`: 공통 경로 매핑

---

```java
@RestController
@RequestMapping("/users")
public class UserController {
```

- `@RestController`: 이 클래스는 JSON 응답을 위한 컨트롤러야 (뷰 없이 데이터 응답)
- `@RequestMapping("/users")`: 이 클래스 아래의 모든 요청은 `/users`로 시작

---

```java
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "봄이", "spring@dev.com");
    }
}
```

- `@GetMapping("/{id}")`: `/users/1` 같은 요청을 처리
- `@PathVariable`: URL 경로에서 `{id}` 값을 `id` 변수에 바인딩
- `new UserDto(...)`: 사용자 정보를 담은 객체를 생성해 리턴
- Spring Boot + Jackson 덕분에 `UserDto` → JSON으로 자동 변환되어 응답됨

---

## 🔍 UserDto.java — **응답 데이터 전용 클래스 (DTO)**

```java
public class UserDto {
    private Long id;
    private String name;
    private String email;
```

- `UserDto`는 클라이언트에게 전달할 데이터를 담는 객체
- `private` 필드로 데이터를 숨기고, getter로만 노출

---

```java
    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
```

- 생성자: 객체를 만들 때 id, name, email을 받아 필드에 채움

---

```java
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

- getter 메서드는 JSON 변환 시 필드 값을 꺼낼 수 있도록 해줘
- Jackson은 getter를 보고 JSON 키-값을 생성해

---

## 🔁 전체 흐름 요약

```
1. 프로젝트 시작 시 → SpringbootStructureApplication 실행됨
2. 내부적으로 @SpringBootApplication이 컴포넌트 자동 등록 시작
3. @RestController가 붙은 UserController가 자동 등록됨
4. /users/{id} 요청이 오면 → UserDto 객체를 생성해 JSON으로 응답
5. 클라이언트는 JSON으로 사용자 정보를 받게 됨
```

---

## 🌱 봄이 맞춤 포인트

| 개념 | 설명 |
| --- | --- |
| Spring Boot 자동 구성 | `@SpringBootApplication` 하나로 컴포넌트 스캔 + 설정 자동화 |
| JSON 응답 | `@RestController` 덕분에 객체 → JSON 자동 변환됨 |
| DTO 역할 | 데이터를 주고받을 때, 필요한 정보만 묶는 순수 데이터 객체 |
| Jackson | DTO → JSON 변환을 자동으로 해주는 라이브러리 (내장됨) |
| REST API | URL 경로(`/users/{id}`)로 요청 → 응답은 JSON 형태로 리턴 |