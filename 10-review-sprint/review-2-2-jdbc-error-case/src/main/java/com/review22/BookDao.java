package com.review22;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    // constructor
    public BookDao(Connection conn) { this.conn = conn; }

    // insert
    // 단건 insert는 트랜잭션 필요없음
    public void insert(Book book) throws SQLException {
        try {
            String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // select all
    // 읽기 전용 select는 트랜잭션 필요 없음
    public List<Book> findAll() throws SQLException {
        List<Book> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM books";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return list;
    }

    // select by id
    public Book findById(int id) throws SQLException {
        Book found = new Book();

        try {
            String sql = "SELECT * FROM books WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                found.setId(rs.getInt("id"));
                found.setTitle(rs.getString("title"));
                found.setAuthor(rs.getString("author"));
                found.setPrice(rs.getInt("price"));
            } else {
                // 피드백 1 수정
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return found;
    }

    // insert multiple
    public int insertMultiple(List<Book> books) throws SQLException {
        int insertCnt = 0;
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);
            // transaction
            conn.setAutoCommit(false);

            for (Book book : books) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getPrice());
                pstmt.executeUpdate();
                insertCnt ++;
            }

            // commit
            conn.commit();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("에러: 입력 중 오류 발생! 롤백 수행됨");
                }
            } catch (SQLException rollbackEx) {
                System.out.println("에러: 롤백 중 오류 발생!");
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return insertCnt;
    }

    // update
    public void update(int id, int type, String value) throws SQLException {
        String typeText = "";
        switch (type) {
            case 1: typeText = "title"; break;
            case 2: typeText = "author" ; break;
            case 3: typeText = "price"; break;
            default: throw new SQLException("유효하지 않은 type 입니다.");
        }

        String sql = "UPDATE books SET " + typeText + " = ? WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // transaction
            conn.setAutoCommit(false);
            if (type == 3) {
                try {
                    pstmt.setInt(1, Integer.parseInt(value));
                } catch (NumberFormatException formatEx) {
                    throw new SQLException("에러: 가격은 숫자로 입력해야 합니다. [value =" + value + "]", formatEx);
                }

            } else {
                pstmt.setString(1, value);
            }
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

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
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    // delete by id
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            // transaction
            conn.setAutoCommit(false);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

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
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}