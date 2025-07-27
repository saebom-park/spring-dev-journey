package com.review23;

public class Main {
}

/* 🔍 피드백 요약

1. insertMultiple(List<Order>) 호출 전, orders가 null 또는 비어있는지 유효성 검사를 먼저 수행하면 예외 방지와 가독성이 향상돼.
   → 실습에서는 트랜잭션 시작 전에 검사하는 위치가 적절해!

2. insertMultiple() 예외 처리 시 rollback과 e.printStackTrace() 이후 throw를 다시 해줘야 호출부에서도 적절히 처리 가능해.
   → catch 블록에서 `throw rollbackEx`, `throw e` 구조 적용!

3. findById()는 조회 실패 시 null 반환보다 Optional<Order>를 사용하는 것이 훨씬 안전하고 표현력도 높아.
   → 호출부에서는 ifPresentOrElse() 등으로 명확하게 분기 처리 가능해!

4. findById() 내부에서 `new Order()`로 빈 객체를 미리 생성할 필요는 없어.
   → `rs.next()`가 true일 때만 Order 객체를 생성하는 구조로 리팩토링하면 메모리도 절약되고 의미도 분명해져!
*/