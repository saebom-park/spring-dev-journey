package practice;

import java.sql.*;
import java.util.List;

public class OrderDaoMain {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute("CREATE TABLE orders (id INT, memberId INT, bookId INT, status VARCHAR(100))");
            OrderDao dao = new OrderDao(conn);

            // Insert
            dao.insert(new Order(1, 1, 1, OrderStatus.DELIVERED));
            dao.insert(new Order(2, 1, 2, OrderStatus.PAID));
            dao.insert(new Order(3, 2, 1, OrderStatus.SHIPPED));
            dao.insert(new Order(4, 2, 3, OrderStatus.PENDING));
            dao.insert(new Order(5, 3, 1, OrderStatus.CANCELLED));
            dao.insert(new Order(6, 3, 2, OrderStatus.RETURNED));

            // Select
            System.out.println("[주문 내역 전체 조회]");
            List<Order> orders = dao.selectAll();
            for (Order order : orders) {
                System.out.println(
                        order.getId() + ", " +
                        order.getMemberId() + ", " +
                        order.getBookId() + ", " +
                        order.getStatus()
                );
            }

            // Update
            dao.update(new Order(1, 1, 1, OrderStatus.RETURNED));
            dao.update(new Order(2, 1, 2, OrderStatus.DELIVERED));

            // Delete
            dao.delete(6);

            // Select
            System.out.println("[최종 주문 내역 조회]");
            orders = dao.selectAll();
            for (Order order : orders) {
                System.out.println(
                        order.getId() + ", " +
                        order.getMemberId() + ", " +
                        order.getBookId() + ", " +
                        order.getStatus()
                );
            }

            // Drop
            dao.drop();
            System.out.println("[테이블 삭제 완료]");
            try {
                orders = dao.selectAll();
                for (Order order : orders) {
                    System.out.println(
                            order.getId() + ", " +
                                    order.getMemberId() + ", " +
                                    order.getBookId() + ", " +
                                    order.getStatus()
                    );
                }
            } catch(SQLException e) {
                System.out.println("테이블이 존재하지 않습니다.");
            }
        }
    }
}