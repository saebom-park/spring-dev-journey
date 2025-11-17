# [REVIEW-5-3] 트랜잭션 & 영속성 컨텍스트 (jpa-tx-context)

> 💬 이번 실습은 JPA의 트랜잭션과 영속성 컨텍스트 개념을 손코딩으로 직접 실험하며 복습하는 과정입니다.
>
> flush, dirty checking, rollback 등 실무에서 반드시 이해하고 있어야 할 JPA 핵심 동작 원리를 직접 실습 시나리오에 녹여 구현합니다.

---

## 💡 시나리오

배송 관리 시스템에서 다음과 같은 시나리오가 발생합니다.

고객이 상품을 주문하면 `배송 요청`이 생성되고, 배송 기사가 상태를 업데이트해 나갑니다.  
또한 중간에 배송 문제가 발생하면 롤백이 수행되어야 하는 상황도 존재합니다.

당신은 아래 기능을 통해 영속성 컨텍스트의 동작을 관찰합니다:

- 배송 생성 → 초기 상태는 `READY`
- 배송 시작 → 상태를 `IN_PROGRESS`로 변경
- 배송 완료 → 상태를 `COMPLETED`로 변경
- 배송 중 예외 → `EXCEPTION` 상태로 변경되며, 트랜잭션이 롤백되어야 함

---

## 📋 요구사항

- Spring Boot + JPA 프로젝트 구성
- 다음 도메인을 설계 및 매핑할 것:
  - Delivery (배송 요청)
- 상태 필드는 Enum(`DeliveryStatus`)으로 관리
- 다음 기능을 서비스 계층에서 트랜잭션 기반으로 처리:
  - 배송 요청 생성
  - 배송 상태 변경
  - flush() 실험
  - rollback 실험
- 상태 변경은 dirty checking 기반으로 자동 반영
- flush 시점 이후 예외 발생 시, rollback 되는지 로그로 확인
- controller에서 API를 통해 각 흐름 실행 가능하게 구현

---

## 🎯 체크리스트

- ✅ Spring Boot + JPA 환경 설정
- ✅ Delivery 엔티티 설계 (Enum 상태 포함)
- ✅ `@Transactional` 기반 서비스 구성
- ✅ flush() 호출 시점 명시
- ✅ flush 이후 예외 발생 → rollback 확인
- ✅ 상태 변경: dirty checking으로 DB 반영
- ✅ controller에서 기능별 endpoint 분리
- ✅ 로그로 flush / rollback 동작 확인

---

## 📂 폴더 구조

```
review-5-3-jpa-tx-context/
├── controller/
│   └── DeliveryController.java
├── service/
│   └── DeliveryService.java
├── repository/
│   └── DeliveryRepository.java
├── dto/
│   ├── DeliveryRequestDto.java
│   └── DeliveryStatusUpdateDto.java
├── domain/
│   └── Delivery.java
├── enums/
│   └── DeliveryStatus.java
├── resources/
│   └── application.yml
├── build.gradle
├── JpaTxContextApplication.java
└── README.md
```

---

> 이번 실습은 JPA 트랜잭션과 영속성 컨텍스트를 코드로 **정확히 관찰하고 체화하는 실습**입니다.
>
> flush 시점, dirty checking, rollback 처리를 꼭 직접 로그로 확인하고,  
> 실무에서 발생할 수 있는 트랜잭션 문제를 예측할 수 있도록 연습하세요. 🌱
