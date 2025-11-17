# [REVIEW-4-1] 상품 검색 API (mybatis-search)

> 💬 지금까지 배운 MyBatis의 기초 개념을 바탕으로 실무에서 자주 사용하는 **검색 기능**을 구현해봅니다. XML Mapper, resultMap, SQL 매핑 기법을 손에 익히는 게 목적이에요. 이번 실습은 Spring Boot + Controller + Service + Mapper + DTO + Mapper XML 흐름을 모두 통합한 **실무형 API 구현** 실습입니다.

---

## 💡 시나리오

운영 중인 쇼핑몰 시스템에서 상품 검색 기능이 필요해졌습니다. 운영자는 키워드로 상품명을 검색하고, 해당 상품의 상세 정보를 조회할 수 있어야 합니다.

- 검색어는 `keyword` 파라미터로 전달됩니다.
- 부분 일치 검색으로 상품명을 필터링합니다.
- 검색 결과는 `상품명`, `가격`, `카테고리명`을 포함해야 합니다.

---

## 📋 요구사항

- Spring Boot 프로젝트 기반으로 구성할 것
- `Product`, `Category` 도메인 클래스를 설계할 것
- `ProductResponseDto`를 생성하여 응답 구조 정의할 것
- `ProductMapper` 인터페이스 + `ProductMapper.xml` 작성할 것
- Mapper XML에 SELECT + JOIN + LIKE 쿼리를 명시할 것
- 결과 매핑은 `resultMap`을 사용하여 복합 필드 매핑 처리할 것
- Service 계층과 Controller 계층을 구현하여 `/products/search` API를 완성할 것
- JSON 형태로 응답을 반환하며, 빈 검색어 처리 예외도 구현할 것

---

## 🎯 체크리스트

- ✅ Spring Boot 프로젝트 환경 구성
- ✅ Product, Category 도메인 클래스 설계
- ✅ ProductResponseDto 생성
- ✅ ProductMapper 인터페이스 선언
- ✅ ProductMapper.xml 작성 (SELECT + JOIN + LIKE 쿼리)
- ✅ resultMap으로 복합 필드 매핑
- ✅ ProductService / ProductController 구현
- ✅ `/products/search?keyword=xxx` API 테스트
- ✅ JSON 형태로 검색 결과 반환
- ✅ 빈 검색어 예외 처리 or 전체 조회 처리

---

## 📂 폴더 구조

```
review-4-1/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── review41/
│       │           ├── controller/
│       │           │   └── ProductController.java
│       │           ├── service/
│       │           │   └── ProductService.java
│       │           ├── repository/
│       │           │   └── ProductMapper.java
│       │           ├── dto/
│       │           │   └── ProductResponseDto.java
│       │           ├── domain/
│       │           │   ├── Product.java
│       │           │   └── Category.java
│       │           └── ProductApiApplication.java
│       └── resources/
│           ├── application.yml
│           └── mapper/
│               └── ProductMapper.xml
├── build.gradle
├── settings.gradle
├── README.md
├── review-4-1-questions.md
├── review-4-1-mistakes.md
```

---

> 지금까지의 흐름은 REVIEW-4-2 실습인 “동적 SQL 처리”로 자연스럽게 이어집니다.
> 보편적인 조건 분기 쿼리를 어디까지 유연하게 구성할지가 핵심입니다! 🌱

