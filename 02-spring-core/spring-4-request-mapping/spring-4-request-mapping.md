# [SPRING-4단계] @RestController와 요청 매핑

> ✨ "요청 URL을 어떻게 매핑하고, 어떤 방식으로 값을 전달받을 수 있을까?"
👉 스프링은 다양한 방식의 요청 매핑을 제공해. GET/POST 요청을 구분하고, 경로 변수나 쿼리 파라미터도 쉽게 받을 수 있어!
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| @RestController | JSON 응답을 위한 컨트롤러 지정 (@Controller + @ResponseBody 조합) |
| @RequestMapping | 클래스 또는 메서드에 요청 경로를 지정 (GET/POST 등 명시 X) |
| @GetMapping | GET 방식 요청을 처리하는 단축 애노테이션 |
| @PathVariable | 경로 변수 추출 (예: /users/{id} → 변수 매핑) |
| @RequestParam | 쿼리 파라미터 추출 (예: /search?keyword=abc → 변수 매핑) |

---

## 🧾 예시 코드

> 예시 파일: SpringbootStructureApplication.java, UserSearchController.java, UserDto.java
> 

```java
package com.springlab9;

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
package com.springlab9;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserSearchController {

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return new UserDto(id, "봄이", "spring@dev.com");
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword) {
        return "검색어: " + keyword;
    }
}

```

```java
package com.springlab9;

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

- `@RestController`는 JSON 응답에 특화된 컨트롤러
- `@PathVariable`은 URL 경로 일부 값을 변수로 매핑
- `@RequestParam`은 쿼리 스트링을 메서드 인자로 추출함
- `/users/3` → JSON 응답: `{ "id": 3, "name": "봄이", "email": "spring@dev.com" }`
- `/users/search?keyword=스프링` → 문자열 응답: `검색어: 스프링`

---

## 🧪 실습 미션

> 🎯 목표: 상품 정보 조회 API를 만들고, 경로 변수 및 쿼리 파라미터 매핑을 직접 체험한다.
> 
1. `ProductDto.java` – 필드: `productId`, `name`, `price`
2. `ProductController.java` 생성 후, 아래 요청을 처리:
    - `/products/{productId}` 요청 시: `@PathVariable`로 productId 추출 → DTO 반환
    - `/products/search?name=xxx` 요청 시: `@RequestParam`으로 name 추출 → 문자열 응답
3. 각각의 요청에 대해 브라우저에서 직접 테스트
4. JSON 응답 구조 확인 및 `@RestController` 동작 복습

> 참고: 실습 시 @RestController, @GetMapping, @PathVariable, @RequestParam을 혼합해 다양한 방식의 요청을 테스트해볼 것
> 

---