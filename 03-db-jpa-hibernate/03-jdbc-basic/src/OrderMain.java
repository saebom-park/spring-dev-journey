import java.sql.*;

public class OrderMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()
                ) {
            stmt.execute("CREATE TABLE orders (id INT, member_id INT, book_id INT, borrow_date DATETIME);");
            //stmt.execute("INSERT INTO orders VALUES (10001, 1, 101, NOW());");
            // NOW() 대신 CURRENT_TIMESTAMP 사용해도 무방, 실무 DB에서 호환성이 더 우수함.
            stmt.execute("INSERT INTO orders VALUES (10001, 1, 101, CURRENT_TIMESTAMP);");

            ResultSet rs = stmt.executeQuery("SELECT * FROM orders;");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getInt("member_id") + ", " + rs.getInt("book_id") + ", " + rs.getString("borrow_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}