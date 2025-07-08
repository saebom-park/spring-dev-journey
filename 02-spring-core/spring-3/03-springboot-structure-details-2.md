# [SPRING-3단계] 무조건 암기해도 되는 Spring Boot 실전 패턴 세트

### ✅ 1. 메인 클래스 (SpringBootApplication 진입점)

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectNameApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectNameApplication.class, args);
    }
}
```

| 왜 암기하냐? | 스프링 부트는 항상 이 구조로 시작해! 반복 외우면 부팅은 손코딩으로 가능해짐 |

---

### ✅ 2. 컨트롤러 구성

```java
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "봄이", "spring@dev.com");
    }
}
```

| 왜 암기하냐? | `@RestController`, `@GetMapping`, `@PathVariable` 세트는 거의 모든 API에서 쓰임 |

---

### ✅ 3. DTO 구조

```java
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

| 왜 암기하냐? | DTO는 이름만 바뀌고 구조는 늘 비슷해. 한 번 외우면 모든 도메인에 써먹을 수 있어! |

---

## 💡 팁: 이렇게 외우면 돼!

- 🌿 "메인 클래스 3줄 + run()"
- 🌿 "컨트롤러 4 import + 3 애노테이션 + 1 메서드"
- 🌿 "DTO: 필드 + 생성자 + getter 세트"