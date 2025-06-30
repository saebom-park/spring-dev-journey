import java.sql.*;

public class BookOrderTransactionWithResources {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE stocks (id INT AUTO_INCREMENT PRIMARY KEY, bookId INT, cnt INT)");
            stmt.execute("CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, memberId INT, bookId INT, status VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO stocks (bookId, cnt) VALUES (101, 5)");

            conn.setAutoCommit(false);  // 트랜잭션 시작
            
            String sql1 = "UPDATE stocks SET cnt = cnt - 1 WHERE bookId = ?";
            String sql2 = "INSERT INTO orders (memberId, bookId, status) VALUES (?, ?, ?)";
            try (
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ) {
                // 1. 재고 감소
                pstmt1.setInt(1, 101);
                pstmt1.executeUpdate();
                
                // 2. 주문 내역 입력
                pstmt2.setInt(1, 1);
                pstmt2.setInt(2, 101);
                pstmt2.setString(3, OrderStatus.PAID.name());
                pstmt2.executeUpdate();

                conn.commit();
                System.out.println("주문 처리 완료!");
                try (ResultSet rs = pstmt2.getGeneratedKeys()) {
                    while (rs.next()) {
                        System.out.println("생성된 주문키: " + rs.getInt(1));
                    }
                }
            } catch (SQLException rollbackEx) {
                conn.rollback();
                System.out.println("주문 실패! 롤백 수행됨.");
                rollbackEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}