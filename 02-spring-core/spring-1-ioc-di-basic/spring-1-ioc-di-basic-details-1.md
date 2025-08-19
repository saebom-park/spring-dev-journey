# [SPRING-1단계] 추가 설명 1 - 예시 코드 분석

## 🧾 예시 코드 전체 구조

코드에는 총 4개 클래스가 있어:

1. `App.java` – 실행 클래스
2. `Car.java` – 자동차 클래스 (엔진 필요)
3. `Engine.java` – 엔진 클래스
4. `AppConfig.java` – 설정 클래스

---

## 🔍 App.java — **실행의 시작점**

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
```

- 스프링에서 **컨테이너 역할**을 하는 클래스를 불러오는 코드야
- `ApplicationContext`: 스프링 컨테이너 (Bean 관리해줌)
- `AnnotationConfigApplicationContext`: Java 기반 설정 클래스(@Configuration)를 읽어서 컨테이너 생성해주는 구현체

---

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

- `AppConfig.class`를 기준으로 **컴포넌트 스캔이 시작됨**
- 이 시점에 `@Component`로 등록된 클래스들이 전부 Bean으로 등록돼

---

```java
        Car car = context.getBean(Car.class);
```

- `Car` 객체를 **스프링 컨테이너로부터 꺼내는 것**
- 이 `Car` 객체는 직접 new 한 게 아니라 **스프링이 대신 만들어준 것**

---

```java
        car.drive();
    }
}
```

- `car`에 있는 `drive()` 메서드 실행
- 내부적으로 `Engine`이 먼저 작동되고, 이후 “자동차가 출발합니다” 메시지 출력

---

## 🔍 Car.java — **자동차 클래스 (의존성 주입 대상)**

```java
@Component
class Car {
    private final Engine engine;
```

- `@Component`: 이 클래스는 스프링이 관리해야 하는 Bean이야!
- `Engine`이라는 객체를 필드로 가짐 → 즉, Car는 **Engine에 의존**

---

```java
    public Car(Engine engine) {
        this.engine = engine;
    }
```

- 생성자에 `Engine`이 들어와 → **생성자 주입 방식**
- 스프링이 자동으로 `Engine` Bean을 찾아서 넣어줘

---

```java
    public void drive() {
        engine.start();
        System.out.println("자동차가 출발합니다");
    }
}
```

- `drive()`를 호출하면 내부적으로 `engine.start()` → 그 다음에 자동차가 출발한다고 출력

---

## 🔍 Engine.java — **자동차에 필요한 엔진 역할**

```java
@Component
class Engine {
    public void start() {
        System.out.println("엔진이 가동됩니다");
    }
}
```

- 이것도 `@Component`로 등록된 Bean
- 스프링이 Engine 객체를 먼저 만들고, `Car`에 주입해줄 수 있게 됨

---

## 🔍 AppConfig.java — **설정 클래스**

```java

@Configuration
@ComponentScan(basePackages = "com.example")
class AppConfig {
}
```

- `@Configuration`: 이 클래스는 설정 전용 클래스야!
- `@ComponentScan`: 이 패키지 아래에서 `@Component`가 붙은 클래스를 **자동으로 스캔해서 Bean으로 등록**

> 지금 예시에서는 "com.example" 경로라고 적혀있지만, 실제로는 해당 클래스들이 있는 패키지 기준으로 맞춰야 해
> 

---

## 🔁 전체 흐름 요약

```
1. App 실행 → AppConfig 읽어서 스프링 컨테이너 시작
2. @Component 붙은 Car, Engine 클래스 자동 등록
3. Car가 Engine을 필요로 하니까 → Engine을 생성자에 자동으로 주입
4. context.getBean(Car.class) → 완성된 Car 꺼내옴
5. car.drive() 실행하면 내부에서 engine.start() → 자동차가 출발
```

---

## 🌱 봄이 맞춤 포인트

| 개념 | 요약 |
| --- | --- |
| IoC | 객체 생성, 연결을 **내가 아니라 스프링이** 한다 |
| DI | 필요한 객체를 **밖에서 넣어준다** (여기선 생성자 주입 방식) |
| @Component | 스프링한테 "이거 관리해줘!" 라고 등록하는 표시 |
| ApplicationContext | 스프링 컨테이너 — 여기서 Bean 꺼내쓴다 |