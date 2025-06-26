package practice;

import java.sql.*;

public class JdbcLegacyMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100))");
            stmt.execute("INSERT INTO students VALUES (1, '봄이', '통계학')");
            stmt.execute("INSERT INTO students VALUES (2, '온이', '컴퓨터공학')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ", " +
                        rs.getString("name") + ", " +
                        rs.getString("major")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}