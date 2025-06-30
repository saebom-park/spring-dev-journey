import java.sql.*;

public class BookOrderTransaction {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE stocks (id INT AUTO_INCREMENT PRIMARY KEY, bookId INT, cnt INT)");
            stmt.execute("CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, memberId INT, bookId INT, status VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO stocks (bookId, cnt) VALUES (101, 5)");

            conn.setAutoCommit(false); // 트랜잭션 시작

            // 1. 재고 수량 감소
            String sql1 = "UPDATE stocks SET cnt = cnt - 1 WHERE bookId = ? ";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, 101);
            pstmt1.executeUpdate();

            // 2. 주문 내역 입력
            String sql2 = "INSERT INTO orders (memberId, bookId, status) VALUES (?, ?, ?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, 1);
            pstmt2.setInt(2, 101);
            pstmt2.setString(3, OrderStatus.PENDING.name());
            pstmt2.executeUpdate();

            conn.commit();
            System.out.println("주문 처리 완료!");
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("주문 실패! 롤백 수행 됨.");
                }
            } catch (SQLException rollbackEx) {
                System.out.println("롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }

        }
    }

}