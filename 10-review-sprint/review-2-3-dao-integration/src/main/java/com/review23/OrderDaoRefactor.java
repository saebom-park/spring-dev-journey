package com.review23;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoRefactor {
    private Connection conn;

    // constructor
    public OrderDaoRefactor(Connection conn) {
        this.conn = conn;
    }

    // insert
    // 단건 입력은 트랜잭션 불필요
    public void insert(Order order) throws SQLException {
        String sql = "INSERT INTO orders (productName, quantity, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getProductName());
            pstmt.setInt(2, order.getQuantity());
            pstmt.setInt(3, order.getPrice());
            pstmt.executeUpdate();
        }
    }

    // find all
    // 읽기 전용 메서드는 트랜잭션 불필요
    public List<Order> findAll() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setProductName(rs.getString("productName"));
                order.setQuantity(rs.getInt("quantity"));
                order.setPrice(rs.getInt("price"));
                list.add(order);
            }
        }
        return list;
    }

    // find by id
    // 읽기 전용 메서드는 트랜잭션 불필요
    // 피드백 3 수정
    public Optional<Order> findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 피드백 4 수정
                    Order order = new Order();
                    order.setId(rs.getLong("id"));
                    order.setProductName(rs.getString("productName"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setPrice(rs.getInt("price"));
                    // 피드백 3 수정
                    return Optional.of(order);
                }
            }
        }
        // 피드백 3 수정
        return Optional.empty();
    }

    // insert multiple
    public int insertMultiple(List<Order> orders) throws SQLException {
        int insertCnt = 0;
        // 피드백 1 수정
        if (orders == null || orders.isEmpty()) {
            System.out.println("입력된 주문 정보가 없습니다.");
            return 0;
        }

        try {
            String sql = "INSERT INTO orders (productName, quantity, price) VALUES (?, ?, ?)";

            // transaction
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Order order : orders) {
                    pstmt.setString(1, order.getProductName());
                    pstmt.setInt(2, order.getQuantity());
                    pstmt.setInt(3, order.getPrice());
                    pstmt.executeUpdate();
                    insertCnt ++;
                }
            }

            // commit
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("에러: 입력중 오류 발생! 롤백 수행됨");
                }
            } catch (SQLException rollbackEx) {
                System.out.println("에러: 롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
                // 피드백 2 수정
                throw rollbackEx;
            }
            e.printStackTrace();
            // 피드백 2 수정
            throw e;
        }
        return insertCnt;
    }
}