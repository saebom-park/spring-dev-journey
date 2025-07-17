# [DBA-4-1단계] 단방향 연관관계 매핑 (relation-mapping-unidirectional)

> ✨ “JPA에서 여러 상품(Product)을 하나의 카테고리(Category)에 연결하려면 어떻게 해야 할까?”
> 
> 
> 👉 `@ManyToOne`, `@OneToMany`, `@JoinColumn`을 사용해
> 
> 객체 간의 관계를 DB 외래키(FK)로 자동 매핑할 수 있어!
> 

---

### 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| 연관관계 매핑 | 테이블 간 외래 키(FK) 관계를 엔티티 필드로 표현하는 것 |
| @ManyToOne | 다대일(N:1) 관계 설정. 연관 대상 엔티티를 필드로 선언 |
| @JoinColumn | 외래 키(FK) 컬럼명 지정. 생략 시 기본 네이밍 전략 사용 |
| fetch 옵션 | 연관 엔티티 로딩 전략. 기본값은 `EAGER`, 실무에선 `LAZY` 권장 |
| 단방향 vs 양방향 | 한쪽만 참조하는 관계 vs 양쪽 모두 참조하는 관계 |

---

### 🧾 예시 코드 (실행 가능 기준)

📁 예시 구조 기준: `com.springlab16`

📁 예시 도메인: `Order` → `Member`

### 1. build.gradle

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.5'
}

group = 'com.springlab16'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'com.mysql:mysql-connector-j:8.0.33'
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

```

---

### 2. application.yml (📁 `src/main/resources/application.yml`)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: spring1234
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        enable_lazy_load_no_trans: true # 트랜잭션 없이도 LAZY 로딩 허용 (예시 코드 실행 편의를 위해 설정)
    show-sql: true

logging:
  level:
    org.hibernate.SQL: debug
    org.hibernate.type: trace

```

---

### 3. JpaRelationExampleApplication.java

(📁 `src/main/java/com/springlab16/JpaRelationExampleApplication.java`)

```java
package com.springlab16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaRelationExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaRelationExampleApplication.class, args);
    }
}

```

---

### 4. Member.java

(📁 `src/main/java/com/springlab16/Member.java`)

```java
package com.springlab16;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    public Member() {}

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

### 5. Order.java

(📁 `src/main/java/com/springlab16/Order.java`)

```java
package com.springlab16;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Order() {}

    public Order(String itemName, Member member) {
        this.itemName = itemName;
        this.member = member;
    }

    public String getItemName() {
        return itemName;
    }

    public Member getMember() {
        return member;
    }
}
```

---

### 6. MemberRepository.java

(📁 `src/main/java/com/springlab16/MemberRepository.java`)

```java
package com.springlab16;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

```

---

### 7. OrderRepository.java

(📁 `src/main/java/com/springlab16/OrderRepository.java`)

```java
package com.springlab16;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

```

---

### 8. InitRelationDataRunner.java

(📁 `src/main/java/com/springlab16/InitRelationDataRunner.java`)

```java
package com.springlab16;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitRelationDataRunner implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public InitRelationDataRunner(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {
        Member m = new Member("홍길동");
        memberRepository.save(m);

        Order o = new Order("책 주문", m);
        orderRepository.save(o);

        orderRepository.findAll().forEach(ord -> {
            System.out.println(ord.getItemName() + " / " + ord.getMember().getName());
        });
    }
}

```

---

### 📌 포인트 요약

- `@ManyToOne`은 연관 필드에 선언하며, **반드시 `@JoinColumn`과 함께 사용**
- 외래 키 이름은 `@JoinColumn(name = ...)`으로 명시적으로 지정하는 것이 유지보수에 유리
- 연관 엔티티는 기본 `EAGER` 로딩 → 실무에선 성능 최적화를 위해 `LAZY`로 설정 권장
- `@ManyToOne`은 항상 N:1 구조에서 N 쪽에 설정됨
- 이 예시는 **단방향 매핑**으로 Member는 Order를 알지 못함
- Member도 DB에 먼저 저장해야 하므로 `MemberRepository.save()` 필요함

---

### 🧪 실습 미션

🎯 목표: `Product` → `Category` 연관관계를 매핑하고 저장/조회 흐름을 테스트해보기

1. `Category.java` (id, name 필드)
2. `Product.java` (id, name, price, category 필드)
    - category는 `@ManyToOne(fetch = FetchType.LAZY)`
    - 외래 키는 `category_id`로 지정
3. `ProductRepository`, `CategoryRepository` 생성
4. `InitProductDataRunner` 작성하여 저장/조회 테스트
5. `application.yml`에 `enable_lazy_load_no_trans` 반드시 추가