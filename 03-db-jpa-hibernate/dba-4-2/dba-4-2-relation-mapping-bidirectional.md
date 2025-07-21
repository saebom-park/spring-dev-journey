# [DBA-4-2단계] 양방향 연관관계 매핑 (relation-mapping-bidirectional)

> ✨ “JPA에서 한 명의 회원이 여러 주문을 가졌다는 구조를 어떻게 객체로 표현할까?”
> 
> 
> 👉 `@OneToMany(mappedBy)`와 `@ManyToOne`을 함께 사용해
> 
> 컬렉션 기반의 양방향 연관관계를 매핑할 수 있어!
> 

---

### 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| 양방향 매핑 | 서로 참조하는 두 엔티티 간 관계. 한쪽은 컬렉션, 한쪽은 단건 필드로 연결됨 |
| 주인/비주인 | `@ManyToOne`이 주인, `@OneToMany(mappedBy)`는 연관관계 주인이 아님 |
| 편의 메서드 | 양쪽을 모두 설정해야 일관성 유지 → `addOrder()` 같은 메서드 필요 |
| mappedBy | 외래 키를 관리하는 주인을 지정하는 속성. 읽기 전용 필드임 |
| 실무 고려사항 | 실무에선 순환참조 주의 (`toString`, JSON 등), 성능 이슈도 함께 고려해야 함 |

---

### 🧾 예시 코드 (실행 가능 기준)

📁 예시 구조 기준: `com.springlab17`

📁 예시 도메인: `Order` ↔ `Member`

### 1. build.gradle

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.5'
}

group = 'com.springlab17'
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
        enable_lazy_load_no_trans: true
    show-sql: false

logging:
  level:
    org.hibernate.SQL: debug
    org.hibernate.type: trace
```

---

### 3. JpaRelationBidirectionalApplication.java

(📁 `src/main/java/com/springlab17/JpaRelationBidirectionalApplication.java`)

```java
package com.springlab17;

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

(📁 `src/main/java/com/springlab17/Member.java`)

```java
package com.springlab17;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    //@OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST) → 실무에서는 신중하게!
    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    // Constructor
    public Member() {}
    public Member(String name){ this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }

    // 연관관계 편의 메서드 (객체 양쪽 필드를 항상 일관되게 동기화 하기 위함)
    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }
}
```

---

### 5. Order.java

(📁 `src/main/java/com/springlab17/Order.java`)

```java
package com.springlab17;

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

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Constructor
    public Order() {}
    public Order(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    // getter
    public Long getId() { return id; }
    public String getItemName() { return itemName; }
    public int getPrice() { return price; }
    public Member getMember() { return member;}

    // setter
    public void setMember(Member member) { this.member = member; }

}
```

---

### 6. MemberRepository.java

(📁 `src/main/java/com/springlab17/MemberRepository.java`)

```java
package com.springlab17;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
```

---

### 7. OrderRepository.java

(📁 `src/main/java/com/springlab17/OrderRepository.java`)

```java
package com.springlab17;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
```

---

### 8. InitRelationBidirectionalDataRunner.java

(📁 `src/main/java/com/springlab17/InitRelationBidirectionalDataRunner.java`)

```java
package com.springlab17;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitRelationDataRunner implements CommandLineRunner {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public InitRelationDataRunner(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void run(String... args){
        Member m1 = new Member("온이");
        Order o1 = new Order("테스트상품3", 1000);
        Order o2 = new Order("테스트상품4", 1500);
        m1.addOrder(o1);
        m1.addOrder(o2);
        memberRepository.save(m1);
        orderRepository.save(o1);
        orderRepository.save(o2);

        memberRepository.findAll().forEach(mem -> {
            System.out.println(mem.getName() + "님의 주문 목록: ");
                    mem.getOrders().forEach(ord ->
                            System.out.println(ord.getItemName() + " / " + ord.getPrice()));

        });

    }
}
```

---

### 📌 포인트 요약

- `@OneToMany(mappedBy = "member")`는 읽기 전용 컬렉션 필드이며, **주인은 아님**
- 연관관계의 주인은 항상 `@ManyToOne` 쪽에서 설정됨
- `member.getOrders().add(order)`도 수동으로 호출해야 컬렉션 동기화 가능
- 이를 자동화하기 위해 편의 메서드(`addOrder()`)를 엔티티에 직접 구현하는 게 좋음
- `@ToString(exclude = ...)`, `@JsonIgnore` 등 순환참조 방지 전략도 고려해야 함

---

### 🧪 실습 미션 - 1

🎯 목표: `Category` ↔ `Product` 양방향 연관관계를 구성하고, 편의 메서드를 통한 저장 흐름까지 구현해보기

1. `Category.java`
    - `@OneToMany(mappedBy = "category") List<Product> products` 필드 추가
    - `addProduct(Product product)` 편의 메서드 구현
2. `Product.java`
    - `@ManyToOne(fetch = FetchType.LAZY)`
    - `@JoinColumn(name = "category_id")`
3. `InitProductDataRunner` 수정
    - 카테고리 객체 생성 후, `category.addProduct(p)` 형태로 연관관계 설정
4. 저장 시 컬렉션 조회 및 양방향 동작 확인

> 참고: 순환 참조 방지를 위해 @ToString(exclude = ...) 또는 @JsonIgnore 필요할 수 있음
> 

---

### 🧪 실습 미션 - 2

🎯 목표: `Department` ↔ `Employee` 양방향 연관관계를 구성하고, 편의 메서드를 통한 저장 흐름까지 구현해보기

1. `Department.java`
    - `@OneToMany(mappedBy = "department") List<Employee> employees` 필드 추가
    - `addEmployee(Employee employee)` 편의 메서드 구현
2. `Employee.java`
    - `@ManyToOne(fetch = FetchType.LAZY)`
    - `@JoinColumn(name = "department_id")`
3. `InitEmployeeDataRunner` 작성
    - 부서 객체 생성 후, `dept.addEmployee(emp)` 형태로 연관관계 설정
    - `departmentRepository.save(dept)`로 저장
4. 저장 후 전체 부서를 조회하고, 각 부서에 속한 직원 목록을 출력해보기

> 참고: @ToString 또는 @JsonIgnore 설정은 생략 가능 (현재 흐름에선 순환참조 문제 없음)
> 

### 💾 예시 출력 예상

```
개발팀 부서의 직원 목록:
- 홍길동 / 주니어 개발자
- 김영희 / 시니어 개발자

인사팀 부서의 직원 목록:
- 이철수 / 인사 담당자
```