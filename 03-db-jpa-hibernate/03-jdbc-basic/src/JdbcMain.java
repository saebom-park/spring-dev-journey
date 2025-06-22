import java.sql.*;

public class JdbcMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100));");
            stmt.execute("INSERT INTO students VALUES (1, '봄이', '통계학')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("major"));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}