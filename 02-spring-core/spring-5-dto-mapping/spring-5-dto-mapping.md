# [SPRING-5단계] DTO 설계 & JSON 매핑 (dto-mapping)

> ✨ "클라이언트가 보낸 JSON 데이터를 어떻게 DTO로 받고, 다시 JSON으로 응답할 수 있을까?"
👉 스프링은 @RequestBody를 이용해서 JSON 데이터를 DTO 객체로 바로 매핑할 수 있어. 응답도 DTO를 리턴하면 자동으로 JSON으로 변환돼!
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| DTO (Data Transfer Object) | 요청/응답 시 사용하는 순수 데이터 전달용 객체 |
| @RequestBody | JSON 요청 데이터를 자바 객체(DTO)로 변환해주는 애노테이션 |
| @PostMapping | POST 방식 요청 처리 애노테이션 |
| JSON 직렬화 | 객체 → JSON (응답 시), Jackson이 자동 처리 |
| JSON 역직렬화 | JSON → 객체 (요청 시), `@RequestBody`로 처리됨 |

---

## 🧾 예시 코드

> 예시 파일: SpringbootStructureApplication.java, OrderController.java, OrderRequestDto.java, OrderResponseDto.java
> 

```java
package com.springlab11;

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
package com.springlab11;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/orders")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return new OrderResponseDto(
            requestDto.getItem(),
            requestDto.getQuantity(),
            "주문이 성공적으로 접수되었습니다."
        );
    }
}

```

```java
package com.springlab11;

public class OrderRequestDto {
    private String item;
    private int quantity;

    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
}

```

```java

package com.springlab11;

public class OrderResponseDto {
    private String item;
    private int quantity;
    private String message;

    public OrderResponseDto(String item, int quantity, String message) {
        this.item = item;
        this.quantity = quantity;
        this.message = message;
    }

    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getMessage() { return message; }
}

```

---

## 📌 포인트 요약

- 클라이언트가 JSON으로 POST 요청 시 `@RequestBody`로 DTO 매핑
- 필드명은 JSON 키와 일치해야 자동 매핑됨
- 응답은 문자열이지만, 실제 서비스에서는 응답 DTO로 확장 가능
- Jackson이 getter 기반으로 값을 추출하고 JSON 변환 처리

---

## 🧪 실습 미션

> 🎯 목표: 상품 등록 API를 구현하고, JSON 요청/응답 매핑 구조를 익힌다.
> 
1. `ProductRequestDto.java` – 필드: `name`, `price`
2. `ProductResponseDto.java` – 필드: `name`, `price`, `message`
3. `ProductController.java`에 POST 요청 처리 메서드 작성:
    - URL: `/products`
    - 요청: `@RequestBody`로 `ProductRequestDto` 받기
    - 응답: `ProductResponseDto`로 JSON 반환
4. Postman 또는 브라우저 확장 도구로 POST 요청 테스트
5. JSON 요청 → DTO → 처리 → DTO → JSON 응답 전체 흐름 익히기

> 참고: 요청/응답 DTO는 서로 다른 클래스로 구분하는 것이 유지보수에 유리함
> 

---