# [SPRING-1단계] 추가 설명 2 - 용어 정리

## 💡 1. **컨테이너 (Container)**

### 📘 정의

> 객체를 생성하고, 연결하고, 관리해주는 큰 상자 (= Spring의 핵심 기능)
> 

### 🧠 쉽게 말하면

- Spring에서는 모든 객체를 **개발자가 new 하지 않아도** 됨
- 이 모든 객체들을 대신 **생성하고 보관하는 공간**이 바로 **스프링 컨테이너**
- 너가 객체를 꺼낼 땐 → `context.getBean(...)` 처럼 **컨테이너에서 꺼내는 것**

### 🎨 비유

> 공장에 주문서(AppConfig)를 제출하면, 컨테이너가 필요한 부품(Car, Engine)을 조립해서 완제품(Car)을 꺼내주는 구조!
> 

---

## 💡 2. **구현체 (Implementation class)**

### 📘 정의

> 인터페이스 또는 추상 클래스를 실제로 동작하게 만든 클래스
> 

### 🧠 쉽게 말하면

- 예를 들어, 자동차 설계도(Car 인터페이스)가 있다면
- 실제로 움직이는 차를 만든 클래스가 바로 **구현체(CarImpl 클래스)**

### ✅ 지금 코드에서 예시

```java
java
복사편집
ApplicationContext context = new AnnotationConfigApplicationContext(...);

```

- `ApplicationContext`는 인터페이스
- `AnnotationConfigApplicationContext`는 그 **구현체**
    
    → 실제로 동작 가능한 구체 클래스
    

---

## 💡 3. **컴포넌트 (Component)**

### 📘 정의

> 스프링에게 “이 클래스는 자동으로 관리해줘!” 라고 알려주는 표시
> 

### 🧠 쉽게 말하면

- 클래스 위에 `@Component` 붙이면,
- “얘는 자주 쓰는 부품이니까 스프링 컨테이너에 등록해줘!” 라는 뜻
- 그러면 스프링이 이 클래스를 Bean으로 만들어서 관리해줘

### 🎨 비유

> “자동차 조립 공장에 내가 쓸 엔진 부품입니다~” 라고 붙이는 태그
> 

---

## 💡 4. **Bean (빈)**

### 📘 정의

> 스프링 컨테이너가 관리하는 객체
> 

### 🧠 쉽게 말하면

- Spring 입장에서 **@Component, @Service, @Repository 등으로 등록된 모든 객체**를 다 “Bean”이라고 불러
- Bean은 스프링 컨테이너 안에서 생성되고, 재사용되고, 주입됨

### 🎨 비유

> 컨테이너 안에 담긴 조립 가능한 등록된 부품들
> 

---

## 💡 5. **ApplicationContext vs 구현체**

### 📘 정의

> ApplicationContext는 스프링 컨테이너를 제어하는 **인터페이스(추상 리모컨)**이며, AnnotationConfigApplicationContext는 이를 실제 구현한 **구현체(실제 리모컨)**이다.
> 

### 🧠 쉽게 말하면

- `ApplicationContext`는 “스프링 컨테이너를 다룰 수 있는 리모컨의 설명서(설계도)”야
- `AnnotationConfigApplicationContext`는 “실제로 손에 쥔 리모컨”으로, 자바 기반 설정을 읽고 Bean을 조립해주는 역할을 함
- 우리는 `context.getBean(...)`처럼 이 리모컨으로 컨테이너 안 객체를 꺼내서 사용함

### 🎨 비유

> 📺 ApplicationContext = 리모컨 설계도 (추상화)
🛠️ AnnotationConfigApplicationContext = 진짜 리모컨 (구현체)
🧰 컨테이너 = 조작 대상이 되는 스마트 TV 본체
> 

---

## 🌱 요약 정리 표

| 용어 | 핵심 설명 | 쉽게 말하면 |
| --- | --- | --- |
| 컨테이너 | 객체를 생성하고 관리하는 시스템 | 공장/창고 같은 공간 |
| 구현체 | 실제 동작하는 클래스 | 설계도(Car)를 실제로 만든 차(CarImpl) |
| 컴포넌트 | 스프링에 등록 요청하는 클래스 | “이 부품 써주세요!” 표시 |
| Bean | 컨테이너에 등록된 객체 | 조립 가능한 부품 |
| ApplicationContext vs 구현체 | 컨테이너를 조작하는 인터페이스 vs 그것을 실제 구현한 클래스 | 리모컨 설명서 vs 실제 리모컨 |