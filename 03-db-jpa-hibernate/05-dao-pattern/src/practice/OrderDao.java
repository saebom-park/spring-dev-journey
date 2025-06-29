package practice;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class OrderDao {
    private Connection conn;

    // constructor
    public OrderDao(Connection conn) {this.conn = conn;}

    // Method: insert
    public void insert(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, order.getId());
            pstmt.setInt(2, order.getMemberId());
            pstmt.setInt(3, order.getBookId());
            pstmt.setString(4, order.getStatus().name());
            pstmt.executeUpdate();
        }
    }

    // Method: Select
    public List<Order> selectAll() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while(rs.next()) {
//                Order order = new Order();
//                order.setId(rs.getInt("id"));
//                order.setMemberId(rs.getInt("memberId"));
//                order.setBookId(rs.getInt("bookId"));
//                order.setStatus(rs.getString("status"));
                Order order = new Order(rs.getInt("id"), rs.getInt("memberId"), rs.getInt("bookId"), OrderStatus.valueOf(rs.getString("status")));
                list.add(order);
            }
            return list;
        }
    }

    // Method: Update
    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET memberId = ?, bookId = ?, status = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getMemberId());
            pstmt.setInt(2, order.getBookId());
            pstmt.setString(3, order.getStatus().name());
            pstmt.setInt(4, order.getId());
            pstmt.executeUpdate();
        }
    }

    // Method: Delete
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Method: Drop
    public void drop() throws SQLException {
        String sql = "DROP TABLE orders";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}