import java.sql.*;

public class BookMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()
                ) {
            stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(50));");
            stmt.execute("INSERT INTO books VALUES (101, '논어', '공자');");

            ResultSet rs = stmt.executeQuery("SELECT * FROM books;");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("title") + ", " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}