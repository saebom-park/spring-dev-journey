# [SPRING-3단계] Spring Boot 프로젝트 구조

> ✨ "Spring Boot는 어떻게 구조화되어 있고, JSON 응답은 어떻게 처리될까?"
> 
> 
> 👉 `@SpringBootApplication`, `@RestController`, DTO를 이용해 REST API를 쉽게 만들 수 있어!
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| `@SpringBootApplication` | Spring Boot의 진입점. 내부에 `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan` 포함 |
| `@RestController` | `@Controller + @ResponseBody` 기능을 가진 JSON 응답 전용 컨트롤러 |
| DTO | Data Transfer Object. 계층 간 데이터 전달을 위한 순수 데이터 객체 |
| Jackson | 객체를 JSON으로 직렬화하는 라이브러리. Spring Boot에 내장됨 |
| `ResponseEntity` | 응답 상태 코드, 헤더, 바디를 모두 조작할 수 있는 응답 객체 |

---

## 🧾 예시 코드

> 예시 파일: SpringbootStructureApplication.java, UserController.java, UserDto.java
> 

```java
package com.springlab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootStructureApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStructureApplication.class, args);
    }
}
```

```java
package com.springlab7;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "봄이", "spring@dev.com");
    }
}
```

```java
package com.springlab7;

public class UserDto {
    private Long id;
    private String name;
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

---

## 📌 포인트 요약

- `@SpringBootApplication` 하나로 설정 + 스캔 + 자동 구성까지 한 번에 처리됨
- `@RestController`는 JSON 응답에 특화된 컨트롤러로 뷰를 리턴하지 않음
- DTO 객체는 클라이언트와 주고받을 데이터를 캡슐화함
- Jackson 덕분에 `UserDto` 객체는 자동으로 JSON으로 변환됨
- 실제 요청 예시: `GET /users/1` → JSON 응답 반환

---

## 🧪 실습 미션

> 🎯 목표: Spring Boot 구조에 익숙해지고, 책 정보를 반환하는 REST API를 구현한다.
> 
1. `BookDto.java` – 필드: `bookId`, `title`, `author`
2. `BookController.java` – `/books/{bookId}`로 요청 받기
3. 지정된 `bookId`에 대해 `BookDto`를 생성해 JSON 응답
4. 반환되는 JSON 예시:

```json
{
  "bookId": 101,
  "title": "스프링 완전 정복",
  "author": "온이"
}
```

1. `localhost:8080/books/101` 호출 시 결과 확인

> 참고: @RestController, @GetMapping, @PathVariable 어노테이션 필수 사용
> 
> 
> Jackson이 자동으로 DTO를 JSON으로 직렬화함
>