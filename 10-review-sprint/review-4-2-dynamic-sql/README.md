# [REVIEW-4-2] 동적 SQL 처리 (dynamic-sql)

> 💬 다양한 조건을 유동적으로 처리하려면 SQL도 유연해야 해. 이번 실습에선 MyBatis의 `<if>`, `<where>`, `<choose>` 같은 태그를 이용해 여러 검색 조건을 조합해보는 동적 SQL 실습을 진행합니다. 실무에서 자주 쓰이는 **필터 검색 API**를 Spring Boot + MyBatis + 3계층 구조로 구현할 거야.

---

## 💡 시나리오

운영 중인 쇼핑몰 관리자 페이지에서 다음과 같은 **상품 필터 검색 기능**이 필요해졌습니다:

- 상품명 키워드로 검색
- 특정 카테고리만 조회
- 가격 범위 지정
- 정렬 기준(상품명, 가격, 오름/내림차순)

각 조건은 **선택적**으로 적용되며, 조합에 따라 SQL 쿼리가 유동적으로 생성되어야 합니다.

---

## 📋 요구사항

- Spring Boot 프로젝트 기반으로 구성할 것
- `Product`, `Category` 도메인 클래스를 설계할 것
- `ProductFilterDto`를 생성하여 필터 조건을 전달할 것
- `ProductResponseDto`를 생성하여 응답 구조 정의할 것
- `ProductMapper` 인터페이스 + `ProductMapper.xml` 작성할 것
- Mapper XML에 `<if>`, `<where>`, `<choose>` 태그를 활용한 동적 SQL 구성할 것
- 정렬 기준은 `${}` 또는 `<choose>`로 처리할 것
- 조건이 없을 경우 전체 상품을 조회하도록 구성할 것
- Service 계층과 Controller 계층을 구현하여 `/products/filter` API를 완성할 것
- JSON 형태로 응답을 반환하며, 조합 조건 테스트를 모두 통과할 것

---

## 🎯 체크리스트

- ✅ Spring Boot 프로젝트 환경 구성
- ✅ Product, Category 도메인 클래스 설계
- ✅ ProductFilterDto / ProductResponseDto 생성
- ✅ ProductMapper 인터페이스 선언
- ✅ ProductMapper.xml 작성 (동적 SQL 포함)
- ✅ 조건 분기 `<if>`, `<where>`, 정렬 처리 `${}` 또는 `<choose>` 적용
- ✅ ProductService / ProductController 구현
- ✅ `/products/filter` API 테스트
- ✅ JSON 형태로 검색 결과 반환
- ✅ 조건 조합별 필터링 정상 동작 확인

---

## 📂 폴더 구조

```
review-4-2/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── review42/
│       │           ├── controller/
│       │           │   └── ProductController.java
│       │           ├── service/
│       │           │   ├── ProductService.java
│       │           │   └── ProductServiceImpl.java
│       │           ├── repository/
│       │           │   └── ProductMapper.java
│       │           ├── dto/
│       │           │   ├── ProductFilterDto.java
│       │           │   └── ProductResponseDto.java
│       │           ├── domain/
│       │           │   ├── Product.java
│       │           │   └── Category.java
│       │           └── ProductSortApiApplication.java
│       └── resources/
│           ├── application.yml
│           └── mapper/
│               └── ProductMapper.xml
├── build.gradle
├── settings.gradle
├── README.md
├── review-4-2-questions.md
├── review-4-2-mistakes.md
```

---

> 이번 실습을 통해 다양한 조건 조합을 동적으로 처리하는 SQL 작성법을 익히게 됩니다.\
> 다음 실습인 REVIEW-4-3에서는 연관관계를 포함한 **중첩 resultMap 매핑**을 배워볼 거예요! 🌱

