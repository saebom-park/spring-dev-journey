package practice;

import java.sql.*;

public class JdbcInsert {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100))");

            int result = stmt.executeUpdate("INSERT INTO books VALUES (1, '자바의 정석', '남궁성')");
            System.out.println("INSERT 실행 결과: " + result + " 건 삽입됨");

            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            System.out.println("\n[현재 책 목록]");
            while (rs.next()) {
                System.out.println(
                   rs.getInt("id") + ", " +
                   rs.getString("title") + ", " +
                   rs.getString("author")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}