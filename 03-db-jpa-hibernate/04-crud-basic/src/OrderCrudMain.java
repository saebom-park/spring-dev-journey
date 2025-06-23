// * 실습 미션 : orders 테이블을 생성하고, CRUD 전체 흐름 구현
// 1. `orders` 테이블: `id`, `member_id`, `book_id`, `status` 컬럼으로 생성
// 2. 데이터 2건 삽입: 서로 다른 주문자(member_id)와 도서(book_id)
// 3. 특정 주문의 상태를 `'CANCELLED'`로 변경
// 4. 하나의 주문을 삭제
// 5. 최종적으로 전체 조회하여 변경 사항 반영 여부 확인
// 6. `System.out.println()`으로 각 단계 결과 출력 필수
import java.sql.*;

public class OrderCrudMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";
        
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ) {
            // CREATE
            stmt.execute("CREATE TABLE orders (id INT, member_id INT, book_id INT, status VARCHAR(30));");
            
            // INSERT
            int insertCount = stmt.executeUpdate("INSERT INTO orders VALUES (1001, 1, 101, 'DELIVERED');");
            insertCount += stmt.executeUpdate("INSERT INTO orders VALUES (1002, 2, 103, 'PAID')");
            System.out.println("삽입된 행 수: " + insertCount);

            // READ
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders;");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getInt("member_id") + ", " + rs.getInt("book_id") + ", " + rs.getString("status"));
            }

            // UPDATE
            int updateCount = stmt.executeUpdate("UPDATE orders SET status = 'CANCELLED' WHERE id = 1002;");
            System.out.println("수정된 행 수: " + updateCount);

            // DELETE
            int deleteCount = stmt.executeUpdate("DELETE FROM orders WHERE id = 1001;");
            System.out.println("삭제된 행 수: " + deleteCount);

            // READ
            rs = stmt.executeQuery("SELECT * FROM orders;");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getInt("member_id") + ", " + rs.getInt("book_id") + ", " + rs.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}