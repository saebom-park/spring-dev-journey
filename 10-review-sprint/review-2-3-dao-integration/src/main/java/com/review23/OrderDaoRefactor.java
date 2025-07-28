package com.review23;

import java.sql.*;
import java.util.*;

public class OrderDaoRefactor {
    private Connection conn;

    // constructor
    public OrderDaoRefactor(Connection conn) {
        this.conn = conn;
    }

    // find all
    // 읽기 전용 메서드는 일반적으로 트랜잭션 사용하지 않음
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setProductName(rs.getString("productName"));
                order.setQuantity(rs.getInt("quantity"));
                order.setPrice(rs.getInt("price"));
                orders.add(order);
            }
        }
        return orders;
    }
    
    // find by id
    // 피드백 3 수정
    public Optional<Order> findById(Long id) throws SQLException {
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

    // insert
    // 단건 입력은 일반적으로 트랜잭션 사용하지 않음
    public int insert(Order order) throws SQLException {
        int insertCnt = 0;
        String sql = "INSERT INTO orders (productName, quantity, price) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getProductName());
            pstmt.setInt(2, order.getQuantity());
            pstmt.setInt(3, order.getPrice());
            insertCnt = pstmt.executeUpdate();
        }
        return insertCnt;
    }

    // insert multiple
    public int insertMultiple(List<Order> orders) throws SQLException {
        // 피드백 1 수정
        if (orders == null || orders.isEmpty()) {
            throw new SQLException("입력된 주문 정보가 없습니다.");
        }
        int insertCnt = 0;
        String sql = "INSERT INTO orders (productName, quantity, price) VALUES (?, ?, ?)";

        try {
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
                    System.out.println("에러: 입력 중 오류 발생! 롤백 수행 됨");
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

    // update
    public int update(Long id, int type, String value) throws SQLException {
        // 피드백 6 수정
        String typeText = ORDER_COLUMNS.get(type);
        if (typeText == null) {
            throw new SQLException("유효하지 않은 type 입니다.");
        }
        int updateCnt = 0;
        String sql = "UPDATE orders SET " + typeText + " = ? WHERE id = ?";

        try {
            // transaction
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 피드백 5 수정
                if ("quantity".equals(typeText) || "price".equals(typeText)) {
                    try {
                        pstmt.setInt(1, Integer.parseInt(value));
                    } catch (NumberFormatException formatEx) {
                        throw new SQLException("에러: 숫자만 입력 가능합니다. [value = " + value + "]", formatEx);
                    }
                } else {
                    pstmt.setString(1, value);
                }
                pstmt.setLong(2, id);
                updateCnt = pstmt.executeUpdate();
            }

            // commit
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("에러: 업데이트 중 오류 발생! 롤백 수행");
                }
            } catch (SQLException rollbackEx) {
                System.out.println("에러: 롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
                throw rollbackEx;
            }
            e.printStackTrace();
            throw e;
        }
        return updateCnt;
    }

    // delete
    public int delete(Long id) throws SQLException {
        int deleteCnt = 0;
        String sql = "DELETE FROM orders WHERE id = ?";

        try {
            // transaction
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                deleteCnt = pstmt.executeUpdate();
            }

            // commit
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("에러: 삭제 중 오류 발생! 롤백 수행");
                }
            } catch (SQLException rollbackEx) {
                System.out.println("에러: 롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
                throw rollbackEx;
            }
            e.printStackTrace();
            throw e;
        }
        return deleteCnt;
    }
    
    // 피드백 6 수정
    private static final Map<Integer, String> ORDER_COLUMNS = new HashMap<>();
    static {
        ORDER_COLUMNS.put(1, "productName");
        ORDER_COLUMNS.put(2, "quantity");
        ORDER_COLUMNS.put(3, "price");
    }
}