package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcSelectOne {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        Scanner scanner = new Scanner(System.in);

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
        ) {
            stmt.execute("CREATE TABLE students (id INT, name VARCHAR(100), major VARCHAR(100))");
            stmt.execute("INSERT INTO students VALUES (1, '봄이', '통계학')");
            stmt.execute("INSERT INTO students VALUES (2, '온이', '컴퓨터공학')");

            System.out.print("조회할 학생의 ID를 입력해 주세요: ");
            int targetId = scanner.nextInt();
            scanner.nextLine();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE id = " + targetId);
            if (rs.next()) {
                System.out.println("조회결과:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("이름: " + rs.getString("name"));
                System.out.println("전공: " + rs.getString("major"));
            } else {
                System.out.println("해당 ID의 학생이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}