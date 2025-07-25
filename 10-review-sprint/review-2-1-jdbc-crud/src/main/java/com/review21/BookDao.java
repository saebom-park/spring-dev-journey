package com.review21;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection conn;

    // constructor
    // 피드백 4 수정
    public BookDao(Connection conn) { this.conn = conn; }

    // insert
    public void insert(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPrice());
            pstmt.executeUpdate();
        }
    }

    // select
    public List<Book> findAll() throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
            return list;
        }
    }

    // select by id
    public Book findById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            // 피드백 3 수정
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Book found = new Book();
                    found.setId(rs.getInt("id"));
                    found.setTitle(rs.getString("title"));
                    found.setAuthor(rs.getString("author"));
                    found.setPrice(rs.getInt("price"));
                    return found;
                } else {
                    return null;
                }
            }
        }
    }

    // update
    public int update(int id, int type, String value) throws SQLException {
        String typeText = "";
        switch(type) {
            case 1:
                typeText = "title";
                break;
            case 2:
                typeText = "author";
                break;
            case 3:
                typeText = "price";
            default:
        }

        int result= 0;
        if (!typeText.isEmpty()) {
            String sql = "UPDATE books SET " + typeText + " = ? " + "WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 피드백 1 수정
                if (type == 3) {
                    pstmt.setInt(1, Integer.parseInt(value));
                } else {
                    pstmt.setString(1, value);
                }

                pstmt.setInt(2, id);
                result = pstmt.executeUpdate();
            }
        }
        return result;
    }

    // delete by id
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        int result = 0;
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        }
        return result;
    }
}