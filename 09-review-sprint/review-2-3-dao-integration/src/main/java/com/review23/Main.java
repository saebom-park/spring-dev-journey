package com.review23;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "spring1234";
        OrderDaoRefactor orderDao;

        Order o1 = new Order("상품1", 3, 9000);
        Order o2 = new Order("상품2", 5, 10000);
        Order o3 = new Order("상품2", 12, 24000);
        Order o4 = new Order("상품3", 2, 10000);

        // insert
        orderDao = getOrderDao(url, username, password);
        orderDao.insert(o1);

        // insert multi
        List<Order> orders = new ArrayList<>();
        orders.add(o2);
        orders.add(o3);
        orders.add(o4);
        orderDao = getOrderDao(url, username, password);
        orderDao.insertMultiple(orders);

        // find all
        orderDao = getOrderDao(url, username, password);
        orders = orderDao.findAll();
        System.out.println("[전체 주문 목록]");
        for (Order order : orders) {
            System.out.println(order);
        }

        // find by id
        Scanner scanner = new Scanner(System.in);
        System.out.print("조회할 주문 ID를 입력해 주세요:");
        Long selectId = scanner.nextLong();
        scanner.nextLine();
        orderDao = getOrderDao(url, username, password);
        // 피드백 7 수정
        Optional<Order> optionalOrder = orderDao.findById(selectId);
        optionalOrder.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("조회 결과가 없습니다.")
        );

        // update
        System.out.print("업데이트 할 주문 ID를 입력해주세요: ");
        Long updateId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("업데이트 할 항목 번호를 입력해주세요(상품명: 1 / 수량: 2 / 가격: 3): ");
        int updateType = scanner.nextInt();
        scanner.nextLine();
        System.out.print("업데이트 할 값을 입력해주세요: ");
        String updateValue = scanner.nextLine();
        orderDao = getOrderDao(url, username, password);
        orderDao.update(updateId, updateType, updateValue);

        // delete
        System.out.print("삭제할 주문 ID를 입력해주세요: ");
        Long deleteId = scanner.nextLong();
        scanner.nextLine();
        orderDao = getOrderDao(url, username, password);
        orderDao.delete(deleteId);

        // find all
        orderDao = getOrderDao(url, username, password);
        orders = orderDao.findAll();
        System.out.println("[최종 주문 목록 조회]");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
    
    // 피드백 8 수정
    private static OrderDaoRefactor getOrderDao(String url, String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        return new OrderDaoRefactor(conn);
    }
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

5. update()에서 가격(price)은 문자열을 int로 변환해야 하므로,
   NumberFormatException을 try-catch로 감싸고 SQLException으로 래핑하면,
   호출부에서 일관된 예외 처리 및 사용자 메시지 출력이 가능해져.

6. switch문으로 SQL column명을 설정할 때는 화이트리스트 방식으로 생성하는 게 가장 안전해.
   특히 외부 입력 기반이라면 Map을 이용한 매핑 방식이 보안과 유지보수에 강해.

7. `Optional<Order>` 처리 시 `if (optional != null)` 같은 방식 대신
   `ifPresentOrElse()`를 쓰면 더 선언적이고 명확하게 분기 가능해!
   → `System.out::println`, `() -> System.out.println(...)` 구조로 깔끔하게 표현돼.

8. `getOrderDao(...)` 메서드로 Connection + DAO 생성을 분리한 점은 매우 좋아!
   → 반복되는 연결 코드 중복 제거 + 실전 프로젝트에서도 자주 쓰이는 패턴이야.

*/