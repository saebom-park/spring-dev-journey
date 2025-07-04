# [SPRING-1단계] IoC, DI 개념

> ✨ "객체를 직접 만들지 않고도 사용할 수 있는 방법은 없을까?"
👉 스프링은 객체 생성과 주입을 대신해주는 IoC/DI 개념을 제공해!
> 

---

## 💡 핵심 개념 요약

| 용어 | 설명 |
| --- | --- |
| IoC (제어의 역전) | 객체 생성과 의존성 관리를 개발자가 아닌 프레임워크가 담당하는 구조 |
| DI (의존성 주입) | 필요한 객체를 외부에서 주입받는 방식 (생성자, 필드, setter 방식 등) |
| 관심사의 분리 | 객체의 생성과 사용을 분리하여 결합도를 낮추고 유연한 설계 가능 |
| 스프링 컨테이너 | IoC를 구현한 핵심 객체, Bean을 관리함 |
| Bean | 스프링 컨테이너가 관리하는 객체 (주로 @Component, @Service 등으로 등록) |

---

## 🧾 예시 코드

> 예시 파일: App.java, AppConfig.java, Car.java, Engine.java
> 

```java
package com.springlab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        Car car = context.getBean(Car.class);
        car.drive();
    }
}
```

```java
package com.springlab;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.springlab")
public class AppConfig {
}
```

```java
package com.springlab;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("자동차가 출발합니다.");
    }
}
```

```java
package com.springlab;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void start() {
        System.out.println("엔진이 가동됩니다.");
    }
}
```

---

## 📝 각 파일 설명

| 파일명 | 역할 |
| --- | --- |
| `App.java` | main 메서드가 있는 실행 클래스 (`context.getBean()` 호출) |
| `Car.java` | `@Component` 붙은 클래스, `Engine`에 의존 (생성자 주입) |
| `Engine.java` | `@Component` 붙은 클래스, `start()` 메서드만 있음 |
| `AppConfig.java` | `@Configuration` + `@ComponentScan` 설정 포함 |

## 💡 자주 쓰는 스프링 import 요약

🧩 설정 관련

- @Configuration → org.springframework.context.annotation.Configuration
- @ComponentScan → org.springframework.context.annotation.ComponentScan

📦 컨테이너

- ApplicationContext → org.springframework.context.ApplicationContext
- AnnotationConfigApplicationContext → org.springframework.context.annotation.AnnotationConfigApplicationContext

🌱 컴포넌트

- @Component → org.springframework.stereotype.Component
- @Service → org.springframework.stereotype.Service
- @Repository → org.springframework.stereotype.Repository

---

## 📌 포인트 요약

- `@Component`를 사용해 Bean 등록 → 자동으로 스프링 컨테이너에 의해 관리됨
- `@Component`를 붙이면 자동으로 Bean 등록됨
- `ApplicationContext`는 컨테이너 역할을 수행하며 `getBean()`으로 Bean 조회 가능
- `ComponentScan`으로 Bean 탐색 위치 지정 필요
- `@ComponentScan`은 반드시 **패키지 경로와 일치하게 설정**
- `Car` 클래스는 `Engine`에 의존 → 생성자 주입을 통해 IoC/DI 구조 구현
- `src/main/java` 구조와 패키지 구조 반드시 맞춰야 IntelliJ + Gradle이 정상 인식함

---

## 🧪 실습 미션 - 1

🎯 목표: IoC와 DI의 개념을 직접 구현해보며, 스프링의 핵심 동작 원리를 체득한다

1. `MessagePrinter`, `MessageService` 클래스를 생성한다
2. `MessageService`는 "Hello Spring!"을 출력하는 메서드 포함
3. `MessagePrinter`는 `MessageService`에 의존하며, 생성자 주입 방식으로 DI 구현
4. `AppConfig`를 설정하고, `ApplicationContext`에서 `MessagePrinter`를 호출해 메서드 실행

> 참고: 위 예시 코드와는 다른 도메인(메시지 출력)으로 구성하며, @Component 및 @Configuration 기반으로 IoC/DI를 체험해볼 것
> 

---

## 🧪 실습 미션 - 2: “MessagePrinter → Formatter → MessageService” 체인 구성

🎯 목표:

**DI의 구조적 확장 + 필드 주입/생성자 주입 차이점 + 초기화 타이밍(PostConstruct)**까지 직접 체험한다

---

### 1. 구조 설계

```
App.java
 └── MessagePrinter (출력 역할)
      └── MessageFormatter (문자열 가공)
           └── MessageService (데이터 제공)
```

---

### 2. 구현 요구사항

1. `MessageService`
    - `"Hello Spring!"` 리턴하는 `getMessage()` 메서드 구현
2. `MessageFormatter`
    - `MessageService`를 **필드 주입(@Autowired)** 받는다
    - `format()` 메서드로 `"📢 메시지: Hello Spring!"` 형태의 문자열 반환
    - `@PostConstruct`를 사용해 초기화 로그 출력
3. `MessagePrinter`
    - `MessageFormatter`를 **생성자 주입** 받는다
    - `print()` 메서드에서 포맷된 메시지를 출력
4. `App.java`
    - `ApplicationContext`로 컨테이너 생성 후 `MessagePrinter` 호출
5. 모든 클래스에 `@Component`, 설정 파일에는 `@Configuration + @ComponentScan`

---

### 💡 조건

- 반드시 `springlab3` 패키지 내에서 구성
- `@Autowired`, `@PostConstruct`, `@Component`, `@Configuration` 등 총동원
- 실행결과는 다음처럼 나와야 성공:

```
[초기화] Formatter 준비 완료
📢 메시지: Hello Spring!
```