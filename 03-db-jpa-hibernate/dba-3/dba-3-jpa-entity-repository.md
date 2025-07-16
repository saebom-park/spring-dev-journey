# [DBA-3단계] JPA 엔티티와 Repository (jpa-entity-repository)

> ✨ “Spring Boot + JPA로 DB와 객체를 자동으로 매핑하려면 어떻게 해야 할까?”
> 
> 
> 👉 `@Entity`, `@Repository`, `JpaRepository`만으로도 기본 CRUD를 구현할 수 있어!
> 
> DB 연결은 `application.yml`, 의존성은 `build.gradle`로 설정해줘야 해.
> 

---

### 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| JPA | Java Persistence API. 자바 객체 ↔ DB 테이블 매핑는 표준 ORM |
| Entity | DB 테이블과 매핑되는 클래스. `@Entity`, `@Id`, `@GeneratedValue` 사용 |
| Repository | JPA에서 DB 접근 담당. `JpaRepository<T, ID>` 상속으로 CRUD 제공 |
| Spring Data JPA | Repository 인터페이스만 정의해도 내부 구현 자동 생성 |
| 실행 흐름 | `save()`, `findAll()`, `findById()` 등은 모두 기본 제공됨 |

---

### 🧾 예시 코드 (실행 가능 기준)

📁 예시 구조 기준: `com.springlab15`

📁 예시 도메인: `Product`

### 1. build.gradle

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.5'
}

group = 'com.springlab15'
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
    show-sql: true
```

| 항목 | 의미 | 기억 포인트 |
| --- | --- | --- |
| `spring.datasource.url` | DB 주소 | `jdbc:mysql://localhost:3306/testdb` |
| `spring.datasource.username` | DB 아이디 | 보통 `root` |
| `spring.datasource.password` | DB 비번 | 네 설정에 따라 다름 |
| `spring.jpa.hibernate.ddl-auto` | 테이블 생성 전략 | `update`, `create`, `none` 중 선택 |
| `spring.jpa.show-sql` | SQL 보이게 하기 | `true`면 콘솔에 SQL 출력됨 |
| `spring.jpa.properties.hibernate.format_sql` | SQL 예쁘게 보기 | 이건 옵션이야 |

---

### 3. JpaExampleApplication.java
(📁 `src/main/java/com/springlab15/JpaExampleApplication.java`)

```java
package com.springlab15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }
}
```

---

### 4. Product.java (📁 `src/main/java/com/springlab15/Product.java`)

```java
package com.springlab15;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
```

---

### 5. ProductRepository.java
(📁 `src/main/java/com/springlab15/ProductRepository.java`)

```java
package com.springlab15;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

---

### 6. InitDataRunner.java
(📁 `src/main/java/com/springlab15/InitDataRunner.java`)

```java
package com.springlab15;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataRunner implements CommandLineRunner {

    private final ProductRepository productRepository;

    public InitDataRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Product p = new Product();
        p.setName("테스트 상품");
        p.setPrice(10000);
        productRepository.save(p);

        productRepository.findAll().forEach(prod ->
            System.out.println(prod.getId() + ": " + prod.getName() + " / " + prod.getPrice()));
    }
}
```

---

### 📌 포인트 요약

- `@Entity`는 클래스가 DB 테이블임을 의미함
- `@Id` + `@GeneratedValue`로 기본 키 자동 생성 가능
- `JpaRepository<Entity, ID>`만 상속해도 기본 메서드 제공됨
- 실행 클래스 없이 `CommandLineRunner`로 데이터 초기화 가능
- `application.yml`에 DB URL, 사용자, 패스워드 꼭 입력해야 함

---

### 🧪 실습 미션

🎯 목표: `Order` 도메인을 직접 정의하고, CRUD를 완성해보기

1. `Order.java` (id, itemName, price)
2. `OrderRepository.java` 생성
3. `OrderRunner.java`에서 save, findAll 테스트
4. 실행하면 DB에 INSERT 되고 결과 콘솔 출력되도록 만들기

> 참고: DB 테이블은 자동 생성됨 (ddl-auto: update 옵션 덕분)
>