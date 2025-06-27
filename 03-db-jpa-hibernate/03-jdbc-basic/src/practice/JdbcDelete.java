package practice;

import java.sql.*;
import java.util.Scanner;

public class JdbcDelete {
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
            stmt.execute("INSERT INTO students VALUES (3, '솔이', '경제학')");

            System.out.print("삭제할 아이디를 입력해주세요: ");
            int idToDelete = scanner.nextInt();
            scanner.nextLine();

            boolean success = !stmt.execute("DELETE FROM students WHERE id = " + idToDelete);
            // success는 항상 false임 (DELETE는 결과 셋이 없으므로)
            System.out.println("삭제 쿼리 실행 완료 (삭제 여부: " + success + ")");

            System.out.println("[현재 남은 학생 목록] ");
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println (
                        rs.getInt("id") + ", " +
                        rs.getString("name") + ", " +
                        rs.getString("major")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}