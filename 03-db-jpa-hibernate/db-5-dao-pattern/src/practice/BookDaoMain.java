package practice;

import java.sql.*;
import java.util.List;

public class BookDaoMain {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE books (id INT, title VARCHAR(100), author VARCHAR(100))");

        BookDao dao = new BookDao(conn);
        dao.insert(new Book(1, "Spring Dev Journey", "Saebom"));
        dao.insert(new Book(2, "Spring Trading Lab", "GodBom"));

        List<Book> books = dao.findAll();
        for(Book b : books) {
            System.out.println(
                    b.getId() + ", " +
                    b.getTitle() + ", " +
                    b.getAuthor()
            );
        }
    }
}