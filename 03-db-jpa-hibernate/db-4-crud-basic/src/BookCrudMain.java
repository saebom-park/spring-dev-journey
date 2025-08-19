import java.sql.*;

public class BookCrudMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ) {
            // CREATE
            stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100));");

            // INSERT
            int insertCount = stmt.executeUpdate("INSERT INTO books VALUES (1, '자바의 정석', '남궁성');");
            System.out.println("삽입된 행 수: " + insertCount);

            // READ
            ResultSet rs = stmt.executeQuery("SELECT * FROM books;");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("title") + ", " + rs.getString("author"));
            }

            // UPDATE
            int updateCount = stmt.executeUpdate("UPDATE books SET author = '홍길동' WHERE id = 1;");
            System.out.println("수정된 행 수: " + updateCount);

            // DELETE
            int deleteCount = stmt.executeUpdate("DELETE FROM books WHERE id = 1;");
            System.out.println("삭제된 행 수: " + deleteCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}