# REVIEW-2-3: DAO 통합 & try-with-resources 적용 (dao-integration)

> ✨ JDBC 기반 CRUD 흐름을 try-with-resources 방식으로 개선하고,
> 
> 
> 중복 구조를 DAO 중심으로 통합하여 실전 개발에 가까운 구조로 리팩토링합니다.
> 

---

## 🎯 목표

- try-with-resources를 통해 자원 정리를 자동화하고 예외 안전성 향상
- 반복되는 Connection/PreparedStatement/ResultSet 흐름 간소화
- DAO 구조를 실무 스타일로 개선하여 유지보수성과 확장성 확보

---

## 💡 실습 시나리오

📦 **주문 관리 DAO 리팩토링 (OrderDaoRefactor)**

- 기존 Book 기반 실습에서 벗어나 주문(Order)을 주제로 실습 진행
- `OrderDao`의 CRUD 메서드를 `try-with-resources` 기반으로 리팩토링
- 코드 중복 제거 (Connection, PreparedStatement 반복 사용 개선)
- `findById()`는 Optional 반환, `update()`는 int 반환 등 실전 스타일 적용

---

## 📋 구현 요구사항

### 1. `Order` 클래스 설계

- 필드: `id`, `productName`, `quantity`, `price`
- 생성자, getter/setter, toString 포함

### 2. `OrderDaoRefactor` 클래스

- 필드: `Connection conn`
- 메서드 요구사항:

| 메서드 | 설명 |
| --- | --- |
| `void insert(Order order)` | 단건 등록 (try-with-resources 사용) |
| `List<Order> findAll()` | 전체 조회 (ResultSet 자동 정리) |
| `Optional<Order> findById(int id)` | ID로 단건 조회, Optional로 감싸서 반환 |
| `int update(int id, int type, String value)` | 수정된 행 개수 반환 |
| `int delete(int id)` | 삭제된 행 개수 반환 |

> 💡 모든 메서드는 try-with-resources로 자원 자동 정리 처리할 것
> 

### 3. `Main.java`

- 주문 데이터 직접 생성 후 CRUD 흐름 수행
- `Optional<Order>` 처리 시 `.ifPresentOrElse()` 등 활용 권장

---

## 🎯 구현 목표 체크리스트

| 항목 | 포함 여부 |
| --- | --- |
| try-with-resources 적용 | ✅ |
| 중복 코드 제거 | ✅ |
| Optional로 안전한 조회 처리 | ✅ |
| 수정/삭제 시 int 반환 | ✅ |
| 자원 정리 누락 없이 처리됨 | ✅ |

---

## 📂 폴더 구조 예시

```
review-2-3-dao-integration/
├── README.md
└── src/main/java
    └── com.review23/
        ├── Order.java
        ├── OrderDaoRefactor.java
        └── Main.java

```