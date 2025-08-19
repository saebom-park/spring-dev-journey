# [DBA-3단계] 추가 설명 - JPA 엔티티와 Repository 관련 예시 코드 분석

## 🧾 예시 코드 전체 구조

이번 실습에는 총 4개 주요 컴포넌트가 있어:

1. `JpaExampleApplication.java` – **메인 실행 클래스 (앱 진입점)**
2. `Product.java` – **JPA 매핑을 위한 엔티티 클래스**
3. `ProductRepository.java` – **CRUD를 자동으로 제공하는 리포지토리 인터페이스**
4. `InitDataRunner.java` – **앱 시작 시 테스트 데이터를 넣고 출력하는 실행 클래스**

---

## 🔍 JpaExampleApplication.java — **애플리케이션 진입점**

```java
@SpringBootApplication
public class JpaExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }
}

```

- `@SpringBootApplication`: 설정 + 컴포넌트 스캔 + 자동 설정 기능 포함
- `main()` → Spring Boot 앱 실행, 서버 켜짐 + 컴포넌트 등록됨

---

## 🔍 Product.java — **DB 테이블과 매핑되는 Entity 클래스**

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    ...
}

```

- `@Entity`: 이 클래스가 DB 테이블과 연결됨
- `@Id`: 기본 키
- `@GeneratedValue`: AUTO_INCREMENT 역할
- 나머지 필드는 컬럼으로 자동 매핑됨
- getter/setter는 JPA가 내부적으로 접근하는 데 필요

---

## 🔍 ProductRepository.java — **자동 CRUD 처리 Repository**

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
}

```

- `JpaRepository<Entity, ID>`를 상속하면
- `save()`, `findAll()`, `findById()` 같은 CRUD 메서드를 자동으로 제공함
- SQL 한 줄도 안 써도 됨!

---

## 🔍 InitDataRunner.java — **데이터 넣고 조회하는 실행 클래스**

```java
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
            System.out.println(prod.getId() + ": " + prod.getName()));
    }
}

```

- `CommandLineRunner`: 앱 시작 후 자동 실행됨
- `@Component`라서 스프링이 자동 등록
- `ProductRepository`를 주입받아 `save()` + `findAll()` 수행

---

## 🔁 전체 흐름 요약

```
1. JpaExampleApplication 실행 → Spring Boot 부팅
2. @Entity(Product)가 테이블로 매핑됨
3. application.yml의 설정을 기반으로 DB 연결
4. ProductRepository 자동 등록 → CRUD 가능
5. InitDataRunner 실행 → save()로 데이터 추가, findAll()로 출력

```

---

## 🌱 봄이 맞춤 포인트

| 개념 | 설명 |
| --- | --- |
| @Entity | 클래스를 DB 테이블로 연결하는 핵심 어노테이션 |
| JpaRepository | 기본 CRUD 메서드를 자동으로 제공 |
| CommandLineRunner | 앱 실행 직후 로직 실행 시 사용 |
| ddl-auto: update | 엔티티 구조 → 테이블 자동 생성/업데이트 |
| 실행 흐름 | "앱 실행 → DB 연결 → Entity 매핑 → 테스트 실행" 이 순서대로 이해 |