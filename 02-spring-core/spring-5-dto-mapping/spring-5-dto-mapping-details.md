# [SPRING-5단계] 추가 설명 - 예시 코드 분석

## 🧾 예시 코드 전체 구조

이번 실습에는 총 3개 클래스가 있어:

1. `SpringbootStructureApplication.java` – **메인 실행 클래스 (앱 진입점)**
2. `OrderController.java` – **POST 요청을 처리하는 컨트롤러**
3. `OrderRequestDto.java / OrderResponseDto.java` – **요청/응답용 데이터 객체 (DTO)**

---

## 🔍 SpringbootStructureApplication.java — **애플리케이션 진입점**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

- `SpringApplication`: Spring Boot를 실행하는 클래스 (내장 서버 실행)
- `@SpringBootApplication`: 아래 3개의 기능을 포함하는 복합 애노테이션
    - `@Configuration`: 설정 클래스
    - `@EnableAutoConfiguration`: 자동 설정
    - `@ComponentScan`: 컴포넌트 자동 탐색 및 빈 등록

---

```java
@SpringBootApplication
public class SpringbootStructureApplication {
```

- 스프링 부트 앱의 메인 클래스
- 클래스 이름은 `Application`으로 끝나는 게 관례

---

```java
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStructureApplication.class, args);
    }
}
```

- `main()` 메서드는 프로그램의 시작점
- `SpringApplication.run(...)`이 실행되면서 스프링 부트 앱이 시작됨 → 서버 실행 + 컴포넌트 등록

---

## 🔍 OrderController.java — **POST 요청 처리 컨트롤러**

```java
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
```

- REST API용 컨트롤러를 만들기 위한 import
    - `@RestController`: JSON 응답 컨트롤러
    - `@PostMapping`: POST 요청 처리
    - `@RequestBody`: 요청 본문의 JSON을 DTO로 매핑

---

```java
@RestController
public class OrderController {
```

- `@RestController`: 이 클래스는 뷰 없이 JSON만 응답하는 컨트롤러임을 명시
- 클래스 이름은 자유롭게 지정 가능하지만 관례적으로 ~Controller로 끝남

---

```java
    @PostMapping("/orders")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
```

- `@PostMapping("/orders")`: `/orders` 경로로 들어오는 POST 요청 처리
- `@RequestBody`: 클라이언트가 보낸 JSON을 `OrderRequestDto` 객체로 자동 매핑
- 리턴 타입이 `OrderResponseDto`니까 이 객체가 JSON으로 변환돼서 클라이언트에 응답됨

---

```java
        return new OrderResponseDto(
            orderRequestDto.getItem(),
            orderRequestDto.getQuantity(),
            "주문이 성공적으로 접수되었습니다."
        );
    }
}
```

- 요청 DTO에서 값 꺼내기: `getItem()`, `getQuantity()`
- 메시지를 포함한 응답 객체를 생성 → JSON으로 응답됨

---

## 🔍 OrderRequestDto.java — **요청 데이터 전용 DTO**

```java
public class OrderRequestDto {
    private String item;
    private int quantity;
```

- 클라이언트가 보내는 JSON 데이터를 담는 순수 데이터 객체
- 필드는 `private`으로 선언 → 보안 + 캡슐화

---

```java
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
}
```

- getter 메서드는 Jackson이 JSON → DTO 매핑 시 내부 필드에 접근할 수 있도록 해줌
- setter는 없어도 Jackson은 리플렉션으로 직접 필드 주입 가능함

---

## 🔍 OrderResponseDto.java — **응답 데이터 전용 DTO**

```java
public class OrderResponseDto {
    private String item;
    private int quantity;
    private String message;
```

- 응답에 담을 정보를 가진 클래스
- 클라이언트에게 전달할 값만 담는 게 원칙

---

```java
    public OrderResponseDto(String item, int quantity, String message) {
        this.item = item;
        this.quantity = quantity;
        this.message = message;
    }
```

- 생성자를 통해 응답 데이터를 초기화

---

```java
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getMessage() { return message; }
}
```

- JSON 응답 생성을 위해 필요한 getter 메서드들

---

## 🔁 전체 흐름 요약

```
1. SpringBootStructureApplication 실행 → 서버 시작
2. @RestController가 붙은 OrderController가 자동 등록됨
3. 클라이언트가 POST /orders 요청과 함께 JSON 데이터 전송
4. @RequestBody로 JSON → OrderRequestDto 객체로 매핑
5. OrderResponseDto를 생성해 응답 → JSON으로 변환되어 클라이언트에게 전달
```

---

## 🌱 봄이 맞춤 포인트

| 개념 | 설명 |
| --- | --- |
| @RequestBody | 요청 본문(JSON)을 DTO로 변환해주는 핵심 어노테이션 |
| DTO | 요청과 응답에 사용하는 순수 데이터 객체 (비즈니스 로직 없음) |
| 자동 변환 | 스프링 부트 + Jackson이 객체 <-> JSON 자동 변환해줌 |
| POST 요청 처리 | `@PostMapping`으로 POST API 작성 흐름 체험 |
| 응답 구조 설계 | 필요한 필드만 담아서 가볍고 명확한 응답 설계 가능 |