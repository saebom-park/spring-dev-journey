# [REVIEW-4-3] 중첩 resultMap 처리 (resultMap-nested)

> 💬 이번 실습에서는 MyBatis에서 JOIN 결과를 **중첩 구조의 객체로 매핑**하는 방법을 학습합니다. 단순한 컬럼 → 필드 매핑이 아니라, **Product → Category처럼 객체 안에 객체를 담는 연관 구조를 응답으로 구성**할 수 있어야 합니다. 실무에서도 중첩 매핑을 통해 효율적인 응답 구조를 자주 사용합니다.

---

## 💡 시나리오

운영 중인 쇼핑몰 시스템에서 상품 상세 조회 시, 응답에 카테고리 정보까지 함께 포함시켜야 합니다. 클라이언트는 다음과 같은 응답 구조를 원합니다:

```json
{
  "id": 1,
  "name": "연필",
  "price": 1000,
  "category": {
    "id": 10,
    "name": "문구류"
  }
}
```

이를 위해선 `Product`가 `Category`를 내부 객체로 포함해야 하며, Mapper XML에서는 `<association>` 태그를 활용한 **중첩 resultMap** 구성이 필요합니다.

---

## 📋 요구사항

- Spring Boot 프로젝트 기반으로 구성할 것
- `Product`, `Category` 도메인 클래스를 설계할 것 (Product가 Category 포함)
- `ProductResponseDto` 내부에 `CategoryDto`를 포함할 것
- `ProductMapper` 인터페이스 + `ProductMapper.xml` 작성할 것
- `<resultMap>` + `<association>`을 활용해 중첩 구조 매핑할 것
- `/products/nested` GET API를 구현해 JSON 응답을 테스트할 것

---

## 🎯 체크리스트

- ✅ Spring Boot 프로젝트 환경 구성
- ✅ Product, Category 도메인 클래스 설계 (중첩 구조)
- ✅ ProductResponseDto + CategoryDto 구성
- ✅ ProductMapper 인터페이스 및 XML 작성
- ✅ resultMap 중첩 매핑 (`<association>` 적용)
- ✅ ProductService / ProductController 구현
- ✅ `/products/nested` API 테스트 완료

---

## 📂 폴더 구조

```
review-4-3/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── review43/
│       │           ├── controller/
│       │           │   └── ProductController.java
│       │           ├── service/
│       │           │   ├── ProductService.java
│       │           │   └── ProductServiceImpl.java
│       │           ├── repository/
│       │           │   └── ProductMapper.java
│       │           ├── dto/
│       │           │   ├── ProductResponseDto.java
│       │           │   └── CategoryDto.java
│       │           ├── domain/
│       │           │   ├── Product.java
│       │           │   └── Category.java
│       │           └── ProductNestedApiApplication.java
│       └── resources/
│           ├── application.yml
│           └── mapper/
│               └── ProductMapper.xml
├── build.gradle
├── settings.gradle
├── README.md
```

---

> 이번 실습을 통해 **MyBatis의 중첩 매핑 기술**을 익히고, 실무에서 자주 마주치는 **계층형 응답 구조 설계 방식**을 손에 익히게 됩니다. 다음 실습에서는 유사한 구조를 JPA 기반으로 다시 구현해볼 예정이에요! 🌱

