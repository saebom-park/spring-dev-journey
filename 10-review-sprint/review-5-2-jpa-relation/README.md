# [REVIEW-5-2] 연관관계 매핑 (jpa-relation)

> 💬 이번 실습은 실무에서 자주 쓰이는 JPA 연관관계 매핑 구조를 직접 구현하며 복습하는 과정입니다.
>
> 단방향과 양방향 연관관계를 조합하여 실무형 도메인 설계 흐름을 익히고, 컬렉션 필드, 연관관계 주인, DTO 변환 등 핵심 매핑 기술을 손코딩으로 다시 체화합니다.

---

## 💡 시나리오

쇼핑몰 시스템에서 주문(Order) 기능을 설계하려고 합니다.

회원(Member)이 여러 주문을 생성할 수 있고,\
주문은 하나 이상의 상품(Product)으로 구성된 여러 주문 항목(OrderItem)을 포함합니다.

관리자는 다음 기능을 통해 전체 주문 내역을 관리합니다:

- 회원이 주문을 생성할 수 있음
- 주문에 여러 상품을 포함할 수 있음
- 주문 목록 조회 시, 연관된 회원 및 상품 정보도 함께 확인 가능해야 함

---

## 📋 요구사항

- Spring Boot + JPA 프로젝트 구성
- 다음 도메인을 설계 및 매핑할 것:
  - Member (회원)
  - Product (상품)
  - Order (주문)
  - OrderItem (주문 상세)
- 연관관계 매핑 규칙:
  - Order → Member: 단방향 @ManyToOne
  - Order → OrderItem: 양방향 @OneToMany(mappedBy = "order")
  - OrderItem → Product: 단방향 @ManyToOne
- 요청/응답에 사용할 DTO 구성
- 주문 생성/조회 API 구현

---

## 🎯 체크리스트

- ✅ Spring Boot + JPA 환경 설정
- ✅ Member, Product, Order, OrderItem 엔티티 설계
- ✅ 연관관계 매핑: 단방향/양방향 혼합 구성
- ✅ DTO 클래스 설계: 등록/조회용
- ✅ Repository 인터페이스 구성 (JpaRepository 상속)
- ✅ Service 클래스 구현: 주문 생성/조회 흐름 처리
- ✅ Controller 구현: 주문 등록/조회 API 매핑
- ✅ 주문 생성 시 연관된 회원/상품 연동 테스트
- ✅ 주문 목록 조회 시 연관 정보 포함 응답 확인

---

## 📂 폴더 구조

```
review-5-2-jpa-relation/
├── controller/
│   └── OrderController.java
├── service/
│   ├── OrderService.java
│   └── OrderServiceImpl.java
├── repository/
│   ├── MemberRepository.java
│   ├── ProductRepository.java
│   ├── OrderRepository.java
│   └── OrderItemRepository.java
├── dto/
│   ├── OrderRequestDto.java
│   └── OrderResponseDto.java
├── domain/
│   ├── Member.java
│   ├── Product.java
│   ├── Order.java
│   └── OrderItem.java
├── resources/
│   └── application.yml
├── build.gradle
├── JpaRelationApplication.java
└── README.md
```

---

> 이번 실습은 JPA 연관관계 매핑 실전 구조의 출발점이자,\
> 실무에서도 거의 그대로 사용하는 설계를 직접 손으로 구현해보는 실습입니다.
>
> 양방향 매핑의 주의사항, DTO 변환 처리, 컬렉션 연관관계까지\
> 꼭 직접 손으로 타이핑하며 구조 복원력을 키워보세요! 🌱

