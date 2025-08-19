import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class BookDao {
    Connection conn;

    public BookDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Book book) throws SQLException {
        String sql = "INSERT INTO books VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                list.add(book);
            }
        } catch (SQLException e) {
                e.printStackTrace();

        }
        return list;
    }
}