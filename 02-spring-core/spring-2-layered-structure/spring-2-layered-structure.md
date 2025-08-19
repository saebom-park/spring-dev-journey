# [SPRING-2단계] 3계층 구조 실습

> ✨ "비즈니스 로직, 요청 처리, 데이터 접근을 분리하면 어떤 이점이 있을까?"
👉 스프링은 Controller, Service, Repository 계층을 나누어 역할 중심 설계를 가능하게 해!
> 

---

## 💡 핵심 개념 요약

| 계층 | 설명 |
| --- | --- |
| Controller | 클라이언트 요청을 받아 처리 시작. Service 호출 역할 |
| Service | 핵심 비즈니스 로직 처리. Repository에 데이터 저장 요청 |
| Repository | 실제 데이터를 저장하거나 조회하는 계층 (DB, 메모리 등) |
| 관심사의 분리 | 역할에 따라 책임을 나누면 코드가 유연하고 유지보수가 쉬워짐 |

---

## 🧾 예시 코드

> 예시 파일: App.java, AppConfig.java, OrderController.java, OrderService.java, OrderRepository.java
> 

```java
package com.springlab5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderController orderController = context.getBean(OrderController.class);
        orderController.placeOrder(123L);
    }
}
```

```java
package com.springlab5;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.springlab5")
public class AppConfig {
}
```

```java
package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void placeOrder(Long id) {
        System.out.println("🧾 주문 접수됨: ID = " + id);
        orderService.processOrder(id);
    }
}
```

```java
package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void processOrder(Long id) {
        orderRepository.save(id);
        System.out.println("📦 주문 처리 완료: ID = " + id);
    }
}
```

```java
package com.springlab5;

import org.springframework.stereotype.Component;

@Component
public class OrderRepository {
    public void save(Long id) {
        System.out.println("💾 주문 저장됨: ID = " + id);
    }
}
```

---

## 📝 각 파일 설명

| 파일명 | 역할 |
| --- | --- |
| `App.java` | main 메서드로 실행 시작, 컨테이너 생성 및 컨트롤러 호출 |
| `OrderController.java` | 요청 처리 시작점. Service 계층 호출 |
| `OrderService.java` | 비즈니스 로직 담당. Repository 호출함 |
| `OrderRepository.java` | 주문 ID를 저장하는 저장소 역할 (콘솔 출력) |
| `AppConfig.java` | 컴포넌트 스캔 설정 포함한 구성 클래스 |

---

## 📌 포인트 요약

- 3계층 구조: Controller → Service → Repository 흐름 구성
- 각 계층은 역할이 분리되어 테스트와 유지보수가 용이함
- 모든 클래스에 `@Component`로 빈 등록 후 자동 스캔
- 생성자 주입 방식(`final` 필드 + 생성자 사용)으로 의존성 주입
- `@ComponentScan`으로 패키지 경로 명시 필요
- 콘솔 출력으로 각 계층 흐름을 직접 확인 가능

---

## 🧪 실습 미션

🎯 목표: **3계층 구조를 구성하고, 각 계층의 역할과 의존성 흐름을 직접 체험한다.**

1. `springlab6` 패키지 생성
2. 아래 클래스 생성 및 구성:

| 클래스명 | 계층 역할 |
| --- | --- |
| `App.java` | 실행 메인 |
| `AppConfig.java` | 컴포넌트 스캔 설정 |
| `BookController.java` | 컨트롤러 |
| `BookService.java` | 서비스 |
| `BookRepository.java` | 저장소 |
1. `BookController`의 `createBook()` 실행 시 다음과 같은 출력이 나와야 함:

```
📚 도서 등록 요청: ID = 101
💾 도서 저장 완료: ID = 101
✅ 도서 등록 완료: ID = 101
```

> 참고: DB 없이 콘솔 출력 기반 흐름만 구현하며, 도서(book)를 관리하는 시나리오로 3계층 구조를 구성할 것
>