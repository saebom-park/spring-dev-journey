# [DBA-3단계] 실수노트: JPA 엔티티 & Repository

> 💻 실습 코드: Order.java, OrderRepository.java, InitDataRunner.java
> 

---

### 😅 실수 1 — `@Entity`는 Bean이 아니기 때문에 new로 생성해야 함

```java
@Autowired
private Order order; // ❌ 오류 발생: Entity는 Bean이 아님
```

✅ 정답:

```java
Order order = new Order(); // ✅ new로 직접 생성
```

📌 **설명**:

- `@Entity`는 JPA에서 DB 테이블과 매핑하는 객체일 뿐, Spring Bean처럼 관리되지 않음
- 스프링 컨테이너가 관리하는 Bean과 달리, Entity는 직접 `new`로 만들어야 함

---

### 😅 실수 2 — `JpaRepository<T, ID>`에서 ID 타입을 잘못 지정함

```java

public interface OrderRepository extends JpaRepository<Order, Order> { // ❌ ID 타입 오류
```

✅ 정답:

```java
public interface OrderRepository extends JpaRepository<Order, Long> { // ✅ ID 필드 타입 기준
```

📌 **설명**:

- 두 번째 제네릭 타입은 Entity 클래스의 `@Id` 필드 타입과 **정확히 일치해야** 함
- `@Id private Long id;` 라면 → `JpaRepository<Order, Long>` 로 설정해야 함

---

### 😅 실수 3 — `CommandLineRunner` 클래스에 `@Component` 누락함

```java
public class OrderRunner implements CommandLineRunner { // ❌ 실행 안 됨
```

✅ 정답:

```java
@Component
public class OrderRunner implements CommandLineRunner { // ✅ 자동 실행됨
```

📌 **설명**:

- `CommandLineRunner`는 **Spring이 관리하는 Bean으로 등록된 경우에만** 실행됨
- `@Component` 없이 선언하면 스프링이 인식하지 못해 `run()`이 호출되지 않음

---

### 😅 실수 4 — `@GeneratedValue`에 `strategy` 생략 시 ID 자동 생성 실패

```java
@GeneratedValue // ❌ 전략 미지정
```

✅ 정답:

```java
@GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ MySQL용 ID 전략 명시
```

📌 **설명**:

- MySQL에서는 `IDENTITY` 전략을 사용해야 `AUTO_INCREMENT`처럼 동작함
- `strategy`를 생략하면 DB마다 다르게 동작할 수 있어 반드시 명시해야 안정적

---

### 😅 실수 5 — interface끼리 상속할 때 `implements` 사용함

```java
public interface OrderRepository implements JpaRepository<Order, Long> { // ❌ 문법 오류
```

✅ 정답:

```java
public interface OrderRepository extends JpaRepository<Order, Long> { // ✅ 인터페이스끼리는 extends
```

📌 **설명**:

- 자바 문법상, **인터페이스 → 인터페이스는 반드시 `extends`** 를 사용해야 함
- `implements`는 클래스가 인터페이스를 구현할 때만 사용 가능함

---

### 📌 요약 포인트

- `@Entity`는 Spring Bean이 아니기 때문에 반드시 `new`로 직접 생성해야 함
- `JpaRepository<T, ID>`에서 ID 타입은 `@Id` 필드 타입과 정확히 일치시켜야 함
- `CommandLineRunner`는 `@Component`로 Bean 등록해야 자동 실행됨
- `@GeneratedValue`는 `strategy`를 명시하지 않으면 DB마다 동작이 다를 수 있음
- 인터페이스끼리 상속할 때는 `implements`가 아닌 `extends`를 사용해야 함